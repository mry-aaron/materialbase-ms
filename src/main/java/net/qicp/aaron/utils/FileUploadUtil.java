package net.qicp.aaron.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * @Author Aaron
 * @Description 文件上传工具类
 * @Version 1.0
 * @Date 14:13 2019/3/15
 */
public class FileUploadUtil {
    private static Logger log = LoggerFactory.getLogger(FileUploadUtil.class);

    private static String   host        = "192.168.0.125";      // 服务器IP
    private static Integer  port        = 22;                   // 服务器端口
    private static String   username    = "root";               // 用户名
    private static String   password    = "root123";            // 密码
    private static String   basePath    = "/opt/file-server/";   // 基础路径
    private static String   filePath    = "images/upload/heads/";     // 文件路径

    /**
     * 文件上传
     *
     * @param fileName
     * @param inputStream
     * @return
     */
    public static String upload(String fileName, InputStream inputStream) {
        ChannelSftp sftp = getSFTP();
        try {
            sftp.cd(basePath + filePath);
            sftp.put(inputStream, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder imgURL = new StringBuilder("http://");
        imgURL.append(host);
        imgURL.append("/" + filePath);
        imgURL.append(fileName);
        return imgURL.toString();
    }

    /**
     * 开启 SFTP连接
     *
     * @return
     */
    private static ChannelSftp getSFTP(){
        ChannelSftp  sftp  = null;
        try {
            JSch jsch = new JSch();
            //获取sshSession
            Session sshSession =jsch.getSession(username, host, port);
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            //严格主机密钥检查
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            //开启SSHSession链接
            sshSession.connect();
            //获取sftp通道
            Channel channel = sshSession.openChannel("sftp");
            //开启
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sftp;
    }




    public String saveToLocal(){
        /*// 获取图片信息
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        // 将图片上传到工程临时目录中
        File tmpFile = new File(localPath);
        File fileSource = new File(tmpFile, randFileName);
        if (!tmpFile.exists()) tmpFile.mkdirs();
        file.transferTo(fileSource);*/
        return null;
    }

}
