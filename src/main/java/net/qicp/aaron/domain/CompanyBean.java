package net.qicp.aaron.domain;

/**
 * @Author Aaron
 * @Description 公司信息
 * @Version 1.0
 * @Date 12:57 2019/3/17
 */
public class CompanyBean {

    private Integer id;     // 编号
    private String  cName;  // 公司名称

    /**
     * 构造器
     */
    public CompanyBean() {
    }
    public CompanyBean(Integer id, String cName) {
        this.id = id;
        this.cName = cName;
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
    public String getcName() {
        return cName;
    }
    public void setcName(String cName) {
        this.cName = cName;
    }
}
