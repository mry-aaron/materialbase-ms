package net.qicp.aaron.mapper;

import net.qicp.aaron.domain.UserBean;
import org.springframework.stereotype.Component;

/**
 * @Author Aaron
 * @Description 用户访问接口
 * @Version 1.0
 * @Date 9:55 2019/3/13
 */
@Component
public interface UserMapper {

    /**
     * 注册用户
     * @param userBean
     */
    Integer regUser(UserBean userBean);

    /**
     * 编辑用户
     * @param userBean
     */
    Integer editUser(UserBean userBean);

    /**
     * 查询用户是否存在
     * @param userBean
     * @return
     */
    Integer findByUser(UserBean userBean);

    /**
     * 根据用户名密码查询用户（登录）
     * @param userBean
     * @return
     */
    UserBean findByNameAndPassword(UserBean userBean);

    /**
     * 更新登录时间
     * @param userBean
     * @return
     */
    Integer updateLoginTime(UserBean userBean);

    /**
     * 查询用户信息
     * @param userBean
     * @return
     */
    UserBean findUserByNameOrTelephone(UserBean userBean);

}
