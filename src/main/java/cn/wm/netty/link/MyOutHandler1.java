package cn.wm.netty.link;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.net.SocketAddress;

/**
 * @Author wangmian
 * @Date 2020/9/29
 */
public class MyOutHandler1 extends ChannelOutboundHandlerAdapter {

    @Override
    public void bind(ChannelHandlerContext ctx, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        super.bind(ctx, localAddress, promise);
        System.out.println("--------->MyOutHandler1--------->bind");
    }

    @Override
    public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress, ChannelPromise promise) throws Exception {
        super.connect(ctx, remoteAddress, localAddress, promise);
        System.out.println("--------->MyOutHandler1--------->connect");
    }

    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        super.disconnect(ctx, promise);
        System.out.println("--------->MyOutHandler1--------->disconnect");
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        super.close(ctx, promise);
        System.out.println("--------->MyOutHandler1--------->close");

    }

    @Override
    public void deregister(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        super.deregister(ctx, promise);
        System.out.println("--------->MyOutHandler1--------->deregister");

    }

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        super.read(ctx);
        System.out.println("--------->MyOutHandler1--------->read");

    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
        System.out.println("--------->MyOutHandler1--------->write");

    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        super.flush(ctx);
        System.out.println("--------->MyOutHandler1--------->flush");

    }

    @Override
    protected void ensureNotSharable() {
        super.ensureNotSharable();
        System.out.println("--------->MyOutHandler1--------->ensureNotSharable");

    }

    //1
    @Override
    public boolean isSharable() {
        System.out.println("--------->MyOutHandler1--------->isSharable");

        return super.isSharable();
    }

    //2
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        System.out.println("--------->MyOutHandler1--------->handlerAdded");

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        System.out.println("--------->MyOutHandler1--------->handlerRemoved");

    }
}
