package com.twp.common.component.process.statemachine.annotation;

import com.twp.common.component.process.statemachine.domain.StateNode;
import com.twp.common.component.process.statemachine.domain.iinterface.IMapRouterProcessStateNodeFunc;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/*
当不使用mapfunction的形式的时候，使用此类处理注解对象生成相应的mapfunction
 */
@Slf4j
@Component
@Scope("prototype")
@Data
public class MapFunctionListenerProcess<T extends StateNode,Container extends Map<T,IMapRouterProcessStateNodeFunc<T>>>
        implements IMapFunctionListenerProcess{
    @Override
    public Container handler(IMapFunction iMapFunction) {
        /*
        mapFunction的容器，
        使用线程安全的map容器
         */
        Container container =(Container) new ConcurrentHashMap<T,IMapRouterProcessStateNodeFunc<T>>();
        Optional.ofNullable(
                iMapFunction
        ).ifPresent(
                existMapFunction->{
                    try{
                        //通过反射拿到实例
                        Class clazz =  existMapFunction.getClass();
                        Method[] methods = clazz.getMethods();
                        if (methods.length==0) return;
                        //1.过滤非特殊处理的注解方法
                        //2.TreeSet做排重操作
                        Set<StateNode> set =  new TreeSet<StateNode>((o1, o2) -> o1.match(o2)?0:-1);
                        for (Method method:methods){
                            MapFunctionListener mapFunctionListener = method.getAnnotation(MapFunctionListener.class);
                            if (mapFunctionListener==null) continue;

                            //函数转调method，带同代理注解方法
                            IMapRouterProcessStateNodeFunc<StateNode>
                                    iMapRouterProcessStateNodeFunc =
                                    currentNode -> {
                                        StateNode newStateNode = null;
                                        try {
                                            //newStateNode = (StateNode) method.invoke(clazz,currentNode);
                                            newStateNode = (StateNode) method.invoke(existMapFunction,currentNode);
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                            currentNode.setEx(e);
                                        } catch (InvocationTargetException e) {
                                            e.printStackTrace();
                                            currentNode.setEx(e);
                                        }finally {
                                            if (newStateNode==null){
                                                newStateNode = currentNode;

                                            }
                                        }
                                        return newStateNode;
                                    };
                            set.add(
                                    StateNode
                                            .builder()
                                            .nodeCode(mapFunctionListener.group()+"_"+mapFunctionListener.code())
                                            .iMapRouterProcessStateNodeFunc(iMapRouterProcessStateNodeFunc)
                                            .build()
                            );
                        }
                        //将处理后的set转换为container
                        if (set!=null&&set.size()>0){
                            set.forEach(
                                    stateNode -> {
                                        container.put((T) stateNode,stateNode.getIMapRouterProcessStateNodeFunc());
                                    }
                            );
                        }

                    }catch (Exception e){
                        log.error("MapFunctionListenerProcess:通过反射拿到实例:",e.getMessage());
                    }
                }
        );
        return container;
    }
}
