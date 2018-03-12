package com.twp.common.component.process.statemachine.base;

import com.twp.common.component.process.statemachine.annotation.IMapFunction;
import com.twp.common.component.process.statemachine.annotation.IMapFunctionListenerProcess;
import com.twp.common.component.process.statemachine.domain.StateNode;
import com.twp.common.component.process.statemachine.domain.iinterface.IMapRouterProcessStateNodeFunc;
import com.twp.common.component.process.statemachine.translate.ITranslateState;
import com.twp.common.tuple.ResultDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.javatuples.Pair;

import java.util.Map;
import java.util.Optional;

@Slf4j
//@Component
@Data
@Accessors(chain = true)
public abstract class StateComponent
        <T extends StateNode,Resource,
        Container extends Map<T,IMapRouterProcessStateNodeFunc<T>>,
                Response extends ResultDTO,Ex extends Exception> {

    //@Autowired
    ITranslateState<T,Resource,Container,Response,Ex> iTranslateState ;//= new TranslateStateCode<>();

    //@Autowired
    IMapFunctionListenerProcess<T,Container> iMapFunctionListenerProcess;// = new MapFunctionListenerProcess<>() ;

    IMapFunction<T> iMapFunction;

    //状态跟节点
    private T stateCode;

    private T endStateCode;

    private Container container;

    //属性集合对象
    private Resource resource;

    //响应体对象
    private Response response;

    public StateComponent(IMapFunction<T> iMapFunction) {
        this.iMapFunction=iMapFunction;
    }

    public StateComponent(Container container) {
        this.container=container;
    }

    public Response runOnResp(){
        log.info("启动组件");
        try{
            //初始化state
            this.stateCode = this.onCreate();
            //初始化属性
            if (iMapFunction!=null){
                this.container = this.onLoadContainer(this.iMapFunction,this.container);
            }
            val o = this.onStart();
            this.resource = o.getValue0();
            this.endStateCode = o.getValue1();
            //处理器中使用状态与属性
            response = (Response)this.onProcess(this.container).getResponse();
        }catch (Exception e){
            //处理异常
            this.onException((Ex)e);
        }finally {
            //释放资源
            try {
                this.onDestroy();
            }catch (Exception e){
                this.onException((Ex)e);
            }finally {
                return response;
            }
        }
    }

    public void run(){
        log.info("启动组件");
        try{
            //初始化state
            this.stateCode = this.onCreate();
            //初始化属性
            //初始化state
            this.stateCode = this.onCreate();
            //初始化属性
            if (iMapFunction!=null) this.container = this.onLoadContainer(this.iMapFunction,this.container);

            val o = this.onStart();
            this.resource = o.getValue0();
            this.endStateCode = o.getValue1();
            this.onProcess(this.container).getResponse();
        }catch (Exception e){
            //处理异常
            this.onException((Ex)e);
        }finally {
            //释放资源
            try {
                this.onDestroy();
            }catch (Exception e){
                this.onException((Ex)e);
            }
        }
    }

    /*
        组件创建
        提供初始化对象
         */
    public abstract T onCreate();

    /*
    提供属性加载
    提供容器加载
    设置结束节点
     */
    public abstract Pair<Resource,T> onStart();


    /*
    优先使用注解class的mapFunction
     */
    public Container onLoadContainer(IMapFunction<T> iMapFunction,Container injectContainer){
        Container container = injectContainer;
        if (this.iMapFunctionListenerProcess!=null){
            container = (Container) this.iMapFunctionListenerProcess.handler(iMapFunction);
        }
        return container;
    }


    /*
    组件执行
     */
    public   T onProcess(Container functionContainer){
        T responseNode=null;
        //结束flag
        boolean flag = false;
        while (!flag){
            val loopState = iTranslateState.translate(this);

            flag = loopState.end();

            //到达最后一个状态节点
            //或者发生异常了的情况，终止
            if (flag){
                //结束所有节点循环
                log.info("状态循环机制结束");
                //执行最后的终点节点状态
                log.info("执行最后的终点节点状态");
                responseNode =  iTranslateState.translate(this).getStateCode();

            }
        }
        return responseNode;
    }

    /*
    组件停止工作 异常情况
     */
    public abstract void onException(Ex e);


    /*
    组件销毁，释放引用资源
     */
    public abstract void onDestroy();


    public Boolean end(){
        return this.getStateCode().end(this.getEndStateCode());
    }

    /*
    返回response
     */

}
