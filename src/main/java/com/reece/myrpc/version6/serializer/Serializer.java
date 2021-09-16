package com.reece.myrpc.version6.serializer;

/**
 * @author reece
 * @date 2021-09-06 18:04
 * @description:
 */
public interface Serializer {


    //把对象转为字节数组

    byte[] serialize(Object obj);

    //将字节数组转为对象
    Object deserialize(byte[] bytes, int messageType);

    Integer getSerializeType();


    static Serializer getSerializerByType(Integer type) {
        switch (type) {
            case 0:
                return new JavaSerializer();
            case 1:
                return new JSONSerializer();

            default:
                return null;
        }
    }
}
