package com.fengquanwei.hello.javase.jdk.io.socket.iosocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 服务端
 *
 * @author fengquanwei
 * @create 2017/12/19 14:18
 **/
public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8088, 50, InetAddress.getByName("127.0.0.1"));
             Socket socket = serverSocket.accept();
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                String input = dataInputStream.readUTF();
                System.out.println("Client: " + input);

                String output = scanner.nextLine();
                System.out.println("Server: " + output);

                dataOutputStream.writeUTF(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
