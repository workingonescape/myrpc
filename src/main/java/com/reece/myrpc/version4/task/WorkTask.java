package com.reece.myrpc.version4.task;

import com.reece.myrpc.request.Request;
import com.reece.myrpc.response.Response;
import com.reece.myrpc.version4.provider.Provider;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author reece
 * @date 2021-09-06 16:12
 * @description:
 */
public class WorkTask  implements Runnable, Task {


    private Socket socket;

    private Provider provider;

    public WorkTask(Socket socket, Provider provider) {
        this.socket = socket;
        this.provider = provider;
    }

    @Override
    public void run() {
        if (socket == null) {
            return;
        }

        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            Request request = (Request) inputStream.readObject();

            Response response = handle(request);


            outputStream.writeObject(response);
            outputStream.flush();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Response handle(Request request) {
        verify(request);

        try {
            Object instance = provider.getInterface(request.getInterfaceName());

            Method method = instance.getClass().getMethod(request.getMethodName(), request.getParamTypes());
            Object result = method.invoke(instance, request.getParams());

            return Response.SUCCESS(result);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return Response.FAIL();
    }

    @Override
  public   void verify(Request request) {
        if (StringUtils.isEmpty(request.getInterfaceName()) || StringUtils.isEmpty(request.getMethodName())
                || CollectionUtils.isEmpty(Arrays.asList(request.getParams()))
                || CollectionUtils.isEmpty(Arrays.asList(request.getParamTypes()))) {
            throw new RuntimeException("invalid request");
        }

    }
}
