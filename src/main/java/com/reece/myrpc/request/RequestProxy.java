package com.reece.myrpc.request;

import com.reece.myrpc.response.Response;
import com.reece.myrpc.util.IOUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author reece
 * @date 2021-09-06 9:58
 * @description:
 */
public class RequestProxy implements InvocationHandler {

    private String host;

    private Integer port;

    public RequestProxy(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    //动态代理
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //封装 request
        Request request = Request.builder().interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName()).params(args).paramTypes(method.getParameterTypes()).build();

        //调用结果
        Response response = IOUtils.sendRequest(host, port, request);

        //返回结果
        return response.getData();
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }

}
