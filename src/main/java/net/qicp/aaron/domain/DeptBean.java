package net.qicp.aaron.domain;

/**
 * @Author Aaron
 * @Description 部门信息
 * @Version 1.0
 * @Date 9:45 2019/3/13
 */
public class DeptBean {

    private Integer id;         // 部门编号
    private String deptName;    // 部门名称

    /**
     * 构造器
     */
    public DeptBean() { }
    public DeptBean(Integer id, String deptName) {
        this.id = id;
        this.deptName = deptName;
    }

    /**
     * 访问器
     */
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
