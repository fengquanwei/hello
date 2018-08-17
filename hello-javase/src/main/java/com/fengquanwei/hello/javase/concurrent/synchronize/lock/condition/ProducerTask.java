package com.fengquanwei.hello.javase.concurrent.synchronize.lock.condition;

/**
 * 生产者任务
 *
 * @author fengquanwei
 * @create 2018/1/27 14:06
 **/
public class ProducerTask implements Runnable {
    private FileMock fileMock;
    private Buffer buffer;

    public ProducerTask(FileMock fileMock, Buffer buffer) {
        this.fileMock = fileMock;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.setPendingLines(true);
        while (fileMock.hasMoreLines()) {
            String line = fileMock.getLine();
            buffer.insert(line);
        }
        buffer.setPendingLines(false);
    }
}
