//package com.twp.common.config.feign;
//
//import com.efivestar.eep.config.properties.feignservice.FeignService;
//import com.efivestar.eep.dto.ResultDTO;
//import feign.Response;
//import feign.codec.Decoder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.netflix.feign.support.ResponseEntityDecoder;
//import org.springframework.util.StringUtils;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//
//@Slf4j
//public class MyFeignDecoder extends ResponseEntityDecoder {
//
//    private FeignService feignService;
//
//    public MyFeignDecoder(Decoder decoder) {
//        super(decoder);
//    }
//
//    public MyFeignDecoder(Decoder decoder, FeignService feignService) {
//        super(decoder);
//        this.feignService=feignService;
//    }
//
//    public Object decode(Response response, Type type) throws IOException {
//        Object o = super.decode(response,type);
//        if (o instanceof ResultDTO){
//            ResultDTO o_ = (ResultDTO)o;
//            if (!o_.getIsSuccess()){
//                try {
//                    String eepMessage =  this.feignService.getCtx()
//                            .read("$.service.mapping.mapCodeMessage."+o_.getCode()+".eepMessage");
//                    String sourceMessage =  this.feignService.getCtx()
//                            .read("$.service.mapping.mapCodeMessage."+o_.getCode()+".sourceMessage");
//                    if (!StringUtils.isEmpty(eepMessage)){
//                        o_.setMessage(eepMessage);
//                    }else if(!StringUtils.isEmpty(eepMessage)){
//                        o_.setMessage(sourceMessage);
//                    }
//                    return o_;
//                }catch (Exception e){
//                    log.error("e:",e.getMessage());
//                }
//            }
//        }
//        return o;
//    }
//}
