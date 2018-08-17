package com.fengquanwei.hello.javase.concurrent.synchronize.phaser.usage;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 文件查找任务
 *
 * @author fengquanwei
 * @create 2018/1/29 22:14
 **/
public class FileSearchTask implements Runnable {
    private String directory; // 待查找的文件夹
    private String extension; // 要查找的文件的扩展名
    private List<String> results; // 查找到的文件列表
    private Phaser phaser; // 控制任务不同阶段的同步

    public FileSearchTask(String directory, String extension, Phaser phaser) {
        this.directory = directory;
        this.extension = extension;
        this.results = new ArrayList<>();
        this.phaser = phaser;
    }

    @Override
    public void run() {
        // 等待所有文件查找线程创建完成
        phaser.arriveAndAwaitAdvance();

        // 执行第一阶段
        System.out.printf("[%s] Start\n", Thread.currentThread().getName());
        File file = new File(directory);
        if (file.isDirectory()) {
            directoryProcess(file);
        }
        if (!checkResults()) {
            return;
        }

        // 执行第二阶段
        filterResults();
        if (!checkResults()) {
            return;
        }

        // 执行第三阶段
        showInfo();
        phaser.arriveAndDeregister();
        System.out.printf("%s: Work completed.\n", Thread.currentThread().getName());
    }

    // 第一阶段：查找指定后缀文件
    private void directoryProcess(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    directoryProcess(files[i]);
                } else {
                    fileProcess(files[i]);
                }
            }
        }
    }

    private void fileProcess(File file) {
        if (file.getName().endsWith(extension)) {
            results.add(file.getAbsolutePath());
        }
    }

    // 第二阶段：过滤结果
    private void filterResults() {
        ArrayList<String> newResults = new ArrayList<>();
        long actualDate = new Date().getTime();
        for (int i = 0; i < results.size(); i++) {
            File file = new File(results.get(i));
            long lastModified = file.lastModified();
            if (actualDate - lastModified < TimeUnit.MILLISECONDS.convert(300, TimeUnit.DAYS)) {
                newResults.add(results.get(i));
            }
            results = newResults;
        }
    }

    // 第一阶段和第二阶段执行完检查结果集
    private boolean checkResults() {
        if (results.isEmpty()) {
            System.out.printf("[%s] Phase %d: 0 results\n", Thread.currentThread().getName(), phaser.getPhase());
            System.out.printf("[%s] Phase %d: End\n", Thread.currentThread().getName(), phaser.getPhase());
            phaser.arriveAndDeregister();
            return false;
        } else {
            System.out.printf("[%s] Phase %d: %d results\n", Thread.currentThread().getName(), phaser.getPhase(), results.size());
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }

    // 第三阶段：打印结果集
    private void showInfo() {
        for (int i = 0; i < results.size(); i++) {
            File file = new File(results.get(i));
            System.out.printf("[%s] Result: %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
        }
        phaser.arriveAndAwaitAdvance();
    }
}
