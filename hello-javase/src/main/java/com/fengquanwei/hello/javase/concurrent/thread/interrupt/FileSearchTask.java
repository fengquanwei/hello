package com.fengquanwei.hello.javase.concurrent.thread.interrupt;

import java.io.File;

/**
 * 文件搜索任务
 *
 * @author fengquanwei
 * @create 2018/1/22 20:56
 **/
public class FileSearchTask implements Runnable {
    private String initPath;
    private String fileName;

    public FileSearchTask(String initPath, String fileName) {
        this.initPath = initPath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        File file = new File(initPath);
        if (file.isDirectory()) {
            try {
                directoryProcess(file);
            } catch (InterruptedException e) {
                System.out.printf("[%s] Interrupted", Thread.currentThread().getName());
            }
        }
    }

    private void directoryProcess(File file) throws InterruptedException {
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    directoryProcess(files[i]);
                } else {
                    fileProcess(files[i]);
                }
            }
        }
    }

    private void fileProcess(File file) throws InterruptedException {
        if (file.getName().equals(this.fileName)) {
            System.out.printf("[%s] Found file: %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
        }

        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }
}
