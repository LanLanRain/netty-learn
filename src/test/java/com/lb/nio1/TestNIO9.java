package com.lb.nio1;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

public class TestNIO9 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("汉武帝".getBytes());

        buffer.flip();
        CharBuffer result = StandardCharsets.UTF_8.decode(buffer);
        System.out.println("result.toString() = " + result.toString());
    }
}
