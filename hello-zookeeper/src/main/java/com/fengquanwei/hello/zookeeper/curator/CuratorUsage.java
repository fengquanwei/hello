package com.fengquanwei.hello.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.barriers.DistributedDoubleBarrier;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Curator 用法
 *
 * @author fengquanwei
 * @create 2018/5/18 16:41
 **/
public class CuratorUsage {
    static DistributedBarrier barrier;

    public static void main(String[] args) {
        CuratorFramework client = null;

        try {
            System.out.println("==================== 创建会话 ====================");

            final String namespace = "test_curator";

            client = CuratorFrameworkFactory.builder()
                    .connectString("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183")
                    .sessionTimeoutMs(5000)
                    .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                    .namespace(namespace) // 客户端隔离命名空间
                    .build();
            client.start();
            System.out.println("【Client start】");

            System.out.println();
            System.out.println("==================== 创建节点 ====================");

            String path1 = client.create().forPath("path1"); // 默认持久节点，默认值为空
            System.out.println("【Create】path: " + path1);

            String path2 = client.create().forPath("path2", "data2".getBytes()); // 设置数据
            System.out.println("【Create】path: " + path2);

            String path3 = client.create().withMode(CreateMode.EPHEMERAL).forPath("path3"); // 创建临时节点
            System.out.println("【Create】path: " + path3);

            String path4 = client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("path4/path44/path444"); // 递归创建父节点
            System.out.println("【Create】path: " + path4);

            System.out.println();
            System.out.println("==================== 读取数据 ====================");

            byte[] data1 = client.getData().forPath(path1);
            System.out.println("【Get Data】path: " + path1 + ", data: " + new String(data1));

            Stat stat2 = new Stat();
            byte[] data2 = client.getData().storingStatIn(stat2).forPath(path2); // 保存 Stat
            System.out.print("【Get Data】path: " + path2 + ", data: " + new String(data2) + ", stat: " + stat2);

            Stat stat3 = new Stat();
            byte[] data3 = client.getData().storingStatIn(stat3).forPath(path3);
            System.out.print("【Get Data】path: " + path3 + ", data: " + new String(data3) + ", stat: " + stat3);

            byte[] data4 = client.getData().forPath(path4);
            System.out.println("【Get Data】path: " + path4 + ", data: " + new String(data4));

            System.out.println();
            System.out.println("==================== 更新节点 ====================");

            client.setData().forPath(path1, "data1".getBytes());
            System.out.println("【Set Data】path: " + path1 + ", data: " + new String(data1));

            Stat newStat3 = client.setData().withVersion(stat3.getVersion()).forPath(path3, "data3".getBytes());// CAS
            System.out.print("【Set Data】path: " + path3 + ", data: " + new String(data3) + ", stat3: " + newStat3);

            System.out.println();
            System.out.println("==================== 删除节点 ====================");

            client.delete().forPath(path1);
            System.out.println("【Delete】path: " + path1);

            client.delete().forPath(path2);
            System.out.println("【Delete】path: " + path2);

            client.delete().withVersion(newStat3.getVersion()).forPath(path3); // 指定版本删除
            System.out.println("【Delete】path: " + path3);

            client.delete().guaranteed().forPath(path4); // 强制删除
            System.out.println("【Delete】path: " + path4);

            System.out.println();
            System.out.println("==================== 异步调用 ====================");

            final CountDownLatch countDownLatch1 = new CountDownLatch(2);

            ExecutorService executorService = Executors.newFixedThreadPool(2);
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
                @Override
                public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                    System.out.println("【BackgroundCallback】processResult, currentThread: " + Thread.currentThread() + ", curatorFramework: " + curatorFramework + ", curatorEvent: " + curatorEvent);
                    countDownLatch1.countDown();
                }
            }, executorService).forPath("path5", "data5".getBytes()); // 使用单独的线程池处理回调

            client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).inBackground(new BackgroundCallback() {
                @Override
                public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                    System.out.println("【BackgroundCallback】processResult, currentThread: " + Thread.currentThread() + ", curatorFramework: " + curatorFramework + ", curatorEvent: " + curatorEvent);
                    countDownLatch1.countDown();
                }
            }).forPath("path6", "data6".getBytes()); // 不使用单独的线程池处理回调

            countDownLatch1.await();
            executorService.shutdown();

            System.out.println();
            System.out.println("==================== 事件监听 ====================");

            // 节点监听
            final String path5 = "path5";
            final NodeCache nodeCache = new NodeCache(client, path5, false);
            nodeCache.start(true);
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    System.out.println("【NodeCacheListener】nodeChanged, path: " + path5 + ", currentData: " + new String(nodeCache.getCurrentData().getData()));
                }
            });

            // 子节点监听
            client.setData().forPath(path5, "newData5".getBytes());
            Thread.sleep(500);
            client.delete().forPath(path5);

            final String path6 = "path6";
            PathChildrenCache pathChildrenCache = new PathChildrenCache(client, path6, true);
            pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                    System.out.println("【PathChildrenCacheListener】currentThread: " + Thread.currentThread() + ", curatorFramework: " + curatorFramework + ", pathChildrenCacheEvent: " + pathChildrenCacheEvent);
                }
            });

            client.create().creatingParentsIfNeeded().forPath(path6 + "/path66");

            Thread.sleep(1000);

            System.out.println();
            System.out.println("==================== Master 选举 ====================");

            final String masterPath = "/master";

            LeaderSelector selector1 = new LeaderSelector(client, masterPath, new LeaderSelectorListenerAdapter() {
                @Override
                public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                    List<String> children = curatorFramework.getChildren().forPath(masterPath);
                    System.out.println("【LeaderSelectorListenerAdapter】takeLeadership, curatorFramework: " + curatorFramework + ", master children: " + children);
                    Thread.sleep(300); // Do some work
                }
            });
            selector1.autoRequeue();
            selector1.start();

            LeaderSelector selector2 = new LeaderSelector(client, masterPath, new LeaderSelectorListenerAdapter() {
                @Override
                public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                    List<String> children = curatorFramework.getChildren().forPath(masterPath);
                    System.out.println("【LeaderSelectorListenerAdapter】takeLeadership, curatorFramework: " + curatorFramework + ", master children: " + children);
                    Thread.sleep(300); // Do some work
                }
            });
            selector2.autoRequeue();
            selector2.start();

            Thread.sleep(1000);

            selector1.close();
            selector2.close();

            System.out.println();
            System.out.println("==================== 分布式锁 ====================");

            final CountDownLatch countDownLatch2 = new CountDownLatch(1);

            String lockPath = "/lock";
            final InterProcessLock lock = new InterProcessMutex(client, lockPath);
            for (int i = 0; i < 30; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            countDownLatch2.await();
                            lock.acquire();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        System.out.println("【ID Generator】id: " + new SimpleDateFormat("HH:mm:ss.SSS").format(new Date()));

                        try {
                            lock.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                countDownLatch2.countDown();
            }

            Thread.sleep(2000);

            System.out.println();
            System.out.println("==================== 分布式计数器 ====================");

            String atomicPath = "/atomic";
            DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client, atomicPath, new RetryNTimes(3, 1000));
            AtomicValue<Integer> atomicValue = atomicInteger.add(8);
            System.out.println(atomicValue.succeeded());

            System.out.println();
            System.out.println("==================== 分布式 Barrier 一 ====================");

            final String barrierPath = "/barrier";

            for (int i = 0; i < 10; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        CuratorFramework cli = CuratorFrameworkFactory.builder()
                                .connectString("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183")
                                .sessionTimeoutMs(5000)
                                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                                .namespace(namespace) // 客户端隔离命名空间
                                .build();
                        cli.start();

                        barrier = new DistributedBarrier(cli, barrierPath);
                        try {
                            barrier.setBarrier();
                            System.out.println("【" + Thread.currentThread() + "】reday");
                            barrier.waitOnBarrier();
                            System.out.println("【" + Thread.currentThread() + "】go");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
            Thread.sleep(1000);
            barrier.removeBarrier();
            Thread.sleep(1000);

            System.out.println();
            System.out.println("==================== 分布式 Barrier 二 ====================");

            for (int i = 0; i < 10; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        CuratorFramework cli = CuratorFrameworkFactory.builder()
                                .connectString("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183")
                                .sessionTimeoutMs(5000)
                                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                                .namespace(namespace) // 客户端隔离命名空间
                                .build();
                        cli.start();

                        DistributedDoubleBarrier barrier = new DistributedDoubleBarrier(cli, barrierPath, 5);
                        System.out.println("【" + Thread.currentThread() + "】ready");
                        try {
                            barrier.enter();
                            System.out.println("【" + Thread.currentThread() + "】enter");
                            barrier.leave();
                            System.out.println("【" + Thread.currentThread() + "】leave");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
            Thread.sleep(1000);




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println();
            System.out.println("==================== 清理数据 ====================");
            try {
                client.delete().deletingChildrenIfNeeded().forPath(""); // 递归删除
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
