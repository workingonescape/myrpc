package com.reece.myrpc.version3;

import com.reece.myrpc.service.BlogService;
import com.reece.myrpc.service.UserService;
import com.reece.myrpc.service.impl.BlogServiceImpl;
import com.reece.myrpc.service.impl.UserServiceImpl;
import com.reece.myrpc.version3.provider.Provider;
import com.reece.myrpc.version3.provider.impl.ServiceProvider;
import com.reece.myrpc.version3.server.NettyRpcServer;

/**
 * @author reece
 * @date 2021-09-06 17:03
 * @description:
 */
public class Server {


    public static void main(String[] args) {

        Provider provider = new ServiceProvider();

        UserService userService = new UserServiceImpl();

        BlogService blogService = new BlogServiceImpl();

        provider.setInterface(userService);

        provider.setInterface(blogService);

        NettyRpcServer server = new NettyRpcServer(provider);

        server.start(8899);
    }
}
