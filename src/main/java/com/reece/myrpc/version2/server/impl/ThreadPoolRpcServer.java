package com.reece.myrpc.version2.server.impl;

import com.reece.myrpc.version2.server.RpcServer;
import com.reece.myrpc.version2.server.provider.Provider;
import com.reece.myrpc.version2.server.task.WorkTask;
import com.reece.myrpc.version2.server.threadfactory.NamedThreadFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author reece
 * @date 2021-09-06 13:23
 * @description:
 */
public class ThreadPoolRpcServer implements RpcServer {


    private ExecutorService executor;


    private Provider provider;



    public ThreadPoolRpcServer(Provider provider) {
        this.provider = provider;
        this.executor = new ThreadPoolExecutor(5, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), new NamedThreadFactory("server 线程"), new ThreadPoolExecutor.AbortPolicy());
    }

    public ThreadPoolRpcServer(Provider provider, int corePoolSize,
                               int maximumPoolSize,
                               long keepAliveTime,
                               TimeUnit unit,
                               BlockingQueue<Runnable> workQueue,
                               ThreadFactory threadFactory,
                               RejectedExecutionHandler handler) {
        this.provider = provider;
        this.executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory
                , handler);
    }

    @Override
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务端启动，监听："+port+" 端口");
            while (true) {
                Socket socket = serverSocket.accept();
                executor.execute(new WorkTask(socket, provider));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() {

    }
}
