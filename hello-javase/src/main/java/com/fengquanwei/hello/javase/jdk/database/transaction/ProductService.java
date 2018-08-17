package com.fengquanwei.hello.javase.jdk.database.transaction;

/**
 * 产品服务
 *
 * @author fengquanwei
 * @create 2017/12/1 17:49
 **/
public interface ProductService {
    void updateProductPrice(long productId, int price);
}
