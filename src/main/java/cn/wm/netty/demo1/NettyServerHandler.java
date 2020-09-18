package cn.wm.netty.demo1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;

/**
 * @Author wangmian
 * @Date 2020/9/17
 * 说明
 * 1.我们自定义一个Handler需要继续netty规定好的某个HandlerAdapter(规范)
 * 2.这时我们自定义一个Handler,才能称为一个handler
 * */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //读取数据实际(这里我们可以读取客户端发送的消息)
    /**
     * 1.ChannelHandlerContextctx:上下文对象,含有管道pipeline,通道channel,地址
     * 2.Objectmsg:就是客户端发送的数据默认Object
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务器读取线程"+Thread.currentThread().getName());
        System.out.println("serverctx="+ctx);
        System.out.println("看看channel和pipeline的关系");
        Channel channel=ctx.channel();
        ChannelPipeline pipeline=ctx.pipeline();
        //本质是一个双向链接,出站入站
        // 将msg转成一个ByteBuf
        // ByteBuf是Netty提供的，不是NIO的
        ByteBuf buf=(ByteBuf)msg;
        System.out.println("客户端发送消息是:"+buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址:"+channel.remoteAddress());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,client...(>~<)", CharsetUtil.UTF_8));
    }

    /**
     * 处理异常，一般是需要关闭通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
