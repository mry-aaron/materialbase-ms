package net.qicp.aaron.domain;

/**
 * @Author Aaron
 * @Description 媒体信息
 * @Version 1.0
 * @Date 13:00 2019/3/17
 */
public class MediaBean {

    private Integer id;     // 编号
    private String  mName;  // 媒体名称

    /**
     * 构造器
     */
    public MediaBean() {
    }
    public MediaBean(Integer id, String mName) {
        this.id = id;
        this.mName = mName;
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
    public String getmName() {
        return mName;
    }
    public void setmName(String mName) {
        this.mName = mName;
    }
}
