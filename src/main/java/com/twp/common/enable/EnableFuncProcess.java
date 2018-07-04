package com.twp.common.enable;

import com.twp.common.component.process.configuration.FuncConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by tanweiping on 2018/7/4.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(FuncConfiguration.class)
@Documented
public @interface EnableFuncProcess {
}
