package com.reece.myrpc.version5.server;

import com.reece.myrpc.request.Request;
import com.reece.myrpc.response.Response;
import com.reece.myrpc.version5.provider.Provider;
import com.reece.myrpc.version5.task.Task;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author reece
 * @date 2021-09-06 16:43
 * @description:
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<Request> implements Task {

    private Provider provider;

    public NettyServerHandler(Provider provider) {
        this.provider = provider;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Request request) throws Exception {

        Response response = handler(request);

        channelHandlerContext.writeAndFlush(response);
        channelHandlerContext.close();
    }

    private Response handler(Request request) {

        verify(request);

        try {
            Object instance = provider.getInterface(request.getInterfaceName());
            if (instance == null) {
                throw new RuntimeException("can not find" + request);
            }
            Method method = instance.getClass().getMethod(request.getMethodName(), request.getParamTypes());
            Object result = method.invoke(instance, request.getParams());
            Response response = Response.SUCCESS(result);
            response.setDataType(result.getClass());
            return response;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public void verify(Request request) {

    }
}
