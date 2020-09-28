package cn.wm.netty.codec;

import cn.wm.entity.MyDataInfo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

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
        MyDataInfo.Student student = (MyDataInfo.Student) msg;
        System.out.println(student);
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
