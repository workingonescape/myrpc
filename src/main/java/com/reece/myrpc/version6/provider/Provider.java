package com.reece.myrpc.version6.provider;

/**
 * @author reece
 * @date 2021-09-06 16:02
 * @description:
 */
public interface Provider {


    void setInterface(Object o);

    Object getInterface(String interfaceName);
}
