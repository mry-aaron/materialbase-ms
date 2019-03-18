package net.qicp.aaron.component;

import net.qicp.aaron.service.MaterialService;
import net.qicp.aaron.utils.FileUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @Author Aaron
 * @Description 文件上传线程类
 * @Version 1.0
 * @Date 9:17 2019/3/18
 */
@Component
public class FileUploadThread{
    private Logger log = LoggerFactory.getLogger(MaterialService.class);

    /**
     * 文件上传
     * @return
     */
    @Async("myTaskAsyncPool")
    public void uploadFile(String filePath, String fileName, InputStream is) throws InterruptedException{
        String fPath = FileUploadUtil.upload(filePath, fileName, is);
        log.debug(fPath);
    }
}
