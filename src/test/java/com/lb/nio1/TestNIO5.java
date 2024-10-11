package com.lb.nio1;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Slf4j
public class TestNIO5 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c'});

        buffer.flip();
        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            log.info("char b is {}", (char) b);
        }
    }
}
