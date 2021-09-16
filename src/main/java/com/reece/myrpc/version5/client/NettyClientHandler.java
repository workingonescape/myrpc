package com.reece.myrpc.version5.client;

import com.reece.myrpc.response.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;


/**
 * @author reece
 * @date 2021-09-06 17:37
 * @description:
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<Response> {



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Response response) throws Exception {
        // 接收到response, 给channel设计别名，让sendRequest里读取response
        AttributeKey<Response> key = AttributeKey.valueOf("Response");
        ctx.channel().attr(key).set(response);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
