package com.reece.myrpc.version2.server.task;

import com.reece.myrpc.request.Request;
import com.reece.myrpc.response.Response;
import com.reece.myrpc.version2.server.provider.Provider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author reece
 * @date 2021-09-06 13:41
 * @description:
 */
public class WorkTask implements Runnable {




    private Socket socket;

    private Provider provider;

    public WorkTask(Socket socket, Provider provider) {
        this.socket = socket;
        this.provider = provider;
    }

    @Override
    public void run() {

        try {

            System.out.println(Thread.currentThread().getName()+" 执行调用");

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            Request request = (Request) inputStream.readObject();

            Response response = handleRequest(request);

            //返回结果
            outputStream.writeObject(response);

            outputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Response handleRequest(Request request) {
        if (request == null) {
            throw new RuntimeException("request can not be null");
        }

        System.out.println("被调用的方法：" + request);


        String interfaceName = request.getInterfaceName();


        Object o = provider.getProvider(interfaceName);


        try {

            Method method = o.getClass().getMethod(request.getMethodName(), request.getParamTypes());

            Object result = method.invoke(o, request.getParams());

            return Response.SUCCESS(result);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();

        } catch (IllegalAccessException e) {
            e.printStackTrace();

        }
        return Response.FAIL();
    }
}
