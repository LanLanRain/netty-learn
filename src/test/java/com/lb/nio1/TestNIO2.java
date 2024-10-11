package com.lb.nio1;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class TestNIO2 {
    public static void main(String[] args) {
        FileChannel channel = null;
        try {
            channel = new RandomAccessFile("data1.txt", "rw").getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(15);
            while (true) {
                int read = channel.read(buffer);
                if (read == -1) {
                    break;
                }
                buffer.flip();
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    log.info("(char)b = {}", b);
                }
                buffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
