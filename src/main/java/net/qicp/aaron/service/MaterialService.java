package net.qicp.aaron.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.qicp.aaron.component.FileUploadThread;
import net.qicp.aaron.domain.CompanyBean;
import net.qicp.aaron.domain.MaterialBean;
import net.qicp.aaron.mapper.MaterialMapper;
import net.qicp.aaron.utils.UUIDUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * @Author Aaron
 * @Description 素材业务逻辑处理类
 * @Version 1.0
 * @Date 20:30 2019/3/17
 */
@Service
public class MaterialService {
    private Logger log = LoggerFactory.getLogger(MaterialService.class);
    @Value("${file.material.picture}")
    private String filePathPicture; // 素材路径（图片）
    @Value("${file.material.video}")
    private String filePathVideo; // 素材路径（视频）

    @Autowired
    private MaterialMapper materialMapper;
    @Autowired
    private FileUploadThread fileUploadThread;

    /**
     * 获取全部公司
     * @return json
     */
    public String getCompany(){
        return JSONArray.fromObject(materialMapper.getCompany()).toString();
    }

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
                filePath = filePathVideo;
            }
            // 使用异步线程实现文件上传
            fileUploadThread.uploadFile(filePath, generName, file.getInputStream());

            // 保存信息到数据库
            materialBean.setCompanyId(cId);
            materialBean.setMaterialPath(generName);
            materialBean.setEntryTime(new Timestamp(new Date().getTime()));
            if(materialMapper.recordMaterial(materialBean) > 0) result = true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 查询素材
     * @param materialBean
     * @return
     */
    public String findMaterial(MaterialBean materialBean){
        // 定义返回数据
        HashMap<String, Object> result = new HashMap<>();
        // 分页处理
        PageHelper.startPage(materialBean.getPage(), materialBean.getLimit());
        materialBean.setIsDelete(0); // 默认查询未删除素材
        List<MaterialBean> materials = materialMapper.findMaterialBy(materialBean);
        for (MaterialBean material : materials){
            material.setcName(material.getCompanyBean().getcName());
            material.settName(material.getSmTypeBean().gettName());
            material.setsName(material.getSmStyleBean().getsName());
            material.setmName(material.getMediaBean().getmName());
        }
        // 绑定返回结果集
        if (materials != null) {
            // 处理Timestamp日期格式
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonValueProcessor() {
                private String format = "yyyy-MM-dd HH:mm:ss";
                public Object processArrayValue(Object value, JsonConfig config) { return process(value); }
                public Object processObjectValue(String arg0, Object value, JsonConfig config) { return process(value); }
                private Object process(Object value) {
                    //遇到类型为日期的，就自动转换成“yyyy-MM-dd HH:mm:ss”格式的字符串
                    if (value instanceof Timestamp) {
                        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.UK);
                        return sdf.format(((Timestamp) value).getTime());
                    }
                    return value == null ? "" : value.toString();
                }
            });
            // 处理结果
            PageInfo<MaterialBean> pageInfo = new PageInfo<>(materials);
            result.put("code",  0);
            result.put("msg",   "");
            result.put("count", pageInfo.getTotal());
            result.put("data",  JSONArray.fromObject(materials, jsonConfig));
        }
        log.debug(JSONObject.fromObject(result).toString()); // 查询结果

        return JSONObject.fromObject(result).toString();
    }

    /**
     * 删除素材
     * @param id
     * @return
     */
    public boolean delMaterialById(Integer id){
        return materialMapper.delMaterialById(id) > 0 ? true : false;
    }

    /**
     * 编辑素材
     * @param materialBean
     * @return
     */
     public boolean editMaterial(MaterialBean materialBean){
        return materialMapper.editMaterial(materialBean) > 0 ? true : false;
     }

}
