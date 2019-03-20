package net.qicp.aaron.mapper;

import net.qicp.aaron.domain.*;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author Aaron
 * @Description 素材访问接口
 * @Version 1.0
 * @Date 20:21 2019/3/17
 */
@Component
public interface MaterialMapper {

    /**
     * 获取全部素材风格
     * @return
     */
    List<SMStyleBean> getStyle();
    /**
     * 获取全部素材类型
     * @return
     */
    List<SMTypeBean> getType();
    /**
     * 获取全部媒体
     * @return
     */
    List<MediaBean> getMedia();
    /**
     * 获取全部公司信息
     * @return
     */
    List<CompanyBean> getCompany();

    /**
     * 根据公司名称查询ID
     * @param cName
     * @return
     */
    Integer findCompanyIdByName(String cName);

    /**
     * 保存公司信息
     * @param cName
     * @return
     */
    Integer saveCompany(String cName);

    /**
     * 录入素材
     * @param materialBean
     * @return
     */
    Integer recordMaterial(MaterialBean materialBean);

    /**
     * 查询素材
     * @param materialBean
     * @return
     */
    List<MaterialBean> findMaterialBy(MaterialBean materialBean);

    /**
     * 删除素材通过ID
     * @param id
     * @return
     */
    Integer delMaterialById(Integer id);

    /**
     * 编辑素材
     * @param materialBean
     * @return
     */
    Integer editMaterial(MaterialBean materialBean);

    /**
     * 获取点赞次数排名前五的素材
     * @param type 素材类型
     * @return
     */
    List<MaterialBean> getMaterialTop5(Integer type);

    /**
     * 获取全部素材
     * @param materialBean
     * @return
     */
    List<MaterialBean> getAllSM(MaterialBean materialBean);

    /**
     * 修改下载次数
     * @param id
     */
    void updateDownloadCount(Integer id);

    /**
     * 获取下载次数
     * @param id
     * @return
     */
    String getDownload(Integer id);

    /**
     * 根据ID获取素材详情
     * @param id
     * @return
     */
    MaterialBean getDetails(Integer id);

    /**
     * 获取下载次数前十的素材
     * @return
     */
    LinkedList<MaterialBean> getMaterialDownloadTop10();

}
