package com.twp.common.basic;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by tanweiping on 17/8/8.
 */
public interface IBaseRequest<T> {

     default IdentityHashMap<String,Object> beanToMap(Object... params){
        IdentityHashMap<String,Object> p = new IdentityHashMap<>();
        Stream.of(params)
                .forEach(
                        o->{
                            if (o instanceof Map){
                                p.putAll((Map<? extends String, ?>) o);
                            }
                            else{
                                //只判断俩层
                                Set<Field> fieldList = new HashSet<Field>(){{
                                    addAll(Arrays.asList(o.getClass().getSuperclass().getDeclaredFields()));
                                    addAll(Arrays.asList(o.getClass().getDeclaredFields()));
                                }}
                                ;
                                fieldList.forEach(field_ -> {
                                    try {
                                        if(!field_.isAccessible()) field_.setAccessible(true);
                                        if (field_.get(o)!=null){
                                            p.put(new String(field_.getName()),field_.get(o));
                                        }
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    }
                                });
                            }
                        }
                );
        return p;
    }
}
