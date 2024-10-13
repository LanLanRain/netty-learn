package com.lb.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class MyServer3 {
    public static void main(String[] args) throws IOException {
        // 打开一个ServerSocketChannel，用于监听客户端连接
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定到8000端口
        serverSocketChannel.bind(new InetSocketAddress(8000));
        // 设置为非阻塞模式，以便使用Selector进行IO多路复用
        serverSocketChannel.configureBlocking(false);

        // 创建一个Selector，用于监听多个Channel的事件
        Selector selector = Selector.open();

        // 将ServerSocketChannel注册到Selector上，监听OP_ACCEPT事件
        SelectionKey selectionKey = serverSocketChannel.register(selector, 0, null);
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);

        System.out.println("MyServler3.main");

        // 循环监听Selector上的事件
        while (true) {
            // 等待事件发生，阻塞直到有事件发生
            selector.select();

            System.out.println("-------------------------");

            // 获取所有发生事件的SelectionKey集合
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                // 处理每个SelectionKey
                SelectionKey key = iterator.next();
                // 从SelectedKeys集合中移除当前处理的SelectionKey，防止重复处理
                iterator.remove();

                if (key.isAcceptable()) {
                    // 处理新的客户端连接请求
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    // 接受客户端连接，返回一个SocketChannel
                    SocketChannel sc = channel.accept();
                    // 设置SocketChannel为非阻塞模式
                    sc.configureBlocking(false);
                    // 将新的SocketChannel注册到Selector上，监听OP_READ事件
                    SelectionKey sckey = sc.register(selector, 0, null);
                    sckey.interestOps(SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    // 处理客户端的读请求
                    try {
                        // 获取发生读事件的SocketChannel
                        SocketChannel sc = (SocketChannel) key.channel();
                        // 创建一个5字节的ByteBuffer，用于读取数据
                        ByteBuffer buffer = ByteBuffer.allocate(5);
                        // 从SocketChannel中读取数据到ByteBuffer
                        int read = sc.read(buffer);
                        if (read == -1) {
                            // 如果读取到-1，表示客户端关闭了连接，取消该SelectionKey
                            key.cancel();
                        } else {
                            // 翻转ByteBuffer，准备读取数据
                            buffer.flip();
                            // 将ByteBuffer中的数据解码为字符串并输出
                            System.out.println("Charset.defaultCharset().decode(buffer).toString() = " + Charset.defaultCharset().decode(buffer).toString());
                        }
                    } catch (IOException e) {
                        // 发生IO异常，取消该SelectionKey
                        e.printStackTrace();
                        key.cancel();
                    }
                }
            }
        }
    }
}
