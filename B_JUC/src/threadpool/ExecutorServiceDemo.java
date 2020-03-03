package threadpool;

import java.util.concurrent.*;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        // threadPoolExecutor();
        // ExecutorService es = Executors.newCachedThreadPool();

        ExecutorService es = Executors.newFixedThreadPool(5);
    }

    private static void threadPoolExecutor() {
        ExecutorService es = new ThreadPoolExecutor(7, 15, 5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(5),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("已拒绝服务 : " + Thread.currentThread().getName());
                    }
                });

        // new Thread(new ESThread()).start();
        for (int i = 0; i < 22; i++) {
            es.execute(new ESThread());
        }
    }
}

class ESThread implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ "\t\t开始执行");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+ "\t\t线程结束");
    }
}