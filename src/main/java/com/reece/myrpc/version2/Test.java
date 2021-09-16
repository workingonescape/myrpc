package com.reece.myrpc.version2;

import com.reece.myrpc.pojo.Blog;
import com.reece.myrpc.pojo.User;
import com.reece.myrpc.service.BlogService;
import com.reece.myrpc.service.UserService;
import com.reece.myrpc.version2.client.ClientProxy;

import java.util.List;

/**
 * @author reece
 * @date 2021-09-06 13:52
 * @description:
 */
public class Test {

    public static void main(String[] args) {

        ClientProxy proxy = new ClientProxy("127.0.0.1", 8899);

        UserService userService = proxy.getProxy(UserService.class);

        User user = userService.getUserById(156);
        System.out.println("user" + user);

        BlogService blogService = proxy.getProxy(BlogService.class);

        List<Blog> blogs = blogService.getAllUser(user);

        for (Blog blog : blogs) {
            System.out.println(blog);

        }
    }
}
