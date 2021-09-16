package com.reece.myrpc.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author reece
 * @date 2021-09-06 9:08
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {


    private static final long serialVersionUID = 1L;


    private Integer code;

    private String message;

    private Object data;

    //数据类型
    private Class<?> dataType;


    public static Response SUCCESS(Object data) {
        return Response.builder().code(200).message("success").data(data).build();
    }

    public static Response FAIL() {
        return Response.builder().code(500).message("internal error").build();
    }
}
