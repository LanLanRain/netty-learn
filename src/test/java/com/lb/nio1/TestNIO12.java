package com.lb.nio1;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TestNIO12 {
    public static void main(String[] args) throws IOException {
        //data1---data2
       /* FileInputStream inputStream = new FileInputStream("data1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("data2.txt");

        byte[] buffer = new byte[1024];

        while (true) {
            int read = inputStream.read(buffer);
            if (read == -1) break;
            fileOutputStream.write(buffer, 0, read);
        } */

        /* FileInputStream inputStream = new FileInputStream("data1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("data2.txt");

        IOUtils.copy(inputStream, fileOutputStream); */

        FileChannel from = new FileInputStream("data1.txt").getChannel();
        FileChannel to = new FileOutputStream("data2.txt").getChannel();

        //传输数据上线的 2G-1
        // 若果实际文件大小就是超过2G 如何进行文件的copy
        //from.transferTo(0, from.size(), to);

        long left = from.size();
        while (left > 0) {
            left = left - from.transferTo(from.size() - left, left, to);
        }
    }
}
