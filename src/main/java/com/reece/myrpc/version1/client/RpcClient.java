package com.reece.myrpc.version1.client;

import com.reece.myrpc.pojo.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * @author reece
 * @date 2021-09-06 8:42
 * @description:
 */
public class RpcClient {


    public static void main(String[] args) {
        try {
            Socket client = new Socket("127.0.0.1", 8899);
            ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
            //传给server端的id
            Integer id = new Random().nextInt();
            outputStream.writeInt(id);
            outputStream.flush();

            //server端返回的数据
            User user = (User) inputStream.readObject();
            System.out.println("server端返回的数据：" + user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
