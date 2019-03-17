package net.qicp.aaron.service;

import net.qicp.aaron.domain.CompanyBean;
import net.qicp.aaron.domain.MaterialBean;
import net.qicp.aaron.mapper.MaterialMapper;
import net.qicp.aaron.utils.FileUploadUtil;
import net.qicp.aaron.utils.UUIDUtil;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author Aaron
 * @Description 素材业务逻辑处理类
 * @Version 1.0
 * @Date 20:30 2019/3/17
 */
@Service
public class MaterialService {
    private Logger log = LoggerFactory.getLogger(MaterialService.class);
    @Value("file.material.picture")
    private String filePathPicture; // 素材路径（图片）
    @Value("file.material.vedio")
    private String filePathVedio; // 素材路径（视频）

    @Autowired
    private MaterialMapper materialMapper;

    /**
     * 获取全部素材风格
     * @return json
     */
    public String getStyle(){
        return JSONArray.fromObject(materialMapper.getStyle()).toString();
    }

    /**
     * 获取全部素材类型
     * @return json
     */
    public String getType(){
        return JSONArray.fromObject(materialMapper.getType()).toString();
    }

    /**
     * 获取全部媒体
     * @return json
     */
    public String getMedia(){
        return JSONArray.fromObject(materialMapper.getMedia()).toString();
    }

    /**
     * 录入素材
     * @param file
     * @param materialBean
     * @param companyBean
     * @return
     */
    public boolean recordMaterial(MultipartFile file, MaterialBean materialBean, CompanyBean companyBean) {
        boolean result = false;
        // 判断公司是否存在
        String cName = companyBean.getcName();
        Integer cId = materialMapper.findCompanyIdByName(cName);
        if(cId == null){ // 不存在该公司
            if(materialMapper.saveCompany(cName) > 0){
                cId = materialMapper.findCompanyIdByName(cName);
            } else {
                return result;
            }
        }
        // 上传素材到服务器
        try {
            //获取文件后缀
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            // 生成文件名称
            String generName = UUIDUtil.generFileName() + suffix;
            //判断是否为一个图片
            String filePath = null;
            if(suffix.matches("^\\.(jpg|png|gif)$")) {
                filePath = filePathPicture;
            }
            // 判断是否为一个视频
            if(suffix.matches("^\\.(mp4|avi)$")) {
                filePath = filePathVedio;
            }
            String fPath = FileUploadUtil.upload(filePath, generName, file.getInputStream());
            log.debug(fPath + "===============MaterialService: fPath====================");

            // 保存信息到数据库
            materialBean.setCompanyId(cId);
            materialBean.setMaterialPath(fPath);
            materialBean.setEntryTime(new Timestamp(new Date().getTime()));
            if(materialMapper.recordMaterial(materialBean) > 0) result = true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

}
