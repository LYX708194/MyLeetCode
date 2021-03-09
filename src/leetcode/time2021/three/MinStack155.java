package leetcode.time2021.three;

import java.util.Stack;

/**
 * @author lyx
 * @date 2021/3/9 21:45
 */
public class MinStack155 {


    Integer min = null;
    Stack<Long> data;


    public MinStack155() {
        data = new Stack<>();
    }

    //栈里存的是和最小值的差值
    public void push(int x) {
        if (data.isEmpty()) {
            data.push(0L);
            min = x;
        } else {
            //如果x是最小的数，这里可能越界，所以用Long来保存
            data.push(Long.valueOf(x) - min);
            min = Math.min(x, min);
        }
    }

    public void pop() {
        Long diff = data.pop();
        if (diff >= 0) {
            //return min + diff;
        } else {
            int res = min;
            //栈顶元素小于0，说明这个是
            min = (int) (min - diff);
            //return res;
        }
    }

    public int top() {
        Long diff = data.peek();
        if (diff >= 0) {
            return (int) (min + diff);
        } else {
            return min;
        }
    }

    public int getMin() {
        return min;
    }

}
