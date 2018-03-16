//package com.twp.common.aop;
//
//import com.alibaba.fastjson.JSON;
//import com.efivestar.upg.annotation.TraderImplemention;
//import com.efivestar.upg.constant.Constants;
//import com.efivestar.upg.domain.TradeOrder;
//import com.efivestar.upg.domain.TradeOrderTask;
//import com.efivestar.upg.schedule.handler.base.ITaskHandler;
//import com.efivestar.upg.schedule.handler.base.TaskIdentify;
//import com.efivestar.upg.service.TradeOrderService;
//import com.efivestar.upg.vo.ReturnObject;
//import com.efivestar.upg.vo.ReturnRefundObject;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.Optional;
//
///**
// * Created by tanweiping on 17/3/31.
// */
//@Component
//@Aspect
//@Slf4j
//public class ProcessThirdPayReFundNotifyTask {
//
//    //微信的handler
////    @Autowired
////    ITaskHandler weiXinReFundHandler;
//    @Autowired
//    ITaskHandler[] iTaskHandlers;
//
//    @Autowired
//    TradeOrderService tradeOrderService;
//
//    //第三方退款的切点01
//    //适配器
//    @Pointcut("execution(* com.efivestar.upg.adapter.*.refund(..))")
//    private void pointsForAdapter() {
//    }
//
//    //第三方退款的切点02
//    // callback
//    @Pointcut(value = "execution(* com.efivestar.upg.web.callback.*.refund(..))&& args(request,response)",argNames="request,response")
//    private void pointsForCallBack(HttpServletRequest request, HttpServletResponse response) {
//    }
//
//    @Around(argNames = "point,request,response",value = "pointsForCallBack(request,response)")
//    public void UpdateTaskRefundRemoteState(ProceedingJoinPoint point, HttpServletRequest request, HttpServletResponse response){
//
//        try {
//            Object[] args = point.getArgs();
//            point.proceed(args);
//            //String returnstr = point.proceed(args).toString();
//            // success代表对第三方 跟 对业务系统都是成功的
//            //if (returnstr.toLowerCase().equals("success".toLowerCase())) return;
//
//            Optional<TaskIdentify> taskIdentifyOptional = getTaskIdentify(point);
//            if(!taskIdentifyOptional.isPresent()) return;
//
//            TaskIdentify taskIdentify = taskIdentifyOptional.get();
//
//            //处理失败的情况
//            // 查询订单的状态判断是remote还是service的错误中断
//            TradeOrder tradeOrder = tradeOrderService.queryTradeOrderByGwTradeNo(request.getParameter("batch_no"));
//
//            //判断任务类型
////            boolean remoteStatus =  (tradeOrder.getStatus().intValue() == Constants.已退款订单.intValue()
////            || tradeOrder.getStatus().intValue() == Constants.退款异常 )?true:false;
//
//            Optional<ITaskHandler> handlerOptional =  MatchTaskToHandler(tradeOrder,taskIdentify);
//            if (!handlerOptional.isPresent()) return;
//
//            ITaskHandler handler = handlerOptional.get();
//
//            handler.JoinTask(new TradeOrderTask(){
//                {
//                    //看枚举配置中是否需要想第三方请求状态
//                    setStatusRemote(true);
//                    setTradeOrderId(tradeOrder.getId());
//                    Date d = new Date();
//                    setCreateTime(d);
//                    setUpdateTimeService(d);
//                    setUpdateTimeRemote(d);
//                    setExecuteRemote(new Date(d.getTime()+ handler.delayTime));
//                    setExecuteService(new Date(d.getTime() + handler.delayTime));
//                    setIdentify(taskIdentify.getOperation());
//                }
//            });
//
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//    }
//
//    @AfterReturning(returning = "returnObject", pointcut = "pointsForAdapter()")
//    public void JoinPollTaskRefund(JoinPoint joinPoint, ReturnObject returnObject){
//        try {
//            Optional<TaskIdentify> taskIdentifyOptional = getTaskIdentify(joinPoint);
//            // 如果不需要加入任务的话,就退出方法
//            //if (!taskIdentify.isProcessAop()) return;
//           // if (taskRefundList.size()==0) return;
//
//            // 只拿去ReFund的task
//           // TaskIdentify taskIdentify = taskRefundList.get(0);
//            if(!taskIdentifyOptional.isPresent()) return;
//
//            TaskIdentify taskIdentify = taskIdentifyOptional.get();
//
//            //做轮询任务加入
//            ReturnRefundObject returnRefundObject =
//                    JSON.parseObject(returnObject.getReturnData(),ReturnRefundObject.class);
//
//            // 如果任务失败了的话,不加入任务
//            if(!returnRefundObject.getResult().equals(Constants.SUCCESS)) return;
//
//            TradeOrder refundTrade = tradeOrderService.queryTradeOrderByGwTradeNo(returnRefundObject.getGwTradeNo());
//
//            Optional<ITaskHandler> handlerOptional =  MatchTaskToHandler(refundTrade,taskIdentify);
//            if (!handlerOptional.isPresent()) return;
//            // 如果这种方式由用户发起,将加入在callback去
//            // 在callback处理他的returnrefundobject
//            if(taskIdentify.isOperationUpToUser()) return;
//            ITaskHandler handler = handlerOptional.get();
//
//            handler.JoinTask(new TradeOrderTask(){
//                {
//                    //看枚举配置中是否需要想第三方请求状态
//                    setStatusRemote(!taskIdentify.isQueryThird());
//                    setTradeOrderId(refundTrade.getId());
//                    Date d = new Date();
//                    setCreateTime(d);
//                    setUpdateTimeService(d);
//                    setUpdateTimeRemote(d);
//                    setExecuteRemote(new Date(d.getTime()+ handler.delayTime));
//                    setExecuteService(new Date(d.getTime() +2 * handler.delayTime));
//                    setIdentify(taskIdentify.getOperation());
//                }
//            });
//
//        }catch (Exception e){
//            log.error("error:",e.toString());
//        }
//    }
//
//    Optional<TaskIdentify> getTaskIdentify(JoinPoint joinPoint){
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        return Arrays.stream(method.getDeclaringClass().getAnnotation(TraderImplemention.class).taskList())
//                .filter(task ->
//                        task.name().endsWith("ReFund")
//                ).findFirst();
//    }
//
//    Optional<ITaskHandler> MatchTaskToHandler(TradeOrder order,TaskIdentify taskIdentify) {
//        //SaveOrUpdate 一个refundTrade 到refundtask
//        //无应用回调的时候,且returncode 为SUCCESS
//        //需要在加入定时任务数据表
//        return Arrays.stream(iTaskHandlers)
//                .filter(handler ->
//                        handler.getClass()
//                                .getSimpleName().toLowerCase()
//                                .startsWith(
//                                        taskIdentify.name().toLowerCase()
//                                )).findFirst();
//    }
//}
