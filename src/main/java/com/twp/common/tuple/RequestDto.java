package com.twp.common.tuple;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor@NoArgsConstructor@Data@Builder
public class RequestDto<T> {
    private java.security.Principal principal;
    private T data;
}
