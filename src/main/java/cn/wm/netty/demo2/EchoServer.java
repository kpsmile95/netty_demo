package cn.wm.netty.demo2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * 服务器的主要代码组件：
 * EchoServerHandler 实现了业务逻辑；
 * main()方法引导了服务器；
 * 引导过程中所需要的步骤如下：
 * 创建一个 ServerBootstrap 的实例以引导和绑定服务器；
 * 创建并分配一个 NioEventLoopGroup 实例以进行事件的处理，如接受新连接以及读/写数据；
 * 指定服务器绑定的本地的 InetSocketAddress；
 * 使用一个 EchoServerHandler 的实例初始化每一个新的 Channel；
 * 调用 ServerBootstrap.bind()方法以绑定服务器。
 * @Author wangmian
 * @Date 2020/9/18
 */
public class EchoServer {

    public static void main(String[] args) throws Exception {
        final EchoServerHandler handler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(9999))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(handler);
                        }
                    });
            //异步地绑定服务器；调用 sync()方法阻塞等待直到绑定完成
            ChannelFuture sync = bootstrap.bind().sync();
            //获取 Channel 的CloseFuture，并且 阻 塞 当 前 线程直到它完成
            sync.channel().closeFuture().sync();
        } finally {
            //关闭 EventLoopGroup，释放所有的资源
            group.shutdownGracefully();
        }
    }
}
