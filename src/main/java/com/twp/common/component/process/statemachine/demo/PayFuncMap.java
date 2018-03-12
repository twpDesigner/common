package com.twp.common.component.process.statemachine.demo;

import com.twp.common.component.process.statemachine.annotation.IMapFunction;
import com.twp.common.component.process.statemachine.annotation.MapFunctionListener;
import com.twp.common.component.process.statemachine.domain.StateNode;
import com.twp.common.tuple.ResultDTO;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/*
支付相关的状态机
 */
@Component
@Slf4j
@Builder
public class PayFuncMap implements IMapFunction {

    /*
    状态入口：交易请求进入
     */
    @MapFunctionListener(name = "交易请求检查",group = "deal",code = "requestEnter")
    public StateNode dealRequestEnter(StateNode<HttpServletRequest, ResultDTO> currentNode){
        log.info("交易请求进入");
        /*
        注意此处声明是servlet的请求体，最原始请求数据，便于统一处理
         */
        log.info(currentNode.getRequestDto().toString());
        /*
        跟据当前节点的请求入参数去匹配子状态机
         */
        /*
        这里加入一个子检查状态机,签名校验,ip检查，身份验证，交易能力，匹配请求成功或失败
         */

        /*
        拿到子检查状态机的结果回归主线状态机匹配
         */
        boolean 校验成功 = true;
        if(校验成功){
            return StateNode.builder().nodeCode("business_match").build();
        }
        //否则进入交易异常
        return StateNode.builder().nodeCode("deal_exception").build();
    }

    /*
    用于匹配交易的实体
     */
    static class BusinessInfo{}

    @MapFunctionListener(name = "匹配具体交易",group = "business",code = "match")
    public StateNode businessMatch(StateNode<BusinessInfo,ResultDTO> currentNode){
        log.info("business_match");

        log.info(currentNode.getRequestDto().toString());

        /*
        根据请求体去匹配 具体的第三方的支付或退款或账单其他操作，等等
        可以依据实际情况选择是否分离子状态机
         */
        return StateNode.builder().response(ResultDTO.builder().message("测试终点状态").build()).build();
    }
    @MapFunctionListener(name = "支付宝支付",group = "alipay",code = "o2oPay")
    public StateNode alipayO2oPay(StateNode currentNode){
        log.info("alipayO2oPay");
        // 支付宝支付 子状态机
        /*
        1.证书
        2.特有参数组装
        3.发起支付请求
        4.判断请求成功与否
        5.加入订单(等待第三方的支付结果回调，此处也许一个处理)
         */
        return StateNode.builder().response(ResultDTO.builder().message("deal_end").build()).build();
    }

    @MapFunctionListener(name = "支付宝退款",group = "alipay",code = "o2orenfund")
    public StateNode alipayO2oRefund(StateNode currentNode){
        log.info("alipayO2oRefund");
        log.info("alipayO2oPay");
        // 支付宝支付 子状态机
        /*
        1.证书
        2.特有参数组装
        3.发起退款请求
        4.判断请求成功与否
        5.加入订单(等待第三方的支付结果回调，此处也许一个处理)
        6.如果第三方不支持回调，此处需要加入一个异步轮询任务池
         */
        return StateNode.builder().response(ResultDTO.builder().message("deal_end").build()).build();
    }


    @MapFunctionListener(name = "交易结束",group = "deal",code = "end")
    public StateNode dealEnd(StateNode currentNode){
        log.info("test02");
        return StateNode.builder().response(ResultDTO.builder().message("测试终点状态").build()).build();
    }

    @MapFunctionListener(name = "交易异常",group = "deal",code = "exception")
    public StateNode dealException(StateNode currentNode){
        log.info("test03");
        //测试异常
        int ss = 1/0;
        return StateNode.builder().nodeCode("t_test02").build();
    }
}
