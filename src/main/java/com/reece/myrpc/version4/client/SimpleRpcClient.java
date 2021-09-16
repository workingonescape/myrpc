package com.reece.myrpc.version4.client;

import com.reece.myrpc.request.Request;
import com.reece.myrpc.response.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author reece
 * @date 2021-09-06 15:51
 * @description:
 */
public class SimpleRpcClient implements RpcClient {


    private String host;

    private Integer port;

    public SimpleRpcClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Response sendRequest(Request request) {

        try {
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

}
