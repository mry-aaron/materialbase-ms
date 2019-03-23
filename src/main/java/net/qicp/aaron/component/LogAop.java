package net.qicp.aaron.component;

import net.sf.json.JSONArray;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author Aaron
 * @Description AOP 日志记录
 * @Version 1.0
 * @Date 14:42 2019/3/23
 */
@Aspect
@Component
public class LogAop {
    private static final Logger log = LoggerFactory.getLogger(LogAop.class);

    // ThreadLocal保证不受其他线程影响，用于记录接口响应时间
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
    // 切点， 每一个controller请求方法
    @Pointcut("execution(public * net.qicp.aaron.controller..*.*(..))")
    public void pointCut(){ }

    @Before(value = "pointCut()")
    public void doBefore(JoinPoint joinPoint){
        // 获取请求request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取请求内容
        String requestURL = request.getRequestURL().toString();
        String method = request.getMethod();
        // Proxy-Client-IP、WL-Proxy-Client-IP、HTTP_CLIENT_IP、HTTP_X_FORWARDED_FOR
        String remoteIP = request.getHeader("x-forwarded-for");
        log.debug("请求URL："+requestURL+" ==== 请求方式："+method+" ==== 请求远程IP："+remoteIP);
        // 记录具体类、方法及参数
        String typeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.debug("Class："+typeName+" === Method："+methodName+" === Args："+ JSONArray.fromObject(parameterMap).toString());
        // 记录请求接口开始时间
        threadLocal.set(System.currentTimeMillis());
    }

    @AfterReturning(pointcut = "pointCut()",returning = "returnMsg")
    public void doAfterRerurn(JoinPoint joinPoint,Object returnMsg){
        //记录一下接口响应时间
        Long reponseTime = System.currentTimeMillis() - threadLocal.get();
        log.debug("接口响应时间(毫秒) ==> "+reponseTime);
        //删除threadLocal变量副本
        threadLocal.remove();
    }

}
