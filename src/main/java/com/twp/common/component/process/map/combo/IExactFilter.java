package com.twp.common.component.process.map.combo;

@FunctionalInterface
public interface IExactFilter<T> {
    boolean match(T param,T key);
}
