package leetcode.time2021.five;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyx
 * @date 2021/6/2 22:50
 */
public class Solution523 {

    //超时
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if ((sum[j+1]-sum[i])%k == 0){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkSubarraySum2(int[] nums, int k) {
        int m = nums.length;
        if (m < 2) {
            return false;
        }
        //余数-下标
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < m; i++) {
            remainder = (remainder + nums[i]) % k;
            //遇到重复的余数，则中间区间肯定可以整除，判断长度是否超过2
            if (map.containsKey(remainder)) {
                //没有覆盖，还是第一次出现的下标
                int prevIndex = map.get(remainder);
                if (i - prevIndex >= 2) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }

}
