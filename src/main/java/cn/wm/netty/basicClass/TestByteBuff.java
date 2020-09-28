package cn.wm.netty.basicClass;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @Author wangmian
 * @Date 2020/9/28
 */
public class TestByteBuff {
    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }

        System.out.println(buffer.capacity());
        System.out.println(buffer.writerIndex());
        System.out.println(buffer.readerIndex());

 /*       for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.getByte(i));
        }*/

        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.readByte());
        }


    }
}
