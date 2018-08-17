package com.fengquanwei.hello.javase.jdk.io.socket.iosocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端
 *
 * @author fengquanwei
 * @create 2017/12/19 14:22
 **/
public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 8088);
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                String output = scanner.nextLine();
                System.out.println("Client: " + output);

                dataOutputStream.writeUTF(output);

                String input = dataInputStream.readUTF();
                System.out.println("Server: " + input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
