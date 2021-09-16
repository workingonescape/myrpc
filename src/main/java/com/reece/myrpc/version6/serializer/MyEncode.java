package com.reece.myrpc.version6.serializer;

import com.reece.myrpc.request.Request;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author reece
 * @date 2021-09-06 20:20
 * @description: 将对象转为字节数组
 */
public class MyEncode extends MessageToByteEncoder {


    private Serializer serializer;

    public MyEncode(Serializer serializer) {
        this.serializer = serializer;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf byteBuf) throws Exception {

        //写入消息类型 Request 还是response
        if (msg instanceof Request) {
            byteBuf.writeShort(MessageType.REQUEST.getType());
        } else {
            byteBuf.writeShort(MessageType.RESPONSE.getType());
        }

        //获取序列化方式
        Serializer serializerByType = Serializer.getSerializerByType(serializer.getSerializeType());

        // 写入序列化方式
        byteBuf.writeInt(serializerByType.getSerializeType());


        // 得到序列化数组
        byte[] bytes = serializerByType.serialize(msg);

        // 写入长度

        byteBuf.writeInt(bytes.length);

        // 写入序列化后的字节数组
        byteBuf.writeBytes(bytes);

    }
}
