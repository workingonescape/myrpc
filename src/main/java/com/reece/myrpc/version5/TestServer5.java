package com.reece.myrpc.version5;



import com.reece.myrpc.service.impl.BlogServiceImpl;
import com.reece.myrpc.service.impl.UserServiceImpl;


import com.reece.myrpc.version5.provider.Provider;
import com.reece.myrpc.version5.provider.impl.ServiceProvider;

import com.reece.myrpc.version5.server.NettyRpcServer;
import com.reece.myrpc.version5.server.RpcServer;

/**
 * @author reece
 * @date 2021-09-06 22:12
 * @description:
 */
public class TestServer5 {


    public static void main(String[] args) {

        Provider provider = new ServiceProvider("127.0.0.1", 8899);

        provider.setInterface(new UserServiceImpl());

        provider.setInterface(new BlogServiceImpl());

        RpcServer server = new NettyRpcServer(provider);

        server.start(8899);

    }
}
