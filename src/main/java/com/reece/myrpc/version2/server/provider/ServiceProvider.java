package com.reece.myrpc.version2.server.provider;

import java.util.HashMap;
import java.util.Map;


/**
 * @author reece
 * @date 2021-09-06 13:31
 * @description:
 */
public class ServiceProvider implements Provider {


    /**
     * 一个实现类可能实现多个接口
     */
    private Map<String, Object> serviceMap ;

    public ServiceProvider() {
        this.serviceMap = new HashMap<>();
    }

    @Override
    public void setProvider(Object service) {
        if (service == null) {
            throw new RuntimeException("service can not be null");
        }
        Class<?>[] interfaces = service.getClass().getInterfaces();
        for (Class clz : interfaces) {
            serviceMap.put(clz.getName(), service);

        }
    }

    @Override
    public Object getProvider(String interfaceName) {
        return serviceMap.get(interfaceName);
    }

}
