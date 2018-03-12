package com.twp.common.component.process.exception;

import org.springframework.transaction.annotation.Transactional;

@FunctionalInterface
public interface IExecptionForTFunc<T> {

    /*
    事物控制
     */
    @Transactional
    void execute(T t);

    @FunctionalInterface
    interface IExecuteStateTranslate<T>{
        @Transactional
        T executeState(T t);
    }
    @FunctionalInterface
    interface IExecptionForTCatch<T,Ex>{
         void processCatch(Ex e,T t);
    }

    @FunctionalInterface
    interface IExecptionFinallyForT<T>{
         void processFinally(T t);
    }
}
