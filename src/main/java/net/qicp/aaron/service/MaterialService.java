package net.qicp.aaron.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.qicp.aaron.component.FileUploadThread;
import net.qicp.aaron.domain.CompanyBean;
import net.qicp.aaron.domain.MaterialBean;
import net.qicp.aaron.mapper.MaterialMapper;
import net.qicp.aaron.utils.DateUtil;
import net.qicp.aaron.utils.UUIDUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

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
    @Value("${file.material.host}")
    private String host; // 素材服务器IP

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
            } else if(suffix.matches("^\\.(mp4|avi)$")) { // 判断是否为一个视频
                filePath = filePathVideo;
            } else {
                return result;
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

            // 处理结果
            PageInfo<MaterialBean> pageInfo = new PageInfo<>(materials);
            result.put("code",  0);
            result.put("msg",   "");
            result.put("count", pageInfo.getTotal());
            result.put("data",  JSONArray.fromObject(materials, DateUtil.jsonConfig));
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

    /**
     * 首页图片轮播（点赞量排名前5的素材）
     * @return
     */
    public String homeBannerData(){
        // type - 1：图片  2：视频
        return JSONArray.fromObject(materialMapper.getMaterialTop5(1)).toString();
     }

    /**
     * 获取全部素材
      * @return
     */
    public String getAllSM(MaterialBean materialBean){
        HashMap<String, Object> result = null;
        PageHelper.startPage(materialBean.getPage(), materialBean.getLimit());
        // type - 1：图片  2：视频
        materialBean.setSmTypeId(1);
        List<MaterialBean> lists = materialMapper.getAllSM(materialBean);
        if(lists != null && lists.size() > 0){
            result = new HashMap<>();
            PageInfo<MaterialBean> pageInfo = new PageInfo<>(lists);
            result.put("code", 0);
            result.put("data", lists);
            result.put("total", pageInfo.getTotal());
        }

        return JSONObject.fromObject(result).toString();
    }

    /**
     * 文件下载
     * @param fileName
     * @param format
     * @param id
     * @param response
     * @throws Exception
     */
    public void fileDownload(String fileName, String format, Integer id, HttpServletResponse response) throws Exception {

        // 判断是图片还是视频
        String filePath = host;
        if(format.matches("^jpg|png|gif$")) {
            filePath += filePathPicture;
        } else if(format.matches("^mp4|avi$")) {
            filePath += filePathVideo;
        }
        format = "." + format;

        //创建url;
        URL url = new URL(filePath + fileName + format);
        log.debug(filePath+fileName+format);
        //创建url连接;
        HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
        //链接远程服务器;
        urlconn.connect();
        try(
                BufferedInputStream bis = new BufferedInputStream(urlconn.getInputStream());
                ServletOutputStream sos = response.getOutputStream()
        ){
            // 指定允许其他域名访问
            response.addHeader("Access-Control-Allow-Origin","*");
            //客户使用保存文件的对话框：
            response.setHeader("Content-disposition","inline;filename="+fileName+format);
            //通知客户文件的MIME类型：
            response.setContentType("application/octet-stream exe;charset=gb2312");
            IOUtils.copy(bis, sos);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            urlconn.disconnect();
        }

        // 下载记录到数据库(下载次数加1)
        materialMapper.updateDownloadCount(id);
    }

    /**
     * 更新下载次数
     * @param id
     * @return
     */
    public String getDownloadCount(Integer id){
        return materialMapper.getDownload(id);
    }

    /**
     * 获取素材详情
     * @param id
     * @return
     */
    public String getDetails(Integer id){
        // 根据编号获取素材详细
        MaterialBean details = materialMapper.getDetails(id);
        // 获取下载次数前十的素材
        LinkedList<MaterialBean> downloadTop10 = materialMapper.getMaterialDownloadTop10();
        // 将根据编号查询的素材添加到集合一起返回页面
        downloadTop10.addFirst(details);

        return JSONArray.fromObject(downloadTop10, DateUtil.jsonConfig).toString();
    }

    /**
     * 获取推荐素材（浏览次数前五的素材）
     * @return
     */
    public String getRecommendMaterial(){
        // type - 1：图片  2：视频
        return JSONArray.fromObject(materialMapper.getMaterialTop5(1)).toString();
    }

}
