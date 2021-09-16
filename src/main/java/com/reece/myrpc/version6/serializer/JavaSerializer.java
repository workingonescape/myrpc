package com.reece.myrpc.version6.serializer;

import java.io.*;

/**
 * @author reece
 * @date 2021-09-06 18:08
 * @description:
 */
public class JavaSerializer implements Serializer {


    @Override
    public byte[] serialize(Object obj) {

        byte[] bytes = null;

        try {
            ByteArrayOutputStream bops = new ByteArrayOutputStream();
            ObjectOutputStream oops = new ObjectOutputStream(bops);
            oops.writeObject(obj);
            oops.flush();

            bytes = bops.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @Override
    public Object deserialize(byte[] bytes, int messageType) {

        Object obj = null;


        try {
            ByteArrayInputStream bips = new ByteArrayInputStream(bytes);
            ObjectInputStream oips = new ObjectInputStream(bips);
            obj = oips.readObject();
            bips.close();
            oips.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Integer getSerializeType() {
        return SerializeTypeEnum.JAVA.getType();
    }

}
