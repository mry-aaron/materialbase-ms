package net.qicp.aaron.service;

import net.qicp.aaron.domain.UserBean;
import net.qicp.aaron.mapper.UserMapper;
import net.qicp.aaron.utils.SendVerificationCodeUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册用户
     * @param userBean
     */
    public boolean regUser(UserBean userBean) {
        userBean.setCreateTime(new Timestamp(new Date().getTime()));
        userMapper.regUser(userBean);
        return false;
    }

    /**
     * 查询用户是否存在（注册时使用）
     * @param userBean
     * @return
     */
    public boolean findUser(UserBean userBean) {
        return userMapper.findByUser(userBean) > 0 ? true : false;
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
            /*String result = SendVerificationCodeUtil.sendShortMessage(telephone, URLEncoder.encode(tplValue, "UTF-8"), "json");*/
            String result = "{\"error_code\":0,\"result\":\"...\",\"reason\":\"操作成功\"}";
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
    public UserBean login(String param) {
        // 将参数解析成JSON对象,获取用户名/密码
        JSONObject object = JSONObject.fromObject(param);
        // 验证用户名是否存在（在拦截器中验证）
        UserBean userBean = new UserBean();
        userBean.setName(object.getString("name"));
        userBean.setPassword(object.getString("password"));
        return userMapper.findByNameAndPassword(userBean);
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
        if(code.equals(request.getSession().getAttribute("code").toString()) &&
            userMapper.findByUser(userBean) > 0){
            return userBean;
        }
        return null;
    }


}
