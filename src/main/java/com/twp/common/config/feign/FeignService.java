//package com.twp.common.config.feign;
//
//
//import com.jayway.jsonpath.JsonPath;
//import com.jayway.jsonpath.ReadContext;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Component;
//import sun.misc.IOUtils;
//
//import javax.annotation.PostConstruct;
//import java.io.IOException;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Component
//public class FeignService {
//
//    @Value("classpath:config/feign-service-http-code/service-code-map.json")
//    Resource fators;
//
//    ReadContext ctx;
//
////    private FeignServiceConfig feignServiceConfig;
////
////    @Data@AllArgsConstructor@NoArgsConstructor
////    public static class FeignServiceConfig{
////        private String desp;
////        private Map<String, Object> mapping;
////    }
//
//    @PostConstruct
//    public void init() throws IOException {
//        String json = new String(IOUtils.readFully(fators.getInputStream(), -1,true));
//        this.ctx = JsonPath.parse(json);
//        //this.feignServiceConfig = JSON.parseObject(json,FeignServiceConfig.class);
//    }
//}
