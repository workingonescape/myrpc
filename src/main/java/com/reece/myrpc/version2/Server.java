package com.reece.myrpc.version2;


import com.reece.myrpc.service.BlogService;
import com.reece.myrpc.service.UserService;
import com.reece.myrpc.service.impl.BlogServiceImpl;
import com.reece.myrpc.service.impl.UserServiceImpl;
import com.reece.myrpc.version2.server.RpcServer;
import com.reece.myrpc.version2.server.impl.ThreadPoolRpcServer;
import com.reece.myrpc.version2.server.provider.Provider;
import com.reece.myrpc.version2.server.provider.ServiceProvider;

/**
 * @author reece
 * @date 2021-09-06 13:58
 * @description:
 */
public class Server {


    public static void main(String[] args) {

        Provider serviceProvider = new ServiceProvider();

        UserService userService = new UserServiceImpl();

        BlogService blogService = new BlogServiceImpl();

        serviceProvider.setProvider(userService);

        serviceProvider.setProvider(blogService);

        RpcServer rpcServer = new ThreadPoolRpcServer(serviceProvider);

        rpcServer.start(8899);
    }
}
