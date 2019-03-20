package net.qicp.aaron.utils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @Author Aaron
 * @Description 日期格式处理工具类
 * @Version 1.0
 * @Date 16:40 2019/3/20
 */
public class DateUtil {

    public static JsonConfig jsonConfig = null;
    static {
        // 处理Timestamp日期格式
        jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonValueProcessor() {
            private String format = "yyyy-MM-dd HH:mm:ss";
            public Object processArrayValue(Object value, JsonConfig config) { return process(value); }
            public Object processObjectValue(String arg0, Object value, JsonConfig config) { return process(value); }
            private Object process(Object value) {
                if (value instanceof Timestamp) {
                    SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.UK);
                    return sdf.format(((Timestamp) value).getTime());
                }
                return value == null ? "" : value.toString();
            }
        });
    }
}
