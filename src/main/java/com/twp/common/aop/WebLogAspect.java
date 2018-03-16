//package com.twp.common.aop;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.efivestar.eep.dto.ResultDTO;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.*;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Arrays;
//
///**
// * Created by tanweiping on 17/8/11.
// */
//@Slf4j
//@Aspect
//@Component
//public class WebLogAspect {
//
//    @Value("${allExceptionMessage}")
//    private String allExceptionMessage;
//
//    ThreadLocal<Long> startTime = new ThreadLocal<>();
//
//    @Pointcut("execution(* com.efivestar.eep.rest.eep.api..*.*(..))")
//    public void webLog(){}
//
//    @Before("webLog()")
//    public void doBefore(JoinPoint joinPoint) throws Throwable {
//        startTime.set(System.currentTimeMillis());
//        // 省略日志记录内容
//        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        // 记录下请求内容
//        log.info("URL : " + request.getRequestURL().toString());
//        log.info("HTTP_METHOD : " + request.getMethod());
//        log.info("IP : " + request.getRemoteAddr());
//        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//    }
//
//    @AfterReturning(returning = "ret", pointcut = "webLog()")
//    public void doAfterReturning(Object ret) throws Throwable {
//// 处理完请求，返回内容
//        log.info("RESPONSE : " + ret);
//        log.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
//    }
//
//    @Around("webLog()")
//    public Object processReturn(ProceedingJoinPoint pjp){
//        Object object;
//        Object object_=null;
//        boolean flag = false;
//        Class returnType = null;
//        try{
//            Signature signature =  pjp.getSignature();
//            returnType = ((MethodSignature) signature).getReturnType();
//            object_ =  pjp.proceed();
//
//        }catch (Throwable e){
//            log.error("ex:"+e.getMessage());
//            if (e.getCause()!=null){
//                if (!StringUtils.isEmpty(e.getCause().getMessage())){
//                    if (returnType.getSimpleName().equals("ResultDTO")){
//                        return ResultDTO.builder().isSuccess(false).message(e.getCause().getMessage()).build();
//                    }
//                    return JSON.toJSONString(ResultDTO.builder().isSuccess(false).message(e.getCause().getMessage()).build());
//                }
//            }else {
//                if (!StringUtils.isEmpty(e.getMessage())){
//                    if (returnType.getSimpleName().equals("ResultDTO")){
//                        return ResultDTO.builder().isSuccess(false).message(e.getMessage()).build();
//                    }
//                    return JSON.toJSONString(ResultDTO.builder().isSuccess(false).message(e.getMessage()).build());
//                }
//            }
//        }
//        ResultDTO resultDTO = null;
//
//        if (object_ instanceof ResultDTO){
//            resultDTO = (ResultDTO) object_;
//        }else  if (object_ instanceof String){
//            try {
//                resultDTO =JSON.parseObject(String.valueOf(object_),ResultDTO.class);
//                flag = true;
//            }catch (Exception e){
//                log.error("ex:"+e.getMessage());
//                return object_;
//            }
//        }else {
//            return object_;
//        }
//
//        if (!resultDTO.getIsSuccess()){
//            String message = resultDTO.getMessage();
//            if (StringUtils.isEmpty(message) ||  message.toLowerCase().contains("Exception".toLowerCase())||message.contains("Connection refused")){
//                resultDTO.setMessage(allExceptionMessage);
//                log.error("出错的日志覆盖");
//            }
//        }
//        if (flag){
//            object = JSON.toJSONString(resultDTO, SerializerFeature.WriteMapNullValue);
//        }else {
//            object = resultDTO;
//        }
//        return object;
//
//    }
//
//
//}
