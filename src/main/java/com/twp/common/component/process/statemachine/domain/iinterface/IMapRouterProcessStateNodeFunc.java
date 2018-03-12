package com.twp.common.component.process.statemachine.domain.iinterface;

import com.twp.common.component.process.IProcessFunc;

@FunctionalInterface
public interface IMapRouterProcessStateNodeFunc<StateNode> extends IProcessFunc {
    StateNode process(StateNode currentNode);
}
