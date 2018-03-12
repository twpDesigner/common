package com.twp.common.component.process.statemachine.annotation;

public interface IMapFunctionListenerProcess<T,Container> {
    Container handler(IMapFunction<T> iMapFunction);
}
