package com.sxt.aop;

import com.sxt.cfg.BaseContextHandler;
import com.sxt.event.LogEvent;
import com.sxt.utils.IpHelper;
import com.sxt.utils.RequestData;
import com.sxt.utils.ResponseData;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

import static com.sun.tools.javac.jvm.ByteCodes.ret;

/****************************************************************************************
 实现AOP的切面主要有以下几个要素：

 使用@Aspect注解将一个java类定义为切面类
 使用@Pointcut定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等。
 根据需要在切入点不同位置的切入内容
 使用@Before在切入点开始处切入内容
 使用@After在切入点结尾处切入内容
 使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
 使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
 使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
 使用@Order(i)注解来标识切面的优先级。i的值越小，优先级越高
 ****************************************************************************************/

/****************************************************************************************
 执行日志顺序：

 2017-10-19 20:22:35.810  INFO 9014 --- [nio-8350-exec-1] WebLogHeadAspect    : (Order(1))************** (doBefore) URL : http://localhost:8350/simple/1
 2017-10-19 20:22:35.810  INFO 9014 --- [nio-8350-exec-1] WebLogHeadAspect    : (Order(1))************** (doBefore) HTTP_METHOD : GET
 2017-10-19 20:22:35.810  INFO 9014 --- [nio-8350-exec-1] WebLogHeadAspect    : (Order(1))************** (doBefore) IP : 127.0.0.1
 2017-10-19 20:22:35.811  INFO 9014 --- [nio-8350-exec-1] WebLogHeadAspect    : (Order(1))************** (doBefore) CLASS_METHOD : com.springms.cloud.controller.SimpleProviderUserAopWebLogController.findById
 2017-10-19 20:22:35.811  INFO 9014 --- [nio-8350-exec-1] WebLogHeadAspect    : (Order(1))************** (doBefore) ARGS : [1]
 2017-10-19 20:22:35.811  INFO 9014 --- [nio-8350-exec-1] com.springms.cloud.aop.WebLogFiveAspect        : (Order(5))============== (doBefore) URL : http://localhost:8350/simple/1
 2017-10-19 20:22:35.811  INFO 9014 --- [nio-8350-exec-1] com.springms.cloud.aop.WebLogFiveAspect        : (Order(5))============== (doBefore) HTTP_METHOD : GET
 2017-10-19 20:22:35.811  INFO 9014 --- [nio-8350-exec-1] com.springms.cloud.aop.WebLogFiveAspect        : (Order(5))============== (doBefore) IP : 127.0.0.1
 2017-10-19 20:22:35.811  INFO 9014 --- [nio-8350-exec-1] com.springms.cloud.aop.WebLogFiveAspect        : (Order(5))============== (doBefore) CLASS_METHOD : com.springms.cloud.controller.SimpleProviderUserAopWebLogController.findById
 2017-10-19 20:22:35.811  INFO 9014 --- [nio-8350-exec-1] com.springms.cloud.aop.WebLogFiveAspect        : (Order(5))============== (doBefore) ARGS : [1]
 Hibernate: select user0_.id as id1_0_0_, user0_.age as age2_0_0_, user0_.balance as balance3_0_0_, user0_.name as name4_0_0_, user0_.username as username5_0_0_ from user user0_ where user0_.id=?
 2017-10-19 20:22:35.853  INFO 9014 --- [nio-8350-exec-1] com.springms.cloud.aop.WebLogFiveAspect        : (Order(5))============== (doAfterReturning) RESPONSE : User@1249202
 2017-10-19 20:22:35.853  INFO 9014 --- [nio-8350-exec-1] com.springms.cloud.aop.WebLogFiveAspect        : (Order(5))============== (doAfterReturning) SPEND TIME : 42
 2017-10-19 20:22:35.853  INFO 9014 --- [nio-8350-exec-1] WebLogHeadAspect    : (Order(1))************** (doAfterReturning) RESPONSE : User@1249202
 2017-10-19 20:22:35.853  INFO 9014 --- [nio-8350-exec-1] WebLogHeadAspect    : (Order(1))************** (doAfterReturning) SPEND TIME : 43

 总结：doBefore 方法先从优先级到低优先级依次执行完，然后 doAfterReturning 方法从低优先级到高优先级依次执行完；也就是进入从高到低，出来从低到高；
 ****************************************************************************************/


/**
 * Web层日志切面。
 */
@Aspect
@Order(5)
@Component
public class WebLogFiveAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApplicationContext applicationContext;

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    private static final String PRE_TAG = "(Order(5))============== ";

    @Pointcut("execution(public * com.sxt.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        StringBuffer logs = new StringBuffer();
        String param = Arrays.toString(joinPoint.getArgs());
        logs.append(System.getProperty("line.separator") + PRE_TAG + "(doBefore) URL : " + request.getRequestURL().toString() + System.getProperty("line.separator"));

        logs.append(PRE_TAG + "(doBefore) User_Mobile : " + BaseContextHandler.getMobile() + System.getProperty("line.separator"));
        logs.append(PRE_TAG + "(doBefore) User_ID : " + BaseContextHandler.getUserID() + System.getProperty("line.separator"));
        logs.append(PRE_TAG + "(doBefore) HTTP_METHOD : " + request.getMethod() + System.getProperty("line.separator"));
        logs.append(PRE_TAG + "(doBefore) IP : " + IpHelper.getIpAddr(request) + System.getProperty("line.separator"));
        logs.append(PRE_TAG + "(doBefore) CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + System.getProperty("line.separator"));

        logs.append(PRE_TAG + "(doBefore) ARGS : " + param + System.getProperty("line.separator"));

        RequestData requestData = new RequestData();
        requestData.setHead(logs.toString());
        requestData.setUrl(request.getRequestURL().toString());
        requestData.setParam(param);
        requestData.setIp(BaseContextHandler.getIp());

        // 记录下请求内容
        logger.debug(logs.toString());
        BaseContextHandler.setRequestDate(requestData);
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public String doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.debug(PRE_TAG + "(doAfterReturning) SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));


            logger.debug(PRE_TAG + "(doAfterReturning) RESPONSE : " + ret);
            if (applicationContext != null) {

                if (ret instanceof ResponseData) {

                applicationContext.publishEvent(new LogEvent(this, BaseContextHandler.getRequestDate(), (ResponseData) ret, "doAfterReturning"));
                 }else{
                    applicationContext.publishEvent(new LogEvent(this, BaseContextHandler.getRequestDate(), null, "doAfterReturning"));

                }
            }

        return ret.toString();
    }
}