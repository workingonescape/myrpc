package com.reece.myrpc.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author reece
 * @date 2021-09-06 9:06
 * @description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    //服务类名
    private String interfaceName;

    //调用的方法
    private String methodName;

    //参数
    private Object[] params;

    //参数类型
    private Class<?>[] paramTypes;
}
