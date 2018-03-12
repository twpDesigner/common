package com.twp.common.component.process.statemachine.translate.impl;

import com.twp.common.component.process.map.combo.IComboMapRouterProcessFunc;
import com.twp.common.component.process.statemachine.base.StateComponent;
import com.twp.common.component.process.statemachine.domain.StateNode;
import com.twp.common.component.process.statemachine.domain.iinterface.IMapRouterProcessStateNodeFunc;
import com.twp.common.component.process.statemachine.translate.ITranslateState;
import com.twp.common.tuple.ResultDTO;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class TranslateStateCode
        <T extends StateNode,Container extends Map<T,IMapRouterProcessStateNodeFunc<T>>,Response extends ResultDTO,Resource,Ex extends Exception>
        implements ITranslateState<T ,Resource,
        Container ,
        Response ,Ex> {

    @Autowired
    IComboMapRouterProcessFunc iComboMapRouterProcessFunc;

    @Override
    public StateComponent<T,Resource,Container,Response,Ex>
    translate(
            StateComponent<T,Resource,Container,Response,Ex> stateComponent
            )
    {
        //判断当前是否最后一个节点
        //Boolean endFlag = stateComponent.getStateCode().end(stateComponent.getEndStateCode());
        /*
        最后一个节点可以设置响应,灵活配置
         */
        //if (endFlag) stateNode.setResponse(ResultDTO.builder().message("最后一个节点可以设置响应").build());
        val current = stateComponent.getStateCode();
        val next = iComboMapRouterProcessFunc.routerFunc(
                current,
                stateComponent.getContainer(),
                current
        );

        if (next.getEx()!=null){
            stateComponent.setStateCode(stateComponent.getEndStateCode());
            //组装异常到最后节点
            stateComponent.getEndStateCode().setEx(next.getEx());
            return stateComponent;
        }

        current.setAfterState(next);
        //否则返回新节点,并设置上一节点,前后关联
        next.setBeforeState(current);
        stateComponent.setStateCode(
                (T) next
        );



        return stateComponent;
    }
}
