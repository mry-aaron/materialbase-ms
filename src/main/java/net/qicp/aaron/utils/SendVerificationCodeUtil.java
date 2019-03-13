package net.qicp.aaron.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Aaron
 * @Description 发送短信验证码工具类
 * @Version 1.0
 * @Date 20:57 2019/3/13
 */
public class SendVerificationCodeUtil {

    /**
     * 定义常量数据
     */
    private static final String  DEF_CHATSET         = "UTF-8";
    private static final String  METHOD_GET          = "GET";
    private static final String  METHOD_POST         = "POST";
    private static final Integer DEF_CONN_TIMEOUT    = 30000;
    private static final Integer DEF_READ_TIMEOUT    = 30000;
    private static final String  TPL_ID              = "141401";
    private static final String  INTERFACE_URL       = "http://v.juhe.cn/sms/send";
    private static final String  APPKEY              = "ea1ce1c50ef44c6c0da06733b304530a";

    /**
     * 发送短信
     *
     * @param telephone
     * @param tplValue
     * @param dtype
     * @return
     */
    public static String sendShortMessage(String telephone, String tplValue, String dtype) {
        // 请求结果字符串
        String result = null;
        try {
            //请求参数
            Map params = new HashMap();
            params.put("mobile", telephone);    //接收短信的手机号码
            params.put("tpl_id", TPL_ID);       //短信模板ID
            params.put("tpl_value", tplValue); //变量名和变量值对（验证码）
            params.put("key", APPKEY);          //应用APPKEY
            params.put("dtype", dtype);         //返回数据的格式xml或json，默认json
            // 发送请求
            result = net(INTERFACE_URL, params, METHOD_GET);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 创建连接发送请求
     *
     * @param req_url
     * @param params
     * @param method
     * @return 网络请求字符串
     * @throws Exception
     */
    private static String net(String req_url, Map params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals(METHOD_GET)) {
                req_url = req_url + "?" + urlencode(params);
            }
            URL url = new URL(req_url);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals(METHOD_GET)) {
                conn.setRequestMethod(METHOD_GET);
            } else {
                conn.setRequestMethod(METHOD_POST);
                conn.setDoOutput(true);
            }
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.connect();
            if (params != null && method.equals(METHOD_POST)) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {

                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    /**
     * Map型转为请求参数型
     *
     * @param data
     * @return
     */
    private static String urlencode(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", DEF_CHATSET)).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
