package com.fengquanwei.hello.javase.jdk.database.transaction;

/**
 * 客户端
 *
 * @author fengquanwei
 * @create 2017/12/1 18:06
 **/
public class Client {
    public static void main(String[] args) {
        // 单线程测试
//        ProductService productService = new ProductServiceImpl();
//        productService.updateProductPrice(1, 3000);

        // 多线程测试（一个线程可能关闭另一个线程的连接，如果不适用线程局部变量的话）
        for (int i = 0; i < 10; i++) {
            ProductService productService = new ProductServiceImpl();
            ClientThread clientThread = new ClientThread(productService);
            clientThread.start();
        }
    }
}
