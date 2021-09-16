package com.reece.myrpc.version6.serializer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author reece
 * @date 2021-09-06 20:28
 * @description:
 */
public class MyDecode extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //读取消息类型
        short messageType = byteBuf.readShort();

        if (messageType != MessageType.REQUEST.getType() && messageType != MessageType.RESPONSE.getType()) {
            throw new RuntimeException("unknown message type ");
        }

        //读取序列化方式
        int serializeType = byteBuf.readInt();



        //获取序列化方式
        Serializer serializer = Serializer.getSerializerByType(serializeType);

        if (serializer == null) {
            throw new RuntimeException("unknown serializer");
        }



        //读取数组长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];

        //读取数据 写入到bytes中
        byteBuf.readBytes(bytes);

        //反序列化成对象
        Object obj = serializer.deserialize(bytes, messageType);

        list.add(obj);

    }
}
