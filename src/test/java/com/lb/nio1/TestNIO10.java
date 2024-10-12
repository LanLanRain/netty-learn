package com.lb.nio1;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@Slf4j
public class TestNIO10 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(50);
        buffer.put("Hi Jerry\nTom love y".getBytes());
        doLineSplit(buffer);
        buffer.put("ou\nDo you like him?\n".getBytes());
        doLineSplit(buffer);
    }

    private static void doLineSplit(ByteBuffer buffer) {
        buffer.flip();
        for (int i = 0; i < buffer.limit(); i++) {
            if (buffer.get(i) == '\n') {
                int length = i + 1 - buffer.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                for (int j = 0; j < length; j++) {
                    target.put(buffer.get());
                }

                target.flip();
                log.info("StandardCharsets.UTF_8.decode(target) = {}", StandardCharsets.UTF_8.decode(target));
            }
        }
        buffer.compact();
    }
}
