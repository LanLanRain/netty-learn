package com.lb.nio1;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestNIO3 {
    public static void main(String[] args) {

        try (FileChannel channel = FileChannel.open(Paths.get("data1.txt"), StandardOpenOption.READ)) {

            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                int read = channel.read(buffer);
                if (read == -1) break;

                buffer.flip();
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    System.out.println("(char)b = " + (char) b);
                }

                buffer.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}