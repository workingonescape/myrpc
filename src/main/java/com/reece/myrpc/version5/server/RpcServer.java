package com.reece.myrpc.version5.server;

/**
 * @author reece
 * @date 2021-09-06 16:00
 * @description:
 */
public interface RpcServer {


    void start(Integer port);


    void shutdown();
}
