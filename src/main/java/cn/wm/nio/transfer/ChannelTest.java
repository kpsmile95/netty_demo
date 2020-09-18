package cn.wm.nio.transfer;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @Author wangmian
 * @Date 2020/9/15
 */
public class ChannelTest {

    @Test
    public void transferFromMethod(){
        try (
                FileInputStream fis = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\app.txt");
                FileOutputStream fos = new FileOutputStream("C:\\Users\\Lenovo\\Desktop\\app2.txt");
                FileChannel fromChannel = fis.getChannel();
                FileChannel toChannel = fos.getChannel();
        ) {
            // 定义position的位置为初始位置0
            long position = 0;
            // 获取文件总的字节数
            final long size = fromChannel.size();
            // 从fromChannel通道缓冲区position位置开始读取count字节数写入到目标通道toChannel中
            toChannel.transferFrom(fromChannel, position, size);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void transferToMethod(){
        try (
                FileInputStream fis = new FileInputStream("C:\\Users\\Lenovo\\Desktop\\app.txt");
                FileOutputStream fos = new FileOutputStream("C:\\Users\\Lenovo\\Desktop\\app2.txt");
                FileChannel fromChannel = fis.getChannel();
                FileChannel toChannel = fos.getChannel();
        ) {
            // 定义position的位置为初始位置0
            long position = 0;
            // 获取文件总的字节数
            final long size = fromChannel.size();
            // 从fromChannel通道缓冲区position位置开始读取count字节数写入到目标通道toChannel中
            fromChannel.transferTo( position, size,toChannel);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
