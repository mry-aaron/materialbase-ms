package net.qicp.aaron.domain;

import java.sql.Timestamp;

/**
 * @Author Aaron
 * @Description 素材信息
 * @Version 1.0
 * @Date 12:36 2019/3/17
 */
public class MaterialBean {

    private Integer     id;             // 编号
    private String      productName;    // 产品名
    private Integer     companyId;      // 公司名
    private Integer     smTypeId;       // 素材类型
    private String      size;           // 素材尺寸
    private Integer     smStyleId;      // 素材风格
    private String      productTheme;   // 产品题材
    private Integer     download;       // 下载次数
    private String      consume;        // 素材消耗
    private Integer     starLevel;      // 素材星级
    private Integer     mediaId;        // 媒体
    private String      platform;       // 素材平台
    private Integer     like;           // 点赞次数
    private Integer     browse;         // 浏览次数
    private Timestamp   entryTime;      // 录入时间
    private String      materialPath;   // 素材路径
    private Integer     isDelete;       // 删除标记（0：未删，1：删除）

    /* 用于区分大于2/等于1/小于0*/
    private Integer switchLike;
    private Integer switchDownload;
    private Integer switchBrowse;

    /* 配置一对一查询 */
    private CompanyBean companyBean;
    private SMTypeBean  smTypeBean;
    private SMStyleBean smStyleBean;
    private MediaBean   mediaBean;
    private String      cName;
    private String      tName;
    private String      sName;
    private String      mName;

    /* 分页属性 */
    private Integer page  = 1;   // 页数
    private Integer limit = 10;  // 行数

    /* 记录素材是否点赞 */
    private Integer points;

    /**
     * 构造器
     */
    public MaterialBean() {
    }
    public MaterialBean(Integer id, String productName, Integer companyId, Integer smTypeId, String size, Integer smStyleId, String productTheme, Integer download, String consume, Integer starLevel, Integer mediaId, String platform, Integer like, Integer browse, Timestamp entryTime, String materialPath, Integer isDelete) {
        this.id = id;
        this.productName = productName;
        this.companyId = companyId;
        this.smTypeId = smTypeId;
        this.size = size;
        this.smStyleId = smStyleId;
        this.productTheme = productTheme;
        this.download = download;
        this.consume = consume;
        this.starLevel = starLevel;
        this.mediaId = mediaId;
        this.platform = platform;
        this.like = like;
        this.browse = browse;
        this.entryTime = entryTime;
        this.materialPath = materialPath;
        this.isDelete = isDelete;
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
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public Integer getSmTypeId() {
        return smTypeId;
    }
    public void setSmTypeId(Integer smTypeId) {
        this.smTypeId = smTypeId;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public Integer getSmStyleId() {
        return smStyleId;
    }
    public void setSmStyleId(Integer smStyleId) {
        this.smStyleId = smStyleId;
    }
    public String getProductTheme() {
        return productTheme;
    }
    public void setProductTheme(String productTheme) {
        this.productTheme = productTheme;
    }
    public Integer getDownload() {
        return download;
    }
    public void setDownload(Integer download) {
        this.download = download;
    }
    public String getConsume() {
        return consume;
    }
    public void setConsume(String consume) {
        this.consume = consume;
    }
    public Integer getStarLevel() {
        return starLevel;
    }
    public void setStarLevel(Integer starLevel) {
        this.starLevel = starLevel;
    }
    public Integer getMediaId() {
        return mediaId;
    }
    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }
    public String getPlatform() {
        return platform;
    }
    public void setPlatform(String platform) {
        this.platform = platform;
    }
    public Integer getLike() {
        return like;
    }
    public void setLike(Integer like) {
        this.like = like;
    }
    public Integer getBrowse() {
        return browse;
    }
    public void setBrowse(Integer browse) {
        this.browse = browse;
    }
    public Timestamp getEntryTime() {
        return entryTime;
    }
    public void setEntryTime(Timestamp entryTime) {
        this.entryTime = entryTime;
    }
    public String getMaterialPath() {
        return materialPath;
    }
    public void setMaterialPath(String materialPath) {
        this.materialPath = materialPath;
    }
    public Integer getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    /* switch */
    public Integer getSwitchLike() {
        return switchLike;
    }
    public void setSwitchLike(Integer switchLike) {
        this.switchLike = switchLike;
    }
    public Integer getSwitchDownload() {
        return switchDownload;
    }
    public void setSwitchDownload(Integer switchDownload) {
        this.switchDownload = switchDownload;
    }
    public Integer getSwitchBrowse() {
        return switchBrowse;
    }
    public void setSwitchBrowse(Integer switchBrowse) {
        this.switchBrowse = switchBrowse;
    }
    /* 一对一 */
    public CompanyBean getCompanyBean() {
        return companyBean;
    }
    public void setCompanyBean(CompanyBean companyBean) {
        this.companyBean = companyBean;
    }
    public SMTypeBean getSmTypeBean() {
        return smTypeBean;
    }
    public void setSmTypeBean(SMTypeBean smTypeBean) {
        this.smTypeBean = smTypeBean;
    }
    public SMStyleBean getSmStyleBean() {
        return smStyleBean;
    }
    public void setSmStyleBean(SMStyleBean smStyleBean) {
        this.smStyleBean = smStyleBean;
    }
    public MediaBean getMediaBean() {
        return mediaBean;
    }
    public void setMediaBean(MediaBean mediaBean) {
        this.mediaBean = mediaBean;
    }
    public String getcName() {
        return cName;
    }
    public void setcName(String cName) {
        this.cName = cName;
    }
    public String gettName() {
        return tName;
    }
    public void settName(String tName) {
        this.tName = tName;
    }
    public String getsName() {
        return sName;
    }
    public void setsName(String sName) {
        this.sName = sName;
    }
    public String getmName() {
        return mName;
    }
    public void setmName(String mName) {
        this.mName = mName;
    }
    /* 分页属性 */
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    /* 记录素材是否点赞 */
    public Integer getPoints() {
        return points;
    }
    public void setPoints(Integer points) {
        this.points = points;
    }
}
