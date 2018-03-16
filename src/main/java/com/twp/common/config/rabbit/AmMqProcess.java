//package com.twp.common.config.rabbit;
//
//import com.alibaba.fastjson.JSON;
//import com.efivestar.eep.domain.primary.eep.appmodel.goods.Category;
//import com.efivestar.eep.domain.primary.eep.common.MessageNotify;
//import com.efivestar.eep.dto.XshPushDto;
//import com.efivestar.eep.repository.primary.eep.common.MessageNotifyRepository;
//import com.efivestar.eep.repository.primary.eep.common.MessageTypeRepository;
//import com.efivestar.eep.service.eep.CategoryService;
//import com.efivestar.eep.service.eep.accept.CategoryGroupAttrService;
//import com.efivestar.eep.service.eep.common.MessageNotifyService;
//import com.google.gson.reflect.TypeToken;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Slf4j
//@Component
//public class AmMqProcess {
//
//    @Autowired
//    CategoryService categoryService;
//
//    @Autowired
//    CategoryGroupAttrService categoryGroupAttrService;
//
//
//    @Autowired
//    MessageNotifyService messageNotifyService;
//
//    @Autowired
//    MessageNotifyRepository messageNotifyRepository;
//
//    @Autowired
//    MessageTypeRepository messageTypeRepository;
//
//    @RabbitListener(queues = MqQueueEnum.PRODUCTCATEGORYOUT)
//    public void onMessageCategotyout(String msg) throws Exception {
//        log.info("receive msg : " +msg); ;
//
//        XshPushDto<Category> dto = JSON.parseObject(
//                msg,
//                new TypeToken<XshPushDto<Category>>() {
//                }.getType()
//        );
//        try {
//            log.info(dto.toString());
//            categoryService.save(dto.getItems());
//        }catch (Exception e){
//            log.error("receive msg:"+e.toString());
//            throw new Exception("接收处理小上海的工业分类数据失败");
//        }
//    }
//
////    @RabbitListener(queues = MqQueueEnum.ATTRIBUTEGROUPOUT)
////    public void onMessageAttributeOut( String msg) throws Exception {
////        log.info("receive msg : " +msg); ;
////
////        XshPushDto<CategoryGroupAttr> dto = JSON.parseObject(
////                msg,
////                new TypeToken<XshPushDto<CategoryGroupAttr>>() {
////                }.getType()
////        );
////        try {
////            log.info(dto.toString());
////            //categoryService.save(dto.getItems());
////            categoryGroupAttrService.save(dto.getItems());
////        }catch (Exception e){
////            log.error("receive msg:"+e.toString());
////            throw new Exception("接收处理小上海的属性组数据失败");
////        }
////
////    }
//
//
//    /*
//    编号|字段名|说明|类型|格式
//    ---|---|---|---|---
//    1|disOrderSN|配送订单号|String|C123456
//    2|changeDescription|变更描述|String|
//    3|messageType|消息类型|String|
//    4|customerMobile|顾客手机号|String|11111111
//    5|channelCode|渠道编码|String|10表示集家、12表示圆圈
//
//
//    ###### 订单状态描述对照表
//
//    消息类型|变更描述
//    ---|---
//    AlreadyPaid|已支付
//    Finished|订单完成
//    AlreadyShipped|已发货
//    Refunded|退款成功
//    RefundFailure|退款失败
//    NeedBack|退货需要回寄地址
//    ReturnedGoods|退货成功
//    ReturnGoodsFailure|退货失败
//     */
//    @Data
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class MqOrderMessage{
//        private String account;
//        private String disOrderSN;
//        private String changeDescription;
//        private String messageType;
//        private String customerMobile;
//        private String channelCode;
//        /*
//        "customerName"，"skuName"
//         */
//        private String customerName;
//
//        private List<NotifyOtherSystemProductDTO> productList;
//    }
//    @Data@AllArgsConstructor@NoArgsConstructor
//    public static class NotifyOtherSystemProductDTO{
//        private String skuName;
//        private Integer quantity;
//    }
//
//    @RabbitListener(queues = MqQueueEnum.ORDERSTATUSOUT12)
//    public void onMessageOrderStatus( String msg) throws Exception {
//        log.info("receive msg : " +msg); ;
//
//        MqOrderMessage mqOrderMessage = JSON.parseObject(msg,MqOrderMessage.class);
//        if(mqOrderMessage.getMessageType().toLowerCase().contains("ReturnedGoods".toLowerCase())){
//            try {
//                log.info(mqOrderMessage.toString());
//                //退单提醒
//                Map<String,Object> content = new HashMap<String,Object>(){
//                    {
//                        put("content", "配送单订单异常:"+mqOrderMessage.getDisOrderSN());
//                    }
//                };
//                int allNum = 0;
//                String name = "";
//                List<NotifyOtherSystemProductDTO> productList = mqOrderMessage.getProductList();
//                if (productList!=null&&productList.size()>0){
//                    allNum = productList.parallelStream()
//                            .map(o->o.getQuantity()).reduce(0,(o1,o2)->o1+o2).intValue();
//                    name = productList.get(0).getSkuName();
//                }
//                String contentTuple = String.valueOf(
//                        name+" "+mqOrderMessage.getCustomerName()+" "+mqOrderMessage.getCustomerMobile());
//
//                content.put("content",contentTuple);
//
//                int modelType = 5;
//
//                MessageNotify messageNotify_ = messageNotifyRepository.findAllByCallbackAndType(modelType);
//
//                messageNotify_.setTitleTuple(
//                        JSON.toJSONString(
//                                new HashMap(){
//                                    {
//                                        put("title","退货提醒");
//                                    }
//                                }
//                        )
//                );
//                messageNotify_.setContentTuple(
//                        JSON.toJSONString(
//                                new HashMap(){
//                                    {
//                                        putAll(content);
//                                    }
//                                }
//                        )
//                );
//                messageNotify_.setReceiveIds(mqOrderMessage.getAccount());
////                        if (StringUtils.isEmpty(messageNotify_.getMessageType())){
////                            messageNotify_.setMessageType("news");
////                        }
//                messageNotify_.setMessageType("news");
//                //发起站内消息
//                messageNotifyService.pushMessage(
//                        messageNotify_,null
//                );
//
//            }catch (Exception e){
//                log.error("receive msg:"+e.toString());
//                throw new Exception("接收处理小上海的订单变化的消息数据失败");
//            }
//        }
//
//    }
//}
