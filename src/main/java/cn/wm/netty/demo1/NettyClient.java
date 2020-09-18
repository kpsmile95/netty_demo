package cn.wm.netty.demo1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author wangmian
 * @Date 2020/9/17
 */
public class NettyClient {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            System.out.println("client is ok...");
            ChannelFuture sync = bootstrap.connect("127.0.0.1", 9999).sync();
            sync.channel().closeFuture().sync();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            group.shutdownGracefully();
        }
    }
}
