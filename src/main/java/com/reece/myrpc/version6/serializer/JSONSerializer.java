package com.reece.myrpc.version6.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.reece.myrpc.request.Request;
import com.reece.myrpc.response.Response;

/**
 * @author reece
 * @date 2021-09-06 18:18
 * @description:
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte[] serialize(Object obj) {
        //直接调用方法即可
        return JSONObject.toJSONBytes(obj);
    }

    @Override
    public Object deserialize(byte[] bytes, int messageType) {
        Object result = null;
        switch (messageType) {
            case 1:
                Request request = JSONObject.parseObject(bytes, Request.class);
                if (request.getParams() == null) {
                    return request;
                }
                Object[] parmas = new Object[request.getParams().length];

                for (int i = 0; i < parmas.length; i++) {

                    Class<?> clazz = request.getParamTypes()[i];

                    if (clazz.isAssignableFrom(request.getParams()[i].getClass())) {
                        parmas[i] = request.getParams()[i];
                    }else{
                        parmas[i] = JSONObject.toJavaObject((JSONObject) request.getParams()[i], request.getParamTypes()[i]);
                    }
                }

                request.setParams(parmas);
                result = request;
                break;
            case 2:

                Response response = JSONObject.parseObject(bytes, Response.class);

                Class<?> dataType = response.getDataType();

                if (!dataType.isAssignableFrom(response.getData().getClass())) {
                    Object data = JSONObject.toJavaObject((JSON) response.getData(), dataType);
                    response.setData(data);
                }
                result = response;
                break;
            default:
                System.out.println("暂时不支持此种消息");
                throw new RuntimeException();

        }

        return result;

    }

    @Override
    public Integer getSerializeType() {
        return SerializeTypeEnum.JSON.getType();
    }

}
