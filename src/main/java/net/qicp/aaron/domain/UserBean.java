package net.qicp.aaron.domain;

import java.sql.Timestamp;

/**
 * @Author Aaron
 * @Description 用户信息
 * @Version 1.0
 * @Date 9:30 2019/3/13
 */
public class UserBean {

    private Integer id;                 // 用户编号
    private String name;                // 用户名
    private String password;            // 密码
    private String telephone;           // 手机号
    private String qq;                  // QQ
    private String wechat;              // 微信
    private String sina;                // 新浪微博
    private Integer roleId;             // 角色编号
    private Integer deptId;             // 部门编号
    private Timestamp createTime;       // 创建时间
    private Timestamp lastLoginTime;    // 最近登录时间
    private String headImg;             // 头像

    /**
     * 构造器
     */
    public UserBean() { }
    public UserBean(Integer id, String name, String password, String telephone, String qq, String wechat, String sina, Integer roleId, Integer deptId, Timestamp createTime, Timestamp lastLoginTime, String headImg) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.telephone = telephone;
        this.qq = qq;
        this.wechat = wechat;
        this.sina = sina;
        this.roleId = roleId;
        this.deptId = deptId;
        this.createTime = createTime;
        this.lastLoginTime = lastLoginTime;
        this.headImg = headImg;
    }
    public UserBean(String name, String password, String telephone) {
        this.name = name;
        this.password = password;
        this.telephone = telephone;
    }

    /**
     * 访问器
     *
     */
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getQq() {
        return qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    public String getWechat() {
        return wechat;
    }
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    public String getSina() {
        return sina;
    }
    public void setSina(String sina) {
        this.sina = sina;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public Integer getDeptId() {
        return deptId;
    }
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    public Timestamp getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }
    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    public String getHeadImg() {
        return headImg;
    }
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
