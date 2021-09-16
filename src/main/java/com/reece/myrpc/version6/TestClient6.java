package com.reece.myrpc.version6;

import com.reece.myrpc.service.BlogService;
import com.reece.myrpc.service.UserService;
import com.reece.myrpc.version6.client.NettyRpcClient;
import com.reece.myrpc.version6.proxy.ClientProxy;

/**
 * @author reece
 * @date 2021-09-06 23:36
 * @description:
 */
public class TestClient6 {


    public static void main(String[] args) {

        NettyRpcClient client = new NettyRpcClient();


        ClientProxy proxy = new ClientProxy(client);

        for (int i = 0; i < 5; i++) {

            UserService userService = proxy.getProxy(UserService.class);

            System.out.println(userService.getUserById(463543));

            BlogService blogService = proxy.getProxy(BlogService.class);

            System.out.println(blogService.getById(463543));

            System.out.println();
        }


    }
}
