package com.fengquanwei.hello.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * ZkClient 用法
 *
 * @author fengquanwei
 * @create 2018/5/16 11:32
 **/
public class ZkClientUsage {
    public static void main(String[] args) {
        // 创建会话
        ZkClient zkClient = new ZkClient("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183/test", 5000);

        String path = "/p";

        // 订阅子节点变更
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChildren) throws Exception {
                System.out.println("【IZkChildListener】parentPath: " + parentPath + ", currentChildren: " + currentChildren);
            }
        });

        // 订阅节点变更
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String path, Object data) throws Exception {
                System.out.println("【IZkDataListener】handleDataChange, path: " + path + ", data: " + data);
            }

            @Override
            public void handleDataDeleted(String path) throws Exception {
                System.out.println("【IZkDataListener】handleDataDeleted, path: " + path);
            }
        });

        // 递归创建节点
        zkClient.createPersistent(path + "/a/b/c", true);

        // 更新节点数据
        zkClient.writeData(path, "123");

        // 读取节点数据
        Object data = zkClient.readData(path);
        System.out.println("path: " + path + ", data: " + data);

        // 检查节点是否存在
        boolean exists = zkClient.exists(path);
        System.out.println("path: " + path + ", exists: " + exists);

        // 获取子节点
        List<String> children = zkClient.getChildren(path);
        System.out.println("path: " + path + ", children: " + children);

        // 递归删除节点
        zkClient.deleteRecursive(path);
    }
}
