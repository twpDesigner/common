package com.twp.common.component.process.exception.combo.impl;

import com.twp.common.component.process.exception.IExecptionForVoidFunc;
import com.twp.common.component.process.exception.combo.IComboExecptionForFunc;
import com.twp.common.tuple.ResultDTO;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Builder
@Slf4j
@Component
public class ExecptionForFunc implements IComboExecptionForFunc {

    public void executeFlow(IExecptionForVoidFunc iExecptionForVoidFunc){
        executeFlow(iExecptionForVoidFunc,null);
    }

    public void executeFlow(IExecptionForVoidFunc iExecptionForVoidFunc,IExecptionForVoidFunc.IExecptionCatch iExecptionCatch){
        executeFlow(iExecptionForVoidFunc,iExecptionCatch,null);
    }

    public void executeFlow(
            IExecptionForVoidFunc iExecptionForVoidFunc,
            IExecptionForVoidFunc.IExecptionCatch iExecptionCatch,
            IExecptionForVoidFunc.IExecptionFinally iExecptionFinally
            ){
        try{
            Optional.ofNullable(iExecptionForVoidFunc).ifPresent(f->f.execute());
        }catch (Exception e){
            log.error("e:",e.getMessage());
            Optional.ofNullable(iExecptionCatch).ifPresent(it->it.processCatch(e));
        }finally {
            Optional.ofNullable(iExecptionFinally).ifPresent(it->it.processFinally());
        }
    }
}
