package net.qicp.aaron.controller;

import net.qicp.aaron.domain.UserBean;
import net.qicp.aaron.service.UserService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.tomcat.jni.Multicast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author Aaron
 * @Description 用户请求响应处理
 * @Version 1.0
 * @Date 13:14 2019/3/13
 */
@RestController
public class UserController {
    private Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/reg")
    public void regUser(UserBean userBean) {
        Integer num = userService.regUser(userBean);
        if(num > 0){
            log.debug("用户注册成功！======UserController: reg =======");
        } else {
            log.debug("用户注册失败！======UserController: reg =======");
        }
    }

    @RequestMapping("/finduser")
    public String findUser(UserBean userBean) {
        // "0"代表存在，"1"代表不存在
        return userService.findUser(userBean) ? "0" : "1";
    }

    @RequestMapping("/sendverificode")
    public Object sendVerificationCode(@RequestParam("telephone") String telephone,
                                       HttpServletRequest request) {
        return userService.sendVerificationCode(telephone, request);
    }

    @RequestMapping("/verifi")
    public String verification(@RequestParam("code") String code, HttpServletRequest request) {
        return code.equals(request.getSession().getAttribute("code").toString()) ? "1" : "0";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("param") String param, HttpServletRequest request) throws Exception {
        // 访问登录方法
        UserBean userBean = userService.login(param, request);
        JSONObject object = new JSONObject();
        if (userBean != null){
            object.put("user", userBean);
            object.put("code", 1);
            // 保存用户名
            HttpSession session = request.getSession();
            session.setAttribute("userName", userBean.getName());
            // 清空错误信息
            session.setAttribute("sign_msg", null);
        } else {
            object.put("code", 0);
        }
        return object.toString();
    }

    @RequestMapping("/logintele")
    public String loginTele(@RequestParam("param") String param, HttpServletRequest request) {
        log.debug(param + "*******UserController ：logintele*******");
        JSONObject object = new JSONObject();
        UserBean userBean = userService.loginTele(param, request);
        if(userBean != null){
            object.put("user", userBean);
            object.put("code", 1);
            // 保存用户名
            HttpSession session = request.getSession();
            session.setAttribute("telephone", userBean.getTelephone());
            // 清空错误信息
            session.setAttribute("sign_msg", null);
        } else {
            object.put("code", 0);
        }
        return object.toString();
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("/");
    }

    @RequestMapping("/edituser")
    public void editUser(@RequestParam("img") MultipartFile img, UserBean userBean,
                         HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug(img.getOriginalFilename() + "========UserController: edituser============");
        log.debug(JSONObject.fromObject(userBean).toString() + "========UserController: edituser============");
        PrintWriter out = response.getWriter();
        if(userService.editUser(img, userBean)){ // 成功
            // 注销Session，重新登录
            request.getSession().invalidate();
            // 跳转页面
            out.print("<script type='text/javascript'>");
            //out.print("alert('用户保存成功！请重新登录！');");
            out.print("location.href='/'");
            out.print("</script>");
        } else { // 失败
            out.print("<script type='text/javascript'>");
            out.print("alert('用户保存失败！请重试！');");
            out.print("history.go(-1);");
            out.print("</script>");
        }
    }

    @RequestMapping("/toeditpage")
    public String toEditPage(HttpServletRequest request){
        UserBean user = null;
        // 获取登录时保存的用户名或手机号
        Object userName = request.getSession().getAttribute("userName");
        Object telephone = request.getSession().getAttribute("telephone");
        if(userName != null || telephone != null){
            // 获取用户信息
            user = userService.findUserByNameOrTelephone(userName, telephone);
        }
        return JSONObject.fromObject(user).toString();
    }

}
