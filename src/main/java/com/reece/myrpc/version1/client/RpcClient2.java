package com.reece.myrpc.version1.client;

import com.reece.myrpc.pojo.User;
import com.reece.myrpc.request.RequestProxy;
import com.reece.myrpc.service.UserService;

/**
 * @author reece
 * @date 2021-09-06 9:30
 * @description:
 */
public class RpcClient2 {


    public static void main(String[] args) {

        RequestProxy requestProxy = new RequestProxy("127.0.0.1", 8852);

        UserService proxy = requestProxy.getProxy(UserService.class);

        // 服务的方法1
        User userByUserId = proxy.getUserById(10);
        System.out.println("从服务端得到的user为：" + userByUserId);
        // 服务的方法2
        User user = User.builder().name("张三").id(100).sex(2).build();
        Boolean success = proxy.save(user);
        System.out.println("向服务端插入数据："+success);


    }

}
