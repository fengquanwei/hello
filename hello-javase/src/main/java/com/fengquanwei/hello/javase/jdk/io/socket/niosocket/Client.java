package com.fengquanwei.hello.javase.jdk.io.socket.niosocket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 客户端
 *
 * @author fengquanwei
 * @create 2017/12/19 15:50
 **/
public class Client {
    private Selector selector = null;
    private SocketChannel socketChannel = null;
    private String name = "";
    private Charset charset = Charset.forName("UTF-8");

    public void init() throws IOException {
        selector = Selector.open();

        socketChannel = SocketChannel.open(new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 8088));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);

        System.out.println("【系统】客户端已启动");

        // 启动新线程读取服务端发来的数据
        new Thread(new ClientThread()).start();

        // 在主线程中将输入的数据发送到服务端
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if ("".equals(line)) {
                continue;
            }

            if ("".equals(name)) {
                name = line;
                line = name + Server.USER_CONTENT_SPILIT;
            } else {
                line = name + Server.USER_CONTENT_SPILIT + line;
            }

            socketChannel.write(charset.encode(line));
        }
    }

    private class ClientThread implements Runnable {
        public void run() {
            try {
                while (true) {
                    int readyChannels = selector.select();
                    if (readyChannels == 0) {
                        continue;
                    }

                    Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
                    while (selectionKeyIterator.hasNext()) {
                        SelectionKey selectionKey = selectionKeyIterator.next();
                        selectionKeyIterator.remove();

                        if (selectionKey.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                            StringBuffer content = new StringBuffer();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            while (socketChannel.read(buffer) > 0) {
                                buffer.flip();
                                content.append(charset.decode(buffer));
                            }

                            // 若系统发送通知名字已经存在，则需要换个昵称
                            if (Server.USER_EXIST.equals(content.toString())) {
                                name = "";
                            }

                            System.out.println(content);

                            selectionKey.interestOps(SelectionKey.OP_READ);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Client().init();
    }
}
