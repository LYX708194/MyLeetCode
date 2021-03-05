package leetcode.time2021.three;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lyx
 * @date 2021/3/5 13:05
 */
public class MyStack225 {


    private Queue<Integer> queue1;

    private Queue<Integer> queue2;

    /** Initialize your data structure here. */
    public MyStack225() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue2.offer(x);
        while (!queue1.isEmpty()){
            queue2.offer(queue1.poll());
        }
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    /**
     * 只用一个队列实现
     */
    public void push2(int x) {
        int n = queue1.size();
        queue1.offer(x);
        for (int i = 0; i < n; i++) {
            queue1.offer(queue1.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue1.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue1.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }


}
