//package com.twp.common.config;
//
//import com.efivestar.eep.exception.AppException;
//import feign.FeignException;
//import feign.Response;
//import lombok.extern.slf4j.Slf4j;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.StringUtils;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * Created by tanweiping on 17/8/2.
// */
//@Slf4j
//@Configuration
//public class BizExceptionFeignErrorDecoder implements feign.codec.ErrorDecoder {
//
//
//    @Override
//    public Exception decode(String methodKey, Response response) {
//        if(response.status() >= 400 && response.status() <= 500){
//            String message = "";
//            String json = "";
//            try {
//                json  = inputStream2String(response.body().asInputStream(),"utf-8");
//                JSONObject jsonObject = new JSONObject(json);
//
//                 /*
//                {"timestamp":1510801757548,"status":500,"error":"Internal Server Error","exception":"com.netflix.zuul.exception.ZuulException","message":"GENERAL"}
//                 */
//                if (
//                        response.status()==500
//                        &&
//                                (!jsonObject.isNull("exception"))
//                        &&
//                        jsonObject.getString("exception")
//                                        .equals("com.netflix.zuul.exception.ZuulException")
//                        ){
//                    log.error("feign调用服务，服务内部网关路由错误");
//                    return new Exception("服务器异常，请稍后重试");
//                }
//
//                message  = jsonObject.getString("message");
//                JSONObject jsonObject_msg = new JSONObject(message);
//                //boolean isSuccess = jsonObject.getBoolean("isSuccess");
//                //String code = jsonObject.getString("code");
////                if (!isSuccess&&code.equals("0")){
////                    return new AccessDeniedException(jsonObject_msg.getString("message"));
////                }
//                return new AppException(jsonObject_msg.getString("code"),jsonObject_msg.getString("message"));
//                //return new AppException()
//            } catch (IOException e) {
//                e.printStackTrace();
//                if (StringUtils.isEmpty(message)){
//                    message="连接服务器出错";
//                }
//            } catch (JSONException e) {
//                if (StringUtils.isEmpty(message)){
//                    message="连接服务器出错";
//                }
//                e.printStackTrace();
//                //message = json;
//            }
//            //return new HystrixBadRequestException(message);
//            return new Exception(message);
//        }
//        return feign.FeignException.errorStatus(methodKey, response);
//    }
//
//    public static String inputStream2String(InputStream is, String charset) {
//        ByteArrayOutputStream baos = null;
//
//        try {
//            baos = new ByteArrayOutputStream();
//            int i = -1;
//            while ((i = is.read()) != -1) {
//                baos.write(i);
//            }
//            return baos.toString(charset);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (null != baos) {
//                try {
//                    baos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                baos = null;
//            }
//        }
//
//        return null;
//    }
//}
