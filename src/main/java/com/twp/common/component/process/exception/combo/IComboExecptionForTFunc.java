package com.twp.common.component.process.exception.combo;

import com.twp.common.component.process.exception.IExecptionForTFunc;
import com.twp.common.component.process.exception.IExecptionForVoidFunc;
import com.twp.common.component.process.statemachine.domain.StateNode;
import org.jetbrains.annotations.NotNull;

public interface IComboExecptionForTFunc {
     <T,Ex extends Exception> T executeFlow(@NotNull T t, IExecptionForTFunc<T> iExecptionForTFunc);
     <T,Ex extends Exception> T executeFlow(@NotNull T t,IExecptionForTFunc<T> iExecptionForTFunc, IExecptionForTFunc.IExecptionForTCatch<T,Ex> iExecptionForTCatch);
     <T,Ex extends Exception> T executeFlow(
             @NotNull T t,
            IExecptionForTFunc<T> iExecptionForTFunc,
            IExecptionForTFunc.IExecptionForTCatch<T,Ex> iExecptionForTCatch,
            IExecptionForTFunc.IExecptionFinallyForT<T> iExecptionFinallyForT
    );

    <T,Ex extends Exception> T executeNewState(
            @NotNull T t,
            IExecptionForTFunc.IExecuteStateTranslate<T> iExecuteStateTranslate,
            IExecptionForTFunc.IExecptionForTCatch<T,Ex> iExecptionForTCatch,
            IExecptionForTFunc.IExecptionFinallyForT<T> iExecptionFinallyForT
    );
}
