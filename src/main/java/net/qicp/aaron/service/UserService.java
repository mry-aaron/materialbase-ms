package net.qicp.aaron.service;

import net.qicp.aaron.domain.UserBean;
import net.qicp.aaron.mapper.UserMapper;
import net.qicp.aaron.utils.FileUploadUtil;
import net.qicp.aaron.utils.MD5Util;
import net.qicp.aaron.utils.SendVerificationCodeUtil;
import net.qicp.aaron.utils.UUIDUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author Aaron
 * @Description 用户业务逻辑处理类
 * @Version 1.0
 * @Date 13:06 2019/3/13
 */
@Service
public class UserService {
    private Logger log = LoggerFactory.getLogger(UserService.class);
    @Value("${file.heads}")
    private String filePath;
    @Value("${file.material.host}")
    private String host;

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册用户
     * @param userBean
     */
    public Integer regUser(UserBean userBean) {
        Integer num = 0;
        userBean.setCreateTime(new Timestamp(new Date().getTime()));
        // 获取密码进行加密
        String password = userBean.getPassword();
        if(password != null) {
            log.debug(password+" =======UserService: regUser==========");
            String md5String = MD5Util.getMD5String(password);
            log.debug(md5String+" =======UserService: regUser==========");
            userBean.setPassword(md5String);
        }
        num = userMapper.regUser(userBean);
        return num;
    }

    /**
     * 查询用户是否存在（注册时使用）
     * @param userBean
     * @return
     */
    public boolean findUser(UserBean userBean) {
        Integer id = userMapper.findByUser(userBean);
        return id != null && id > 0  ? true : false;
    }

    /**
     * 发送短信验证码
     * @param telephone
     * @return
     */
    public Object sendVerificationCode(String telephone, HttpServletRequest request) {
        JSONObject object = null;
        try {
            // 生成随机6位数字验证码
            int randNum = (int)(Math.random()*899999) + 100000;
            log.debug(randNum+" *****验证码****");
            // 将验证码存入session用于验证
            request.getSession().setAttribute("code", randNum);
            // 发送短信验证码
            String tplValue = "#code#=" + randNum;
            String result = SendVerificationCodeUtil.sendShortMessage(telephone, URLEncoder.encode(tplValue, "UTF-8"), "json");
            /*String result = "{\"error_code\":0,\"result\":\"...\",\"reason\":\"操作成功\"}";*/
            // 处理返回结果
            object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                log.debug(object.get("result").toString());
            } else {
                log.debug(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 登录
     * @param param
     * @return
     */
    public UserBean login(String param, HttpServletRequest request) {
        UserBean user = null;
        // 将参数解析成JSON对象,获取用户名/密码
        JSONObject object = JSONObject.fromObject(param);
        // 验证用户名是否存在（在拦截器中验证）
        UserBean userBean = new UserBean();
        userBean.setName(object.getString("name"));
        userBean.setPassword(MD5Util.getMD5String(object.getString("password")));
        user = userMapper.findByNameAndPassword(userBean);
        if(user != null){
            // 更新登录时间
            userBean.setLastLoginTime(new Timestamp(new Date().getTime()));
            userMapper.updateLoginTime(userBean);
            // 设置用户头像
            request.getSession().setAttribute("headImg", host+filePath+userMapper.findUserByNameOrTelephone(userBean).getHeadImg());
        }
        return user;
    }

    /**
     * 手机号登录
     * @param param
     * @return
     */
    public UserBean loginTele(String param, HttpServletRequest request) {
        UserBean userBean = new UserBean();
        // 将参数解析成JSON对象,获取手机号/验证码
        JSONObject object = JSONObject.fromObject(param);
        String code = object.getString("code");
        userBean.setTelephone(object.getString("telephone"));
        // 能查询到手机号并且验证码正确即登录成功
        Integer id = userMapper.findByUser(userBean);
        if(code.equals(request.getSession().getAttribute("code").toString()) && id > 0){
            userBean.setId(id);
            // 更新登录时间
            userBean.setLastLoginTime(new Timestamp(new Date().getTime()));
            userMapper.updateLoginTime(userBean);
            // 设置用户头像
            request.getSession().setAttribute("headImg", host+filePath+userMapper.findUserByNameOrTelephone(userBean).getHeadImg());
            return userBean;
        }
        return null;
    }

    /**
     * 编辑用户
     * @param file
     * @param userBean
     */
    public boolean editUser(MultipartFile file, UserBean userBean, HttpServletRequest request) {
        boolean result = false;
        try {
            String fileName = file.getOriginalFilename();
            log.debug(fileName + "=======fileName=======");
            if(fileName != null && fileName.length() > 0) {
                //获取文件后缀
                String suffix = fileName.substring(fileName.lastIndexOf("."));
                //判断是否为一个图片
                if(!suffix.matches("^\\.(jpg|png|gif)$")) {
                    log.error("******************该文件不是一个图片!");
                    return result;
                }
                // 上传图片
                String imgName = UUIDUtil.generFileName() + suffix;
                String imgURL = FileUploadUtil.upload(filePath, imgName, file.getInputStream());
                log.debug(imgURL + " =============图片链接=================");
                // 保存图片
                userBean.setHeadImg(imgName);
            }

            //保存用户信息
            Integer did = userBean.getDeptId();
            if(did != null && did != 0){ // 管理部为管理员角色
                if(did == 1) userBean.setRoleId(1);
                else userBean.setRoleId(2);
            }
            // 获取密码进行加密
            String password = userBean.getPassword();
            if(password != null && password.length() > 0) userBean.setPassword(MD5Util.getMD5String(password));
            if(userMapper.editUser(userBean) > 0) result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 编辑用户前展示用户信息
     * @param userName
     * @param telephone
     * @return
     */
    public UserBean findUserByNameOrTelephone(Object userName, Object telephone){
        UserBean userBean = new UserBean();
        if(userName != null) userBean.setName(userName.toString());
        if(telephone != null) userBean.setTelephone(telephone.toString());
        // 调用查询
        return userMapper.findUserByNameOrTelephone(userBean);
    }

}
