package cn.wm.netty.heartBeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Author wangmian
 * @Date 2020/9/28
 */
public class HBServer {
    public static void main(String[] args) throws Exception{
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            /**
                             * 1.IdleStateHandler是netty提供的处理空闲状态的处理器
                             * 2.readerIdleTime：表示多长时间没有读，就会发送一个心跳检测包检测是否连接
                             * 3.writerIdleTime：表示多长时间没有写，就会发送一个心跳检测包检测是否连接
                             * 4.allIdleTime：表示多长时间没有读写，就会发送一个心跳检测包检测是否连接
                             */
                            pipeline.addLast(new IdleStateHandler(13, 5, 7, TimeUnit.SECONDS));
                            pipeline.addLast(new HBServerHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind(19999).sync();
            future.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
