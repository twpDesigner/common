package com.twp.common.component.process.exception.combo;

import com.twp.common.component.process.exception.IExecptionForVoidFunc;

public interface IComboExecptionForFunc {
    void executeFlow(IExecptionForVoidFunc iExecptionForVoidFunc);
    void executeFlow(IExecptionForVoidFunc iExecptionForVoidFunc, IExecptionForVoidFunc.IExecptionCatch iExecptionCatch);
    void executeFlow(
            IExecptionForVoidFunc iExecptionForVoidFunc,
            IExecptionForVoidFunc.IExecptionCatch iExecptionCatch,
            IExecptionForVoidFunc.IExecptionFinally iExecptionFinally
    );
}
