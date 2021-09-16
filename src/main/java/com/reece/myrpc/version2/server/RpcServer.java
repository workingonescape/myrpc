package com.reece.myrpc.version2.server;

/**
 * @author reece
 * @date 2021-09-06 13:22
 * @description:
 */
public interface RpcServer {


    void start(int port);

    void shutdown();
}
