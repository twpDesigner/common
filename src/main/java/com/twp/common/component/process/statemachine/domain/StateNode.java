package com.twp.common.component.process.statemachine.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.twp.common.component.process.map.combo.IExactFilter;
import com.twp.common.component.process.statemachine.domain.iinterface.IMapRouterProcessStateNodeFunc;
import com.twp.common.component.process.statemachine.domain.iinterface.IStateEndFace;
import com.twp.common.component.process.statemachine.domain.iinterface.IStateException;
import com.twp.common.component.process.statemachine.domain.iinterface.IStateNodeFlow;
import com.twp.common.tuple.RequestDto;
import com.twp.common.tuple.ResultDTO;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Map;

@AllArgsConstructor@NoArgsConstructor@Data@Builder
@EqualsAndHashCode(exclude = {"beforeState"})
@ToString(exclude = "beforeState")
@Accessors(chain = true)
public class StateNode<RequestTuple,Response extends ResultDTO> implements IExactFilter<StateNode>,IStateEndFace<StateNode>,IStateNodeFlow,IStateException {

    private StateNode beforeState;

    private StateNode afterState;

    /*
    节点名称
     */
    private String nodeName;

    /*
    节点代码
     */
    private String nodeCode;

    /*
    节点描述
     */
    @Builder.Default
    private String nodeDesc = "节点描述";

    /*
    节点可以设置返回的响应携带
     */
    private  Response response;

    /*
    节点携带的参数
     */
    private RequestDto<RequestTuple> requestDto;

    private Exception ex;

    /*
    节点携带的引用，方便实现注解
     */
    IMapRouterProcessStateNodeFunc<StateNode> iMapRouterProcessStateNodeFunc;


    @JsonIgnore
    static final Date d = new Date();

    /**
     * 创建时间
     */
    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime = d;

    /**
     * 更新时间
     */
    @Builder.Default
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime = d;

    @Override
    public boolean match(StateNode param, StateNode key) {
        if (param.equals(key)) return true;
        if (param.getNodeCode().equals(key.getNodeCode())) return true;
        return false;
    }

    public boolean match(StateNode target) {
        if (this.equals(target)) return true;
        if (this.getNodeCode().equals(target.getNodeCode())) return true;
        return false;
    }

    /*
    节点是否结束
     */
    @Override
    public  boolean end(StateNode endStateCode) {
        if (endStateCode==null) return false;
        return match(this, endStateCode);
    }

    @Override
    public StateNode prev() {
        return this.getBeforeState();
    }

    @Override
    public StateNode next() {
        return this.getAfterState();
    }

    @Override
    public void exception(Exception ex) {
        this.setEx(ex);
    }
}
