package com.reece.myrpc.version4.provider.impl;

import com.reece.myrpc.version4.provider.Provider;

import java.util.HashMap;
import java.util.Map;

/**
 * @author reece
 * @date 2021-09-06 16:03
 * @description:
 */
public class ServiceProvider implements Provider {

    private Map<String, Object> map;





    public ServiceProvider() {
        this.map = new HashMap<>();
    }

    @Override
    public void setInterface(Object o) {

        if (o == null) {
            throw new RuntimeException("o can not be null");
        }
        Class<?>[] interfaces = o.getClass().getInterfaces();

        for (Class clazz : interfaces) {
            map.put(clazz.getName(), o);
        }
    }

    @Override
    public Object getInterface(String interfaceName) {
        return map.get(interfaceName);
    }

}
