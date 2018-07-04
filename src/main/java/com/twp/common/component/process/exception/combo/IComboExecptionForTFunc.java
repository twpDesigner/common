package com.twp.common.component.process.exception.combo;

import com.twp.common.component.process.exception.IExecptionForTFunc;


public interface IComboExecptionForTFunc {
     <T,Ex extends Exception> T executeFlow(T t, IExecptionForTFunc<T> iExecptionForTFunc);
     <T,Ex extends Exception> T executeFlow(T t,IExecptionForTFunc<T> iExecptionForTFunc, IExecptionForTFunc.IExecptionForTCatch<T,Ex> iExecptionForTCatch);
     <T,Ex extends Exception> T executeFlow(
             T t,
            IExecptionForTFunc<T> iExecptionForTFunc,
            IExecptionForTFunc.IExecptionForTCatch<T,Ex> iExecptionForTCatch,
            IExecptionForTFunc.IExecptionFinallyForT<T> iExecptionFinallyForT
    );

    <T,Ex extends Exception> T executeNewState(
            T t,
            IExecptionForTFunc.IExecuteStateTranslate<T> iExecuteStateTranslate,
            IExecptionForTFunc.IExecptionForTCatch<T,Ex> iExecptionForTCatch,
            IExecptionForTFunc.IExecptionFinallyForT<T> iExecptionFinallyForT
    );
}
