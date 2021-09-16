package com.reece.myrpc.version6.proxy;

import com.reece.myrpc.request.Request;
import com.reece.myrpc.response.Response;
import com.reece.myrpc.version6.client.RpcClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author reece
 * @date 2021-09-06 15:57
 * @description:
 */
public class ClientProxy implements InvocationHandler {


    private RpcClient client;

    public ClientProxy(RpcClient client) {
        this.client = client;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Request request = Request.builder().interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName()).paramTypes(method.getParameterTypes()).params(args).build();

        Response response = client.sendRequest(request);

        return response.getData();
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }
}
