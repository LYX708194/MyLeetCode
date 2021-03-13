package leetcode.time2021.three;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 数组中出现次数超过一半的数字
 * @author lyx
 * @date 2021/3/13 22:21
 */
public class Solution169 {

    public int majorityElement3(int[] nums) {
        /**
         * 摩尔投票法，票数正负抵消
         * 假设是众数，票数+1，不是众数票数-1，最后票数一定大于0
         * 当票数为0时，后面剩余数字的众数仍不变，因为如果假设的众数正确的话，众数则占一半，否则众数占一半以下
         * 哪怕选举的不是众数，后面只要遇到0肯定会修改假设的众数，最后保证选举的那个一定是众数
         */
        int x = 0,vote = 0;
        for (int num : nums){
            if (vote == 0) x = num;
            vote += x == num ? 1 : -1;
        }
        return x;
    }

    public int majorityElement2(int[] nums) {
        //直接排序，中点位置的数即为所求
        int n = nums.length;
        Arrays.sort(nums);
        return nums[n/2];
    }

    /**
     * hashmap计数
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }
        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }
        return majorityEntry.getKey();
    }

}
