package com.twp.common.component.process.map.combo.impl;

import com.twp.common.component.process.IProcessFunc;
import com.twp.common.component.process.exception.combo.IComboExecptionForFunc;
import com.twp.common.component.process.exception.combo.IComboExecptionForTFunc;
import com.twp.common.component.process.exception.combo.impl.ExecptionForTFunc;
import com.twp.common.component.process.map.IVoidMapRouterProcessFunc;
import com.twp.common.component.process.map.combo.IComboMapRouterProcessFunc;
import com.twp.common.component.process.map.combo.IExactFilter;
import com.twp.common.component.process.statemachine.domain.StateNode;
import com.twp.common.component.process.statemachine.domain.iinterface.IMapRouterProcessStateNodeFunc;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Builder
@Slf4j
@Component
public class MapRouterProcessFunc implements IComboMapRouterProcessFunc{

    //@Builder.Default
    //ExecptionForFunc exceptionProcess = ExecptionForFunc.builder().build();

    @Autowired
    IComboExecptionForFunc iComboExecptionForFunc;

    @Autowired
    IComboExecptionForTFunc iComboExecptionForTFunc;

    @Override
    public <T> void routerVoidFunc(T t, @NotNull Map<T, ? extends IVoidMapRouterProcessFunc> routeFuncMap) {
        routerVoidFunc(t,routeFuncMap,null);
    }


    public <T> void routerVoidFunc(@NotNull T t, @NotNull Map<T, ? extends IVoidMapRouterProcessFunc> routeFuncMap, IExactFilter<T> matchFunc) {
        iComboExecptionForFunc.executeFlow(()->
                ((IVoidMapRouterProcessFunc)matchKey(t,routeFuncMap,matchFunc)).process()
        );
    }


    public <T> IProcessFunc matchKey(T tCopy,Map<T, ? extends IProcessFunc> routeFuncMap,IExactFilter matchFunc) {

        if (matchFunc == null) {
            if (routeFuncMap.containsKey(tCopy)) {
                return filterFunc(routeFuncMap,tCopy);
            }
        }
        final IProcessFunc[] func={null};
        routeFuncMap.keySet().stream().filter(key -> matchFunc.match(tCopy, key)).findFirst()
                .ifPresent(key_ -> func[0] = filterFunc(routeFuncMap,key_));

        return func[0];
    }

    private <T> IProcessFunc filterFunc(Map<T, ? extends IProcessFunc> routeFuncMap, T key){
        return routeFuncMap
                .computeIfPresent(
                        key,
                        (k, v) -> v);
    }

    @Override
    public <T> T routerFunc(T t, @NotNull Map<T, ? extends IMapRouterProcessStateNodeFunc<T>> routeFuncMap) {
        return routerFunc(t,routeFuncMap,null);
    }

    @Override
    public <T> T routerFunc(T currentNode, @NotNull Map<T, ? extends IMapRouterProcessStateNodeFunc<T>> routeFuncMap, @Nullable IExactFilter<T> matchFunc) {
        return iComboExecptionForTFunc.executeNewState(
                currentNode,node->
                ((IMapRouterProcessStateNodeFunc<T>)matchKey(node,routeFuncMap,matchFunc)).process(node),
                (ex,node)->{
                    //发生异常
                    log.info("发生异常");
                },
                node->{
                    //处理新的状态节点
                    //BeanUtils.copyProperties(t_,t);
                }
        );
    }
}
