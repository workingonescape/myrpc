package com.reece.myrpc.version1.server;

import com.reece.myrpc.pojo.User;
import com.reece.myrpc.service.UserService;
import com.reece.myrpc.service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author reece
 * @date 2021-09-06 8:47
 * @description:
 */
public class RpcServer {


    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        try {
            //监听8899端口
            ServerSocket serverSocket = new ServerSocket(8899);
            System.out.println("服务端启动了");
            //BIO方式监听
            while (true) {
                Socket socket = serverSocket.accept();
                //开启线程去处理
                new Thread(()->{
                    try {
                        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                        //读取客户端传入的id
                        Integer id = inputStream.readInt();
                        //调用service方法
                        User user = userService.getUserById(id);
                        //返回给客户端
                        outputStream.writeObject(user);
                        outputStream.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
