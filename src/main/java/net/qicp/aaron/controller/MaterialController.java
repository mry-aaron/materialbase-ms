package net.qicp.aaron.controller;

import net.qicp.aaron.domain.CompanyBean;
import net.qicp.aaron.domain.MaterialBean;
import net.qicp.aaron.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author Aaron
 * @Description
 * @Version 1.0
 * @Date 20:37 2019/3/17
 */
@RestController
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @RequestMapping("/getcompany")
    public String getCompany(){
        return materialService.getCompany();
    }

    @RequestMapping("/getstyle")
    public String getStyle(HttpServletRequest request){
        request.getSession().setAttribute("recordResult", null);
        return materialService.getStyle();
    }

    @RequestMapping("/gettype")
    public String getType(){
        return materialService.getType();
    }

    @RequestMapping("/getmedia")
    public String getMedia(){
        return materialService.getMedia();
    }

    @RequestMapping("/record")
    public void recordMaterial(@RequestParam("file") MultipartFile file, MaterialBean materialBean, CompanyBean companyBean,
                               HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        // 录入前清空录入结果
        request.getSession().setAttribute("recordResult", null);
        if(materialService.recordMaterial(file, materialBean, companyBean)){
            // 录入成功
            request.getSession().setAttribute("recordResult", "success");
        } else {
            // 录入失败
            request.getSession().setAttribute("recordResult", "error");
        }
        out.print("<script type='text/javascript'>");
        out.print("location.href='/record.html';");
        out.print("</script>");
    }

    @RequestMapping("/findmaterial")
    public String findMaterialList(MaterialBean materialBean){
        return materialService.findMaterial(materialBean);
    }

    @RequestMapping("/delmaterial")
    public boolean delMaterial(@RequestParam("id") Integer id){
        return materialService.delMaterialById(id);
    }

    @RequestMapping("/editmaterial")
    public boolean editMaterial(MaterialBean materialBean){
        return materialService.editMaterial(materialBean);
    }

}
