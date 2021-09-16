package com.reece.myrpc.version4.server;

import com.reece.myrpc.version4.provider.Provider;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * @author reece
 * @date 2021-09-06 16:27
 * @description:
 */
public class NettyRpcServer implements RpcServer {


    private Provider provider;


    public NettyRpcServer(Provider provider) {
        this.provider = provider;
    }

    @Override
    public void start(Integer port) {

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        System.out.println("netty 服务端启动");

        try {
            //启动netty服务器
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            //初始化
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new NettyServerInitializer(provider));
            //同步阻塞
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            //死循环监听
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() {

    }
}
