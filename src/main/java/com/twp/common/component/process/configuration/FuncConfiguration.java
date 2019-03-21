package com.twp.common.component.process.configuration;

import com.twp.common.component.process.exception.combo.impl.ExecptionForFunc;
import com.twp.common.component.process.exception.combo.impl.ExecptionForTFunc;
import com.twp.common.utils.SpringContextUtil;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by tanweiping on 2018/7/4.
 */
@ImportAutoConfiguration(
        {
                ExecptionForFunc.class,
                ExecptionForTFunc.class,
                SpringContextUtil.class
        }
)
@Configuration
public class FuncConfiguration {
}
