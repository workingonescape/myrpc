package com.reece.myrpc.version6.loadbalance.impl;

import com.reece.myrpc.version6.loadbalance.LoadBalance;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

/**
 * @author reece
 * @date 2021-09-06 23:25
 * @description: 随机负载均衡
 */
public class RandomLoadBalance implements LoadBalance {



    @Override
    public String getAddress(List<String> addressList) {
        System.out.println("使用了随机负载均衡");
        return addressList.get(RandomUtils.nextInt(0, addressList.size()));
    }
}
