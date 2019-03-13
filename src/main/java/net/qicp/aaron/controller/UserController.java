package net.qicp.aaron.controller;

import net.qicp.aaron.domain.UserBean;
import net.qicp.aaron.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Aaron
 * @Description 用户请求响应处理
 * @Version 1.0
 * @Date 13:14 2019/3/13
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/reg")
    public void regUser(UserBean userBean) {
        userService.regUser(userBean);
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

}
