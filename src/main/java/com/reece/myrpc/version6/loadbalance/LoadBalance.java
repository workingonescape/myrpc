package com.reece.myrpc.version6.loadbalance;

import java.util.List;

/**
 * @author reece
 * @date 2021-09-06 23:24
 * @description:
 */
public interface LoadBalance {


    String getAddress(List<String> addressList);
}
