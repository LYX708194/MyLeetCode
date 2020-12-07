package leecode.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1114 按序打印  可以使用信号量，同步栅栏，原子类等
 *
 * 我们提供了一个类：
 *
 * public class Foo {
 *   public void first() { print("first"); }
 *   public void second() { print("second"); }
 *   public void third() { print("third"); }
 * }
 * 三个不同的线程将会共用一个 Foo 实例。
 *
 * 线程 A 将会调用 first() 方法
 * 线程 B 将会调用 second() 方法
 * 线程 C 将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * @author lyx
 * @date 2020/12/6 14:00
 */
public class Foo {

    /* 加锁和 condition 通知
    int num;
    Lock lock;
    Condition condition1,condition2,condition3;

    public Foo() {
        this.num = 1;
        this.lock = new ReentrantLock();
        this.condition1 = lock.newCondition();
        this.condition2 = lock.newCondition();
        this.condition3 = lock.newCondition();
    }

    public void first(Runnable printFirst) throws InterruptedException {
        try {
            lock.lock();
            while (num != 1){
                condition1.await();
            }
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            num = 2;
            condition2.signal();
        }finally {
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        try {
            lock.lock();
            while (num != 2){
                condition2.await();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            num = 3;
            condition3.signal();
        }finally {
            lock.unlock();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        try {
            lock.lock();
            while (num != 3){
                condition3.await();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            num = 1;
            condition1.signal();
        }finally {
            lock.unlock();
        }
    }
    */

    private final AtomicInteger atomic = new AtomicInteger(0); ;

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        atomic.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (atomic.get() != 1) {
        }
        printSecond.run();
        atomic.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (atomic.get() != 2) {
        }
        printThird.run();
    }

}
