package net.qicp.aaron.controller;

import net.qicp.aaron.service.DeptService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Aaron
 * @Description
 * @Version 1.0
 * @Date 20:32 2019/3/15
 */
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/findall")
    public String findAll(){
        return JSONArray.fromObject(deptService.findAll()).toString();
    }

}
