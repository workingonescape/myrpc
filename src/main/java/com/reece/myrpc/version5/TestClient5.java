package com.reece.myrpc.version5;

import com.reece.myrpc.service.UserService;
import com.reece.myrpc.version5.client.NettyRpcClient;
import com.reece.myrpc.version5.client.RpcClient;
import com.reece.myrpc.version5.client.SimpleRpcClient;
import com.reece.myrpc.version5.proxy.ClientProxy;

/**
 * @author reece
 * @date 2021-09-06 22:30
 * @description:
 */
public class TestClient5 {


    public static void main(String[] args) {

        RpcClient client = new NettyRpcClient();

        ClientProxy proxy = new ClientProxy(client);

        UserService userService = proxy.getProxy(UserService.class);

        System.out.println(userService.getUserById(465343));

    }
}
