package cn.wm.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author wangmian
 * @Date 2020/10/20
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    private final ByteBuf fristMessage;

    public TimeClientHandler(){
        byte[] req = "QUERY TIME ORDER".getBytes();
        fristMessage = Unpooled.buffer(req.length);
        fristMessage.writeBytes(req);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(fristMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("Now is :" + body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println(cause.getMessage());
        ctx.close();
    }
}
