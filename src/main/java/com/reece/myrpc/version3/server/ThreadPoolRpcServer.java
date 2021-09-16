package com.reece.myrpc.version3.server;

import com.reece.myrpc.request.Request;
import com.reece.myrpc.version3.provider.Provider;
import com.reece.myrpc.version3.task.WorkTask;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author reece
 * @date 2021-09-06 16:01
 * @description:
 */
public class ThreadPoolRpcServer implements RpcServer{


    private Provider provider;

    private ExecutorService executorService;


    public ThreadPoolRpcServer(Provider provider) {
        this.provider = provider;
        this.executorService = new ThreadPoolExecutor(4, 8, 60L
                , TimeUnit.SECONDS, new LinkedBlockingDeque<>(20));
    }

    @Override
    public void start(Integer port) {
        try {
            ServerSocket serverSocket=new ServerSocket(port);


            while (true) {
                Socket socket = serverSocket.accept();


                executorService.execute(new WorkTask(socket, provider));

            }

        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() {

    }
}
