package com.lb.nio1;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class TestNIO1 {
    public static void main(String[] args) throws IOException {
        //1 创建Channel通道  FileChannel
        FileChannel channel = new FileInputStream("data1.txt").getChannel();

        //2 创建缓冲区
        //1234567890
        ByteBuffer buffer = ByteBuffer.allocate(10);

        while (true) {
            //3把通道内获取的文件数据，写入缓冲区
            int read = channel.read(buffer);

            if (read == -1) break;

            //4.程序读取buffer内容，后续的操作。设置buffer为读模式。
            buffer.flip();

            //5.循环读取缓冲区中的数据
            while (buffer.hasRemaining()) {
                byte b = buffer.get();
                System.out.println("(char)b = " + (char) b);
            }

            //6. 设置buffer的写模式
            buffer.clear();
        }
    }
}
