package net.qicp.aaron.mapper;

import net.qicp.aaron.domain.*;
import org.springframework.stereotype.Component;

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

}
