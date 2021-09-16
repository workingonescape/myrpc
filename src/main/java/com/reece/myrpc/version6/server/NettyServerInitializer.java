package com.reece.myrpc.version6.server;

import com.reece.myrpc.version6.provider.Provider;
import com.reece.myrpc.version6.serializer.JSONSerializer;
import com.reece.myrpc.version6.serializer.MyDecode;
import com.reece.myrpc.version6.serializer.MyEncode;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @author reece
 * @date 2021-09-06 16:31
 * @description:
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel>  {

    private Provider provider;



    public NettyServerInitializer(Provider provider) {
        this.provider = provider;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {


        try {
            ChannelPipeline pipeline = socketChannel.pipeline();

            // 消息格式 [长度][消息体], 解决粘包问题
            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE
                    , 0, 4, 0, 4));

            //计算当前待发送的消息的长度 写入前4个字节
            pipeline.addLast(new LengthFieldPrepender(4));

            //这里使用java序列化方式 netty支持
            pipeline.addLast(new MyEncode(new JSONSerializer()));

            pipeline.addLast(new MyDecode());

            pipeline.addLast(new NettyServerHandler(provider));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
