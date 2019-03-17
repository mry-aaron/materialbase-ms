package net.qicp.aaron.controller;

import net.qicp.aaron.domain.CompanyBean;
import net.qicp.aaron.domain.MaterialBean;
import net.qicp.aaron.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping("/getstyle")
    public String getStyle(){
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
    public void recordMaterial(@RequestParam("file") MultipartFile file, MaterialBean materialBean, CompanyBean companyBean){
        materialService.recordMaterial(file, materialBean, companyBean);
    }


}
