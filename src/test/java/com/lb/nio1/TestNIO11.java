package com.lb.nio1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class TestNIO11 {
    public static void main(String[] args) throws IOException {
        FileChannel channel = new FileOutputStream("data2.txt").getChannel();
        ByteBuffer buffer = StandardCharsets.UTF_8.encode("Anlan is a pig");

        channel.write(buffer);
    }
}
