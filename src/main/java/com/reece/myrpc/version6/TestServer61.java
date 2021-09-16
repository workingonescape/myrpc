package com.reece.myrpc.version6;

import com.reece.myrpc.service.impl.BlogServiceImpl;
import com.reece.myrpc.service.impl.UserServiceImpl;
import com.reece.myrpc.version6.provider.Provider;
import com.reece.myrpc.version6.provider.impl.ServiceProvider;
import com.reece.myrpc.version6.server.NettyRpcServer;

/**
 * @author reece
 * @date 2021-09-06 23:32
 * @description:
 */
public class TestServer61 {


    public static void main(String[] args) {

        Provider provider = new ServiceProvider("127.0.0.1", 8899);

        provider.setInterface(new UserServiceImpl());

        provider.setInterface(new BlogServiceImpl());

        NettyRpcServer server = new NettyRpcServer(provider);

        server.start(8899);
    }
}
