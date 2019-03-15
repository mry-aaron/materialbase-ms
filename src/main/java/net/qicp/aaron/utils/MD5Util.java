package net.qicp.aaron.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @Author Aaron
 * @Description MD5加密工具类
 * @Version 1.0
 * @Date 21:51 2019/3/14
 */
public class MD5Util {

    /**
     * 加密字符串
     *
     * @param str
     *      加密前按照指定规则将字符串进行位移，提高安全性
     * @return
     */
    public static String getMD5String(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            /*
                digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
                BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
                一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
             */
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
