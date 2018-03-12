package com.twp.common.tuple;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;


/**
 * Created by cai on 24/05/2017.
 */
@Data
@Builder
public class ResultDTO<T> {

    @Builder.Default
    private Boolean isSuccess = Boolean.TRUE;
    private String message;
    @Builder.Default
    private Object code =HttpStatus.OK.value();
    private T data;

    public ResultDTO() {
    }

    public ResultDTO(Boolean isSuccess, String message, Object code, T data) {
        this.isSuccess=isSuccess;
        this.message=message;
        this.code=code;
        this.data=data;
    }
}
