package com.twp.common.component.process.statemachine.demo;


import com.twp.common.component.process.statemachine.annotation.IMapFunction;
import com.twp.common.component.process.statemachine.annotation.MapFunctionListener;
import com.twp.common.component.process.statemachine.domain.StateNode;
import com.twp.common.tuple.ResultDTO;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@Builder
public class DemoFuncMap implements IMapFunction {

    @MapFunctionListener(name = "测试01",group = "t",code = "test01")
    public StateNode test01(StateNode<Map,ResultDTO> currentNode){
        log.info("test01");
        return StateNode.builder().nodeCode("t_test03").build();
    }

    @MapFunctionListener(name = "测试02",group = "t",code = "test02")
    public StateNode test02(StateNode currentNode){
        log.info("test02");
        return StateNode.builder().response(ResultDTO.builder().message("测试终点状态").build()).build();
    }

    @MapFunctionListener(name = "测试03",group = "t",code = "test03")
    public StateNode test03(StateNode currentNode){
        log.info("test03");
        //测试异常
        //int ss = 1/0;
        return StateNode.builder().nodeCode("t_test02").build();
    }
}
