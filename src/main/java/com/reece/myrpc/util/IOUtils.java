package com.reece.myrpc.util;

import com.reece.myrpc.request.Request;
import com.reece.myrpc.response.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author reece
 * @date 2021-09-06 9:44
 * @description:
 */
public class IOUtils {


    /**
     * 公共方法 传入 host port request 获取 response
     * @param host
     * @param port
     * @param request
     * @return
     */
    public static Response sendRequest(String host, Integer port, Request request) {


        try {
            Socket socket = new Socket(host, port);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            System.out.println(request);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();

            Response response = (Response) objectInputStream.readObject();

            System.out.println("返回结果为：" + response);

            return response;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
