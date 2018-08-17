package com.fengquanwei.hello.javase.concurrent.trace;

import java.util.concurrent.*;

/**
 * 丢失异常测试
 *
 * @author fengquanwei
 * @create 2018/3/17 13:33
 **/
public class MissExceptionTest {
    /**
     * 计算任务
     */
    static class CalTask implements Runnable {
        int a, b;

        public CalTask(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            int r = a / b;
            System.out.println(r);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        TraceThreadPoolExecutor threadPool = new TraceThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 5; i++) {
            // 方案一：直接 submit 没有异常信息
//            threadPool.submit(new CalTask(100, i));

            // 方案二：使用 execute 有部分异常信息
//            threadPool.execute(new CalTask(100, i));

            // 方案三：有较完整的异常信息
//            Future<?> future = threadPool.submit(new CalTask(100, i));
//            future.get();

            // 方案四：扩展线程池
            threadPool.submit(new CalTask(100, i));
        }
        threadPool.shutdown();
    }
}
