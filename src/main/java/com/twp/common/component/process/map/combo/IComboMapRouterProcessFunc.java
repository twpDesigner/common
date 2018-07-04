package com.twp.common.component.process.map.combo;

import com.twp.common.component.process.map.IVoidMapRouterProcessFunc;
import com.twp.common.component.process.statemachine.domain.iinterface.IMapRouterProcessStateNodeFunc;

import java.util.Map;

/**
 * Created by tanweiping on 2018/7/4.
 */
public interface IComboMapRouterProcessFunc {
     <T> T routerFunc(T t,Map<T, ? extends IMapRouterProcessStateNodeFunc<T>> routeFuncMap);

     <T> T routerFunc(T t,Map<T, ? extends IMapRouterProcessStateNodeFunc<T>> routeFuncMap,IExactFilter<T> matchFunc);

     <T> void routerVoidFunc(T t,Map<T, ? extends IVoidMapRouterProcessFunc> routeFuncMap);
     <T> void routerVoidFunc(T t,Map<T, ? extends IVoidMapRouterProcessFunc> routeFuncMap,IExactFilter<T> matchFunc);
}
