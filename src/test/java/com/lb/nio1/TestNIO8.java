package com.lb.nio1;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
public class TestNIO8 {
    public static void main(String[] args) {
        /* ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("文杰".getBytes());//UTF-8 3 GBK 2

        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.println("buffer.get() = " + (char)buffer.get());
        }
        buffer.clear(); */


       /*  ByteBuffer buffer = Charset.forName("UTF-8").encode("Hello");

        System.out.println("buffer.capacity() = " + buffer.capacity());//5
        System.out.println("buffer.position() = " + buffer.position());//0
        System.out.println("buffer.limit() = " + buffer.limit());//5

        // buffer.flip();

        System.out.println("---------------------------------");

        System.out.println("buffer.capacity() = " + buffer.capacity());//5
        System.out.println("buffer.position() = " + buffer.position());//0
        System.out.println("buffer.limit() = " + buffer.limit());//5


        while (buffer.hasRemaining()) {
           System.out.println("buffer.get() = " + (char) buffer.get());
        }
        buffer.clear(); */


       /* ByteBuffer buffer = StandardCharsets.UTF_8.encode("Hello");

        while (buffer.hasRemaining()) {
            System.out.println("buffer.get() = " + (char) buffer.get());
        }
        buffer.clear(); */

        ByteBuffer buffer = ByteBuffer.wrap("Hello".getBytes());
        while (buffer.hasRemaining()) {
            log.info("buffer.get() = " + (char) buffer.get());
        }
        buffer.clear();
    }

}