package com.reece.myrpc.version5.provider.impl;

import com.reece.myrpc.version5.provider.Provider;
import com.reece.myrpc.version5.register.RegisterCenter;
import com.reece.myrpc.version5.register.impl.ZKRegisterCenter;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author reece
 * @date 2021-09-06 21:59
 * @description:
 */
public class ServiceProvider implements Provider {


    private String host;


    private Integer port;

    private Map<String, Object> serviceMap;


    private RegisterCenter registerCenter;

    public ServiceProvider(String host, Integer port) {
        // 需要传入服务端自身的服务的网络地址
        this.host = host;
        this.port = port;
        this.serviceMap = new HashMap<>();
        this.registerCenter = new ZKRegisterCenter();

    }

    @Override
    public void setInterface(Object o) {
        Class<?>[] interfaces = o.getClass().getInterfaces();
        for (Class<?> clazz : interfaces) {
            //本机的映射表
            serviceMap.put(clazz.getName(), o);
            //在注册中心注册服务 host, port
            registerCenter.registerService(clazz.getName(), new InetSocketAddress(host, port));
        }
    }

    @Override
    public Object getInterface(String interfaceName) {
        return serviceMap.get(interfaceName);
    }

}
