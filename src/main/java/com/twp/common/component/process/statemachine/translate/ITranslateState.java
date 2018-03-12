package com.twp.common.component.process.statemachine.translate;

import com.twp.common.component.process.statemachine.base.StateComponent;
import com.twp.common.component.process.statemachine.domain.StateNode;
import com.twp.common.component.process.statemachine.domain.iinterface.IMapRouterProcessStateNodeFunc;
import com.twp.common.tuple.ResultDTO;

import java.util.Map;

@FunctionalInterface
public interface ITranslateState
        <T extends StateNode,
                Resource,
        Container extends Map<T,IMapRouterProcessStateNodeFunc<T>>,
        Response extends ResultDTO,Ex extends Exception> {
    /*
    入参是一个状态对象，返回下一个状态对象，不断的循环匹配，知道最后一个
     */
    StateComponent<T,Resource,Container,Response,Ex> translate(StateComponent<T,Resource,Container,Response,Ex> stateComponent) ;
}
