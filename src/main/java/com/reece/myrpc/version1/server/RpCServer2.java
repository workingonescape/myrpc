package com.reece.myrpc.version1.server;

import com.reece.myrpc.request.Request;
import com.reece.myrpc.response.Response;
import com.reece.myrpc.util.ClassUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author reece
 * @date 2021-09-06 9:14
 * @description:
 */
public class RpCServer2 {


    private static final ExecutorService EXECUTORS = new ThreadPoolExecutor(4, 8
            , 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1024)
            , r -> new Thread("任务线程"), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {


        try {
            ServerSocket serverSocket = new ServerSocket(8852);
            System.out.println("服务端启动");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

                        //解析客户端传入的参数

                        Request request = (Request) inputStream.readObject();

                        System.out.println("传入的参数为:" + request);

                        //被调用的接口
                        String interfaceName = request.getInterfaceName();

                        List<Class> impls = ClassUtils.getAllClassByInterface(Class.forName(interfaceName));

                        if (impls.size() > 1) {
                            throw new RuntimeException();
                        }
                        Class clazz = impls.get(0);
                        Object obj = clazz.newInstance();

                        //执行被调用的方法
                        Method method = clazz.getMethod(request.getMethodName(), request.getParamTypes());

                        Object result = method.invoke(obj, request.getParams());

                        //返回结果
                        outputStream.writeObject(Response.SUCCESS(result));

                        outputStream.flush();

                    } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException
                            | NoSuchMethodException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static class Task implements Runnable {

        private final Socket socket;

        public Task(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

        }
    }
}

