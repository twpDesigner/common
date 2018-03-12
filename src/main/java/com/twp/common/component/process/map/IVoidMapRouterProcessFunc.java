package com.twp.common.component.process.map;

import com.twp.common.component.process.IProcessFunc;

@FunctionalInterface
public interface IVoidMapRouterProcessFunc extends IProcessFunc {
    void process();
}
