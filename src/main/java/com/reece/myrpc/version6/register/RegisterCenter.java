package com.reece.myrpc.version6.register;

import java.net.InetSocketAddress;

/**
 * @author reece
 * @date 2021-09-06 21:38
 * @description:
 */
public interface RegisterCenter {

    void registerService(String serviceName, InetSocketAddress serviceAddress);

    InetSocketAddress getServiceAddressByServiceName(String serviceName);

}
