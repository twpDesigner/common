package com.twp.common.component.process.statemachine.domain.iinterface;

import com.twp.common.component.process.statemachine.domain.StateNode;

public interface IStateNodeFlow {
    StateNode prev();
    StateNode next();
}
