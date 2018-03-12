package com.twp.common.component.process.exception;

@FunctionalInterface
public interface IExecptionForVoidFunc {

    /*
    事物控制
     */
    void execute();

    @FunctionalInterface
    interface IExecptionCatch{
        void processCatch(Exception e);
    }

    @FunctionalInterface
    interface IExecptionFinally{
        void processFinally();
    }
}
