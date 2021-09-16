package com.reece.myrpc.version2.client;

import com.reece.myrpc.request.Request;
import com.reece.myrpc.response.Response;
import com.reece.myrpc.util.IOUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author reece
 * @date 2021-09-06 13:53
 * @description:
 */
public class ClientProxy implements InvocationHandler {


    private String host;

    private Integer port;

    public ClientProxy(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Request request = Request.builder().interfaceName(method.getDeclaringClass().getName()).methodName(method.getName())
                .paramTypes(method.getParameterTypes()).params(args).build();

        Response response = IOUtils.sendRequest(host, port, request);

        return response == null ? null : response.getData();
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }
}
