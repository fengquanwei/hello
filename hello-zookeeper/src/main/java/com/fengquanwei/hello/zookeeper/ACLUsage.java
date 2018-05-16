package com.fengquanwei.hello.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * 权限控制用法
 *
 * @author fengquanwei
 * @create 2018/5/16 10:19
 **/
public class ACLUsage {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        // 使用权限控制创建节点
        ZooKeeper zooKeeperWithAuth = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 5000, null);
        zooKeeperWithAuth.addAuthInfo("digest", "foo:true".getBytes());
        String authPath = zooKeeperWithAuth.create("/path_auth", "data_auth".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
        System.out.println("Create auth path: " + authPath);

        // 无权限访问
        try {
            ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 5000, null);
            byte[] data = zooKeeper.getData(authPath, true, null);
            System.out.println("ZooKeeper Get auth path: " + authPath + ", data: " + new String(data));
        } catch (Exception e) {
            System.out.println("ZooKeeper Get auth path: " + authPath + ", exception: " + e);
        }

        // 有权限访问
        byte[] data = zooKeeperWithAuth.getData(authPath, true, null);
        System.out.println("ZooKeeperWithAuth Get auth path: " + authPath + ", data: " + new String(data));
    }
}
