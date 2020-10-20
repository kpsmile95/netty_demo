package cn.wm.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @Author wangmian
 * @Date 2020/10/19
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private int count;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        //String body = new String(req, "UTF-8");
        String body = new String(req, "UTF-8")
                .substring(0,req.length-System.getProperty("line.separator").length());

//        System.out.println("time server : " + body);
        System.out.println("time server receive order : " + body+" ; ");

        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
