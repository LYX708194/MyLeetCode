package leetcode.time2021.three;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lyx
 * @date 2021/3/18 21:27
 */
public class Solution202 {

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while(!set.contains(n)){
            set.add(n);
            int num = 0;
            while (n != 0){
                num += (n % 10) * (n % 10);
                n /= 10;
            }
            if(num == 1)    return true;
            n = num;
        }
        return false;
    }

    public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy2(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        //有环，说明不是快乐数，最后快慢指针肯定会相遇
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }


}
