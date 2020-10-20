package cn.wm.netty.link;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

/**
 * @Author wangmian
 * @Date 2020/9/29
 */
public class MyServerInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new MyInHandler1());
        pipeline.addLast(new MyInHandler2());
        pipeline.addLast(new MyOutHandler1());
        pipeline.addLast(new MyOutHandler2());
    }
}
