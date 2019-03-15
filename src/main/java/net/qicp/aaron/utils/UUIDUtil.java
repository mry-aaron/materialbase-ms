package net.qicp.aaron.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author Aaron
 * @Description 文件名重置工具类
 * @Version 1.0
 * @Date 14:58 2019/3/15
 */
public class UUIDUtil {
    private static Logger log = LoggerFactory.getLogger(UUIDUtil.class);

    /**
     * 生成随机文件名称
     *
     * @return
     */
    public static String generFileName(){
        // 生成随机文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String randFileName = new StringBuilder(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
                .append(new Date().getTime())
                .append(uuid.substring(0,6)).toString();
        log.debug(randFileName + "==========UUIDUtil : generFileName===============");
        return randFileName;
    }

}
