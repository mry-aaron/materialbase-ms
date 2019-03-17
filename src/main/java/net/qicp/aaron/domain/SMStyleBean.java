package net.qicp.aaron.domain;

/**
 * @Author Aaron
 * @Description 素材风格
 * @Version 1.0
 * @Date 13:02 2019/3/17
 */
public class SMStyleBean {

    private Integer id;     // 编号
    private String  sName;  // 风格名称

    /**
     * 构造器
     */
    public SMStyleBean() {
    }
    public SMStyleBean(Integer id, String sName) {
        this.id = id;
        this.sName = sName;
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
    public String getsName() {
        return sName;
    }
    public void setsName(String sName) {
        this.sName = sName;
    }
}
