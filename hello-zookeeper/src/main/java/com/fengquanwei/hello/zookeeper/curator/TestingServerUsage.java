package com.fengquanwei.hello.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.TestingServer;
import org.apache.curator.test.TestingZooKeeperServer;

import java.io.File;

/**
 * TestingServer 用法
 *
 * @author fengquanwei
 * @create 2018/5/21 15:38
 **/
public class TestingServerUsage {
    public static void main(String[] args) {
        try {
            System.out.println("==================== TestingServer ====================");

            TestingServer testingServer = new TestingServer(2181, new File("/Users/fengquanwei/testingserver"));

            CuratorFramework client = CuratorFrameworkFactory.builder()
                    .connectString(testingServer.getConnectString())
                    .sessionTimeoutMs(5000)
                    .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                    .build();
            client.start();

            System.out.println("【TestingServer】getChildren: " + client.getChildren().forPath("/"));

            testingServer.close();

            System.out.println();
            System.out.println("==================== TestingServer ====================");

            TestingCluster testingCluster = new TestingCluster(3);
            testingCluster.start();
            Thread.sleep(2000);

            TestingZooKeeperServer leader = null;
            for (TestingZooKeeperServer server : testingCluster.getServers()) {
                System.out.println(server.getInstanceSpec().getServerId() + "-" +
                        server.getQuorumPeer().getServerState() + "-" +
                        server.getInstanceSpec().getDataDirectory().getAbsolutePath());

                if(server.getQuorumPeer().getServerState().equals("leading")){
                    leader = server;
                }
            }

            leader.kill();
            System.out.println("【TestingCluster】leader killed");
            for (TestingZooKeeperServer server : testingCluster.getServers()) {
                System.out.println(server.getInstanceSpec().getServerId() + "-" +
                        server.getQuorumPeer().getServerState() + "-" +
                        server.getInstanceSpec().getDataDirectory().getAbsolutePath());
            }

            testingCluster.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
