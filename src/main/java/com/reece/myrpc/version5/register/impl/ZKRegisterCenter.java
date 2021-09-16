package com.reece.myrpc.version5.register.impl;

import com.reece.myrpc.version5.register.RegisterCenter;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author reece
 * @date 2021-09-06 21:39
 * @description:
 */
public class ZKRegisterCenter implements RegisterCenter {



    private String  host;

    private Integer port;


    private CuratorFramework client;

    private static final String PREFIX = "/";

    private static final String SPLIT=":";

    private static final String ROOT_PATH = "MyRpc";


    public ZKRegisterCenter() {



        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);


        this.client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181").sessionTimeoutMs(6000)
                .retryPolicy(policy).namespace(ROOT_PATH).build();

        this.client.start();

        System.out.println("zookeeper 连接成功");
    }

    @Override
    public void registerService(String serviceName, InetSocketAddress serviceAddress) {

        try {
            // serviceName创建成永久节点，服务提供者下线时，不删服务名，只删地址
            if (client.checkExists().forPath(PREFIX + serviceName) == null) {
                client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(PREFIX + serviceName);
            }
            // 路径地址，一个代表一个节点
            String path = PREFIX + serviceName + PREFIX + encode2ServiceAddress(serviceAddress);
            //临时节点 服务器下线就删除节点
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("该服务已存在");
        }
    }



    @Override
    public InetSocketAddress getServiceAddressByServiceName(String serviceName) {

        try {
            List<String> pathList = client.getChildren().forPath(PREFIX + serviceName);
            //这里先使用第一个  后边使用负载均衡策略
            String serviceAddress = pathList.get(0);
            return decode2IntSocketAddress(serviceAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }




    // 地址 -> XXX.XXX.XXX.XXX:port 字符串
    private String encode2ServiceAddress(InetSocketAddress serviceAddress) {
        if (serviceAddress == null) {
            return null;
        }
        return serviceAddress.getHostName() + SPLIT + serviceAddress.getPort();
    }


    private InetSocketAddress decode2IntSocketAddress(String serviceAddress) {
        if (StringUtils.isEmpty(serviceAddress)) {
            return null;
        }
        String[] strs = serviceAddress.split(SPLIT);
        return new InetSocketAddress(strs[0], Integer.parseInt(strs[1]));
    }




}
