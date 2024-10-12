package com.lb.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MyServer1 {
    public static void main(String[] args) throws IOException {
        //1. 创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2. 设置服务端的监听端口：---》client通过网络进行访问 ip:port http://localhost:8000
        serverSocketChannel.bind(new InetSocketAddress(8000));

        List<SocketChannel> channelList = new ArrayList<>();

        ByteBuffer buffer = ByteBuffer.allocate(20);

        //3. 接受client的连接
        while (true) {
            //4. SocketChannel 代表 服务端与Client链接的一个通道
            System.out.println("等待连接服务器...");
            SocketChannel socketChannel = serverSocketChannel.accept();//阻塞 程序等待client
            System.out.println("服务器已经连接..." + socketChannel);

            channelList.add(socketChannel);

            //5. client与服务端 通信过程 NIO代码
            for (SocketChannel channel : channelList) {
                System.out.println("开始实际的数据通信....");
                channel.read(buffer);//阻塞 对应的IO通信的阻塞
                buffer.flip();
                CharBuffer decode = StandardCharsets.UTF_8.decode(buffer);
                System.out.println("decode.toString() = " + decode.toString());
                buffer.clear();
                System.out.println("通信已经结束....");

            }
        }
    }
}
