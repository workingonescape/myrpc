package com.reece.myrpc.version4;

import com.reece.myrpc.service.impl.BlogServiceImpl;
import com.reece.myrpc.service.impl.UserServiceImpl;
import com.reece.myrpc.version4.provider.Provider;
import com.reece.myrpc.version4.provider.impl.ServiceProvider;
import com.reece.myrpc.version4.server.NettyRpcServer;

/**
 * @author reece
 * @date 2021-09-06 18:03
 * @description:
 */
public class TestServer4 {


    public static void main(String[] args) {

        Provider provider = new ServiceProvider();

        provider.setInterface(new UserServiceImpl());

        provider.setInterface(new BlogServiceImpl());


        NettyRpcServer server = new NettyRpcServer(provider);

        server.start(8899);
    }
}
