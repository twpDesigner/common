package com.twp.common.component.process.map.combo

import com.twp.common.component.process.map.IVoidMapRouterProcessFunc
import com.twp.common.component.process.statemachine.domain.StateNode
import com.twp.common.component.process.statemachine.domain.iinterface.IMapRouterProcessStateNodeFunc
import com.twp.common.tuple.ResultDTO
import org.omg.CORBA.Object


interface IComboMapRouterProcessFunc {
    fun <T> routerFunc(t: T, routeFuncMap: Map<T, IMapRouterProcessStateNodeFunc<T>>): T

    fun <T> routerFunc(t: T, routeFuncMap: Map<T, IMapRouterProcessStateNodeFunc<T>>,matchFunc:IExactFilter<T>?): T

    fun <T> routerVoidFunc(t: T, routeFuncMap: Map<T, IVoidMapRouterProcessFunc>)
    fun <T> routerVoidFunc(t: T, routeFuncMap: Map<T, IVoidMapRouterProcessFunc>,matchFunc:IExactFilter<T>?)

}
