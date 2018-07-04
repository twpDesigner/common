package com.twp.common.component.process.exception.combo.impl;

import com.twp.common.component.process.exception.IExecptionForTFunc;
import com.twp.common.component.process.exception.IExecptionForVoidFunc;
import com.twp.common.component.process.exception.combo.IComboExecptionForFunc;
import com.twp.common.component.process.exception.combo.IComboExecptionForTFunc;
import com.twp.common.component.process.statemachine.domain.StateNode;
import com.twp.common.component.process.statemachine.domain.iinterface.IStateException;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Builder
@Slf4j
@Component
public class ExecptionForTFunc implements IComboExecptionForTFunc {

    @Override
    public <T,Ex extends Exception> T executeFlow( T t, IExecptionForTFunc<T> iExecptionForTFunc) {
        return executeFlow(t,iExecptionForTFunc,null);
    }

    @Override
    public <T,Ex extends Exception> T executeFlow( T t, IExecptionForTFunc<T> iExecptionForTFunc, IExecptionForTFunc.IExecptionForTCatch<T,Ex> iExecptionForTCatch) {
        return executeFlow(t,iExecptionForTFunc,iExecptionForTCatch,null);
    }

    @Override
    public <T,Ex extends Exception> T executeFlow(
             T state,
            IExecptionForTFunc<T> process,
            IExecptionForTFunc.IExecptionForTCatch<T,Ex> iExecptionForTCatch,
            IExecptionForTFunc.IExecptionFinallyForT<T> iExecptionFinallyForT) {
        try{
            Optional.ofNullable(process).ifPresent(f->f.execute(state));
        }catch (Exception e){
            e.printStackTrace();
            log.error("e:",e.getMessage());
            Optional.ofNullable(iExecptionForTCatch).ifPresent(it->it.processCatch((Ex)e,state));
        }finally {
            Optional.ofNullable(iExecptionFinallyForT).ifPresent(it->it.processFinally(state));
        }
        return state;
    }

    @Override
    public <T, Ex extends Exception> T executeNewState(
             T state,
            IExecptionForTFunc.IExecuteStateTranslate<T> iExecuteStateTranslate,
            IExecptionForTFunc.IExecptionForTCatch<T, Ex> iExecptionForTCatch,
            IExecptionForTFunc.IExecptionFinallyForT<T> iExecptionFinallyForT
    ) {
        try{
            //返回新的状态
            if (iExecuteStateTranslate!=null) state = iExecuteStateTranslate.executeState(state);
        }catch (Exception e){
            log.error("e:",e.getMessage());
            //异常标记
            if (state instanceof IStateException){
                ((IStateException)state).exception(e);
            }
            T finalState=state;
            Optional.ofNullable(iExecptionForTCatch).ifPresent(it->it.processCatch((Ex)e, finalState));
        }finally {
            T finalState1=state;
            Optional.ofNullable(iExecptionFinallyForT).ifPresent(it->it.processFinally(finalState1));
        }
        return state;
    }
}
