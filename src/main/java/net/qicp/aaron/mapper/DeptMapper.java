package net.qicp.aaron.mapper;

import net.qicp.aaron.domain.DeptBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author Aaron
 * @Description
 * @Version 1.0
 * @Date 20:26 2019/3/15
 */
@Component
public interface DeptMapper {

    /**
     * 查询全部部门
     * @return
     */
    List<DeptBean> findAll();

}
