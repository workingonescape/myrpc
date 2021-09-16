package com.reece.myrpc.version2.server.provider;

/**
 * @author reece
 * @date 2021-09-06 13:36
 * @description:
 */
public interface Provider {


    void setProvider(Object service);


    Object getProvider(String interfaceName);
}
