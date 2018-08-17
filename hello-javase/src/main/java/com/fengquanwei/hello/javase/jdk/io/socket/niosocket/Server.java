package com.fengquanwei.hello.javase.jdk.io.socket.niosocket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 服务端
 *
 * @author fengquanwei
 * @create 2017/12/19 15:03
 **/
public class Server {
    private Selector selector = null;
    private static HashSet<String> users = new HashSet<>();
    private Charset charset = Charset.forName("UTF-8");
    public static String USER_EXIST = "【系统】该用户名已存在，请重新输入：";
    public static String USER_CONTENT_SPILIT = "#";

    public void init() throws IOException {
        selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 8088));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务端已启动");

        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }

            Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
            while (selectionKeyIterator.hasNext()) {
                SelectionKey selectionKey = selectionKeyIterator.next();
                selectionKeyIterator.remove();

                dealWithSelectionKey(serverSocketChannel, selectionKey);
            }
        }
    }

    public void dealWithSelectionKey(ServerSocketChannel serverSocketChannel, SelectionKey selectionKey) throws IOException {
        if (selectionKey.isAcceptable()) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);

            System.out.println("客户已连接：" + socketChannel.getRemoteAddress());

            socketChannel.write(charset.encode("【系统】请输入用户名："));

            selectionKey.interestOps(SelectionKey.OP_ACCEPT);
        }

        if (selectionKey.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            StringBuilder content = new StringBuilder();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            try {
                while (socketChannel.read(buffer) > 0) {
                    buffer.flip();
                    content.append(charset.decode(buffer));
                }

                System.out.println("收到客户[" + socketChannel.getRemoteAddress() + "]的消息：" + content);

                selectionKey.interestOps(SelectionKey.OP_READ);
            } catch (IOException e) {
                selectionKey.cancel();
                if (selectionKey.channel() != null) {
                    selectionKey.channel().close();
                }
            }

            if (content.length() > 0) {
                String[] arrayContent = content.toString().split(USER_CONTENT_SPILIT);

                // 注册用户
                if (arrayContent != null && arrayContent.length == 1) {
                    String name = arrayContent[0];
                    if (users.contains(name)) {
                        socketChannel.write(charset.encode(USER_EXIST));
                    } else {
                        users.add(name);
                        int num = OnlineNum(selector);
                        String message = "欢迎【" + name + "】来到聊天室！当前在线人数：" + num;
                        BroadCast(selector, null, message);
                    }
                }

                // 注册完了，发送消息
                else if (arrayContent != null && arrayContent.length > 1) {
                    String name = arrayContent[0];
                    String message = content.substring(name.length() + USER_CONTENT_SPILIT.length());
                    message = "【" + name + "】" + message;
                    if (users.contains(name)) {
                        // 不回发给发送此内容的客户端
                        BroadCast(selector, socketChannel, message);
                    }
                }
            }

        }
    }

    public static int OnlineNum(Selector selector) {
        int num = 0;
        for (SelectionKey selectionKey : selector.keys()) {
            Channel channel = selectionKey.channel();

            if (channel instanceof SocketChannel) {
                num++;
            }
        }
        return num;
    }

    public void BroadCast(Selector selector, SocketChannel except, String content) throws IOException {
        // 广播数据到所有的 SocketChannel 中
        for (SelectionKey selectionKey : selector.keys()) {
            Channel channel = selectionKey.channel();
            // 如果except不为空，不回发给发送此内容的客户端
            if (channel instanceof SocketChannel && channel != except) {
                SocketChannel socketChannel = (SocketChannel) channel;
                socketChannel.write(charset.encode(content));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Server().init();
    }
}