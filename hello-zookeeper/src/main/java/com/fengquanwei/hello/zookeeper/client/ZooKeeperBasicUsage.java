package com.fengquanwei.hello.zookeeper.client;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 基本用法
 *
 * @author fengquanwei
 * @create 2018/5/15 15:57
 **/
public class ZooKeeperBasicUsage {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        System.out.println("========== Connect ZooKeeper ==========");

        // 创建会话
        CountDownLatch countDownLatch = new CountDownLatch(1);
        MyWatcher myWatcher = new MyWatcher(countDownLatch);
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183/test", 5000, myWatcher);
        System.out.println("ZooKeeper state: " + zooKeeper.getState());
        countDownLatch.await();
        System.out.println("ZooKeeper session established");

        // 复用会话
        countDownLatch = new CountDownLatch(1);
        myWatcher = new MyWatcher(countDownLatch);
        long sessionId = zooKeeper.getSessionId();
        byte[] sessionPasswd = zooKeeper.getSessionPasswd();
        zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183/test", 5000, myWatcher, sessionId, sessionPasswd);
        System.out.println("ZooKeeper reconnect state: " + zooKeeper.getState());
        countDownLatch.await();
        System.out.println("ZooKeeper session reconnected");

        System.out.println("========== Create Path ==========");

        // 同步创建节点
        String path1 = zooKeeper.create("/path_ephemeral", "data_ephemeral".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Create node: " + path1);
        String path2 = zooKeeper.create("/path_ephemeral_sequential", "data_ephemeral_sequential".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Create node: " + path2);

        // 异步创建节点
        String createContext = "Create Path";
        zooKeeper.create("/path_persistent", "data_persistent".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new MyStringCallback(), createContext);
        zooKeeper.create("/path_persistent_sequential", "data_persistent_sequential".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL, new MyStringCallback(), createContext);
        Thread.sleep(500);

        System.out.println("========== List Path ==========");

        // 同步获取节点列表
        List<String> children = zooKeeper.getChildren("/", true); // 使用默认的 Watcher（创建会话时的 Watcher）
        System.out.println("Path list: " + children);

        // 异步获取节点列表
        String root = "/";
        zooKeeper.getChildren(root, true, new MyChildren2Callback(), "List Path");
        Thread.sleep(500);

        System.out.println("========== Get Path ==========");

        // 同步获取数据
        Stat stat = new Stat();
        for (String path : children) {
            byte[] data = zooKeeper.getData(root + path, true, stat);
            System.out.print("Get data, path: " + path + ", data: " + new String(data) + ", stat: " + stat);
        }

        // 异步获取数据
        for (String path : children) {
            zooKeeper.getData(root + path, true, new MyDataCallBack(), "Get Data");
        }
        Thread.sleep(500);

        System.out.println("========== Set Path ==========");

        // 同步更新数据
        Stat stat1 = zooKeeper.setData("/path_persistent", "TEST".getBytes(), -1);
        Stat stat2 = zooKeeper.setData("/path_persistent", "TEST".getBytes(), stat1.getVersion());

        try {
            Stat stat3 = zooKeeper.setData("/path_persistent", "TEST".getBytes(), stat1.getVersion()); // 此时版本错误
        } catch (Exception e) {
            System.out.println("Set path error, exception: " + e);
        }

        // 异步更新数据
        zooKeeper.setData("/path_persistent", "TEST".getBytes(), -1, new MyStatCallBack(), "Set Path");
        Thread.sleep(500);

        System.out.println("========== Exist Path ==========");

        // 同步检测节点是否存在
        Stat exists = zooKeeper.exists("/path_persistent", true);
        System.out.print("Exists: " + exists);

        // 异步检测节点是否存在：略

        System.out.println("========== Delete Path ==========");

        // 同步删除节点：略

        // 异步删除节点
        zooKeeper.delete("/path_persistent", -1, new MyVoidCallback(), "Delete Path");
        Thread.sleep(500);

        // 等待异步调用结束
        Thread.sleep(5000);
        System.out.println("========== End ==========");
    }

    // ==================== Watcher ====================

    static class MyWatcher implements Watcher {
        private CountDownLatch countDownLatch;

        public MyWatcher(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.println("【MyWatcher】" + watchedEvent);
            if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                countDownLatch.countDown();
            }
        }
    }

    // ==================== 回调接口 ====================

    static class MyStringCallback implements AsyncCallback.StringCallback {
        /**
         * 处理异步响应结果
         *
         * @param resultCode 0 成功，-4 连接断开，-110 节点已存在，-112 会话过期
         * @param path       路径
         * @param ctx        上下文
         * @param name       实际路径
         */
        @Override
        public void processResult(int resultCode, String path, Object ctx, String name) {
            System.out.println("【MyStringCallback】resultCode: " + resultCode + ", path: " + path + ", ctx: " + ctx + ", name: " + name);
        }
    }

    static class MyVoidCallback implements AsyncCallback.VoidCallback {
        @Override
        public void processResult(int resultCode, String path, Object ctx) {
            System.out.println("【MyVoidCallback】resultCode: " + resultCode + ", path: " + path + ", ctx: " + ctx);
        }
    }

    static class MyChildren2Callback implements AsyncCallback.Children2Callback {
        @Override
        public void processResult(int resultCode, String path, Object ctx, List<String> list, Stat stat) {
            System.out.print("【MyChildren2Callback】resultCode: " + resultCode + ", path: " + path + ", ctx: " + ctx + ", list: " + list + ", stat: " + stat);
        }
    }

    static class MyDataCallBack implements AsyncCallback.DataCallback {
        @Override
        public void processResult(int resultCode, String path, Object ctx, byte[] bytes, Stat stat) {
            System.out.print("【MyDataCallBack】resultCode: " + resultCode + ", path: " + path + ", ctx: " + ctx + ", data: " + new String(bytes) + ", stat: " + stat);
        }
    }

    static class MyStatCallBack implements AsyncCallback.StatCallback {
        @Override
        public void processResult(int resultCode, String path, Object ctx, Stat stat) {
            System.out.print("【MyStatCallBack】resultCode: " + resultCode + ", path: " + path + ", ctx: " + ctx + ", stat: " + stat);
        }
    }


}
