package com.reece.myrpc.version4;

import com.reece.myrpc.pojo.Blog;
import com.reece.myrpc.pojo.User;
import com.reece.myrpc.service.BlogService;
import com.reece.myrpc.service.UserService;
import com.reece.myrpc.version4.client.NettyRpcClient;
import com.reece.myrpc.version4.proxy.ClientProxy;

/**
 * @author reece
 * @date 2021-09-06 18:03
 * @description:
 */
public class TestClient4 {


    public static void main(String[] args) {


        NettyRpcClient client = new NettyRpcClient("127.0.0.1", 8899);



        ClientProxy proxy = new ClientProxy(client);

        UserService userService = proxy.getProxy(UserService.class);

        User user = userService.getUserById(1256);

        System.out.println(user);

        BlogService blogService = proxy.getProxy(BlogService.class);

        Blog blog = blogService.getById(2566);

        System.out.println(blog);

    }
}
