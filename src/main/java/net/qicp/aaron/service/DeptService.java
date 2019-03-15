package net.qicp.aaron.service;

import net.qicp.aaron.domain.DeptBean;
import net.qicp.aaron.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Aaron
 * @Description
 * @Version 1.0
 * @Date 20:30 2019/3/15
 */
@Service
public class DeptService {

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 查询全部部门
     *
     * @return
     */
    public List<DeptBean> findAll(){
        return deptMapper.findAll();
    }
}
