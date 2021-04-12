package leetcode.thread;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author lyx
 * @date 2021/4/12 15:42
 */
public class ZeroEvenOdd {

//    private int n;
//
//    private volatile int state = 0;
//
//    public ZeroEvenOdd(int n) {
//        this.n = n;
//    }
//
//
//    public void zero(IntConsumer printNumber) throws InterruptedException {
//        for (int i = 0; i < n; i++) {
//            while (state != 0){
//                Thread.yield();
//            }
//            printNumber.accept(0);
//            if(i % 2 == 0){
//                state = 1;
//            }else{
//                state = 2;
//            }
//        }
//    }
//
//    public void even(IntConsumer printNumber) throws InterruptedException {
//        for (int i = 2; i <= n; i += 2) {
//            while (state != 1){
//                Thread.yield();
//            }
//            printNumber.accept(i);
//            state = 0;
//        }
//    }
//
//    public void odd(IntConsumer printNumber) throws InterruptedException {
//        for (int i = 1; i <= n; i += 2) {
//            while (state != 2){
//                Thread.yield();
//            }
//            printNumber.accept(i);
//            state = 0;
//        }
//    }


    private int n;
    private Semaphore zero = new Semaphore(1);
    private Semaphore even = new Semaphore(0);
    private Semaphore odd = new Semaphore(0);

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            zero.acquire();
            printNumber.accept(0);
            if (i % 2 == 1){
                odd.release();
            }else{
                even.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i+=2) {
            even.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i+=2) {
            odd.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }


}
