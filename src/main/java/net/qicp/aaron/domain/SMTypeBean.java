package net.qicp.aaron.domain;

/**
 * @Author Aaron
 * @Description 素材类型
 * @Version 1.0
 * @Date 13:04 2019/3/17
 */
public class SMTypeBean {

    private Integer id;     // 编号
    private String  tName;  // 类型名称

    /**
     * 构造器
     */
    public SMTypeBean() {
    }
    public SMTypeBean(Integer id, String tName) {
        this.id = id;
        this.tName = tName;
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
    public String gettName() {
        return tName;
    }
    public void settName(String tName) {
        this.tName = tName;
    }
}
