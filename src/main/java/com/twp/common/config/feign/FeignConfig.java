//package com.twp.common.config.feign;
//
//import com.efivestar.eep.config.properties.feignservice.FeignService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import feign.Logger;
//import feign.codec.Decoder;
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
//import org.springframework.cloud.netflix.feign.support.SpringDecoder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//
///**
// * Created by tanweiping on 17/8/17.
// */
//@Configuration
//public class FeignConfig {
//    @Bean
//    Logger.Level feignLoggerLevel() {
//        return Logger.Level.FULL;
//    }
//
//    @Autowired
//    FeignService feignService;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
////    @Bean
////    Request.Options feignOptions() {
////        return new Request.Options(
////                /**connectTimeoutMillis**/
////                20 * 1000,
////                /** readTimeoutMillis **/
////                20 * 1000
////        );
////    }
//
//    @Bean
//    public Decoder feignDecoder() {
//        HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(objectMapper);
//        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
//        return new MyFeignDecoder(new SpringDecoder(objectFactory),feignService);
//    }
////
////    public ObjectMapper customObjectMapper(){
////        ObjectMapper objectMapper = new ObjectMapper();
////        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
////        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
////        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
////        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
////        return objectMapper;
////    }
//}
