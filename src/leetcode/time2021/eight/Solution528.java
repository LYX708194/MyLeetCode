package leetcode.time2021.eight;

import java.util.Arrays;

/**
 * @author lyx
 * @date 2021/8/31 21:13
 */
public class Solution528 {

    int[] pre ;
    int total;

    public Solution528(int[] w) {
        pre = new int[w.length];
        pre[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            pre[i] = pre[i-1] + w[i];
        }
        total = Arrays.stream(w).sum();
    }

    public int pickIndex() {
        int x = (int)(Math.random() * total) + 1;
        //二分查找
        int low = 0, high = pre.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (pre[mid] < x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

}
