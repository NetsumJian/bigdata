package concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CASDemo {
    private static AtomicInteger ai = new AtomicInteger(10);
    private static AtomicStampedReference<Integer> asr = new AtomicStampedReference<>(10,1);

    public static void main(String[] args) {
        // atomicInteger();
        new Thread(()->{
            asr.compareAndSet(10,12,1,2);
            System.out.println(Thread.currentThread().getName()+"\t10->12->10\t"+asr.getStamp()+"\t"+asr.getReference());
            asr.compareAndSet(12,10,2,3);
            System.out.println(Thread.currentThread().getName()+"\t10->12->10\t"+asr.getStamp()+"\t"+asr.getReference());
        },"lili").start();

        new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean set = asr.compareAndSet(10, 24, 3, 2);
            System.out.println(set);
            System.out.println(Thread.currentThread().getName()+"\t10->24\t"+asr.getStamp()+"\t"+asr.getReference());
        },"didi").start();
    }

    private static void atomicInteger() {
        new Thread(()-> {
            ai.compareAndSet(10,12);
            ai.compareAndSet(12,10);
            System.out.println(Thread.currentThread().getName()+"\t10->12->10\t"+ai.get());
        },"lili").start();

        new Thread(()-> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean set = ai.compareAndSet(10, 24);
            System.out.println(Thread.currentThread().getName()+"\t"+set +"\t10->24\t"+ai.get());
        },"didi").start();
    }
}
