package com.twp.common.component.process.statemachine.base;

import com.twp.common.component.process.statemachine.annotation.IMapFunction;
import com.twp.common.component.process.statemachine.domain.StateNode;
import com.twp.common.component.process.statemachine.domain.iinterface.IMapRouterProcessStateNodeFunc;
import com.twp.common.tuple.ResultDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
//@Component
@Accessors(chain = true)
public abstract class DefaultStateNodeComponent
        extends StateComponent<
        StateNode,Map,
        Map<StateNode,IMapRouterProcessStateNodeFunc<StateNode>>,
        ResultDTO,Exception> {

    public DefaultStateNodeComponent(IMapFunction<StateNode> iMapFunction) {
        super(iMapFunction);
    }

    public DefaultStateNodeComponent(Map<StateNode, IMapRouterProcessStateNodeFunc<StateNode>> stateNodeIMapRouterProcessStateNodeFuncMap) {
        super(stateNodeIMapRouterProcessStateNodeFuncMap);
    }
}
