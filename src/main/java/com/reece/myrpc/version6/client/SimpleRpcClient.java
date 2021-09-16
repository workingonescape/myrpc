package com.reece.myrpc.version6.client;

import com.reece.myrpc.request.Request;
import com.reece.myrpc.response.Response;
import com.reece.myrpc.version6.register.RegisterCenter;
import com.reece.myrpc.version6.register.impl.ZKRegisterCenter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author reece
 * @date 2021-09-06 15:51
 * @description:
 */
public class SimpleRpcClient implements RpcClient {


    private RegisterCenter registerCenter;




    public SimpleRpcClient(String host,Integer port) {
        this.registerCenter = new ZKRegisterCenter();
    }

    @Override
    public Response sendRequest(Request request) {

        try {

            InetSocketAddress socketAddress = registerCenter.getServiceAddressByServiceName(request.getInterfaceName());

            String host = socketAddress.getHostName();

            Integer port = socketAddress.getPort();

            Socket socket = new Socket(host, port);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            outputStream.writeObject(request);

            outputStream.flush();

            Response response = (Response) inputStream.readObject();

            return response;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void close() {

    }

}
