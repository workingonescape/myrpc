package com.reece.myrpc.version6.client;

import com.reece.myrpc.request.Request;
import com.reece.myrpc.response.Response;

/**
 * @author reece
 * @date 2021-09-06 15:50
 * @description:
 */
public interface RpcClient {




    Response sendRequest(Request request);

    void close();
}
