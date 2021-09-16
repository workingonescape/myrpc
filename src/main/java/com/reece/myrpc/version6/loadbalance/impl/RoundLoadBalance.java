package com.reece.myrpc.version6.loadbalance.impl;

import com.reece.myrpc.version6.loadbalance.LoadBalance;

import java.util.List;

/**
 * @author reece
 * @date 2021-09-06 23:27
 * @description: 轮询负载均衡
 */
public class RoundLoadBalance implements LoadBalance {


    private Integer index = -1;


    @Override
    public String getAddress(List<String> addressList) {
        index++;
        index = index % addressList.size();
        System.out.println("使用了轮询负载均衡");
        return addressList.get(index);
    }

}
