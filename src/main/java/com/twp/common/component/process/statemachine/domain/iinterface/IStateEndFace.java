package com.twp.common.component.process.statemachine.domain.iinterface;

import com.twp.common.component.process.statemachine.domain.StateNode;

@FunctionalInterface
public interface IStateEndFace<T extends StateNode> {
     boolean end(T t);
}
