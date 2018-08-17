package com.fengquanwei.hello.javase.jdk.database.transaction;

/**
 * 客户端线程
 *
 * @author fengquanwei
 * @create 2017/12/1 18:05
 **/
public class ClientThread extends Thread {
    private ProductService productService;

    public ClientThread(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        productService.updateProductPrice(1, 500);
    }
}
