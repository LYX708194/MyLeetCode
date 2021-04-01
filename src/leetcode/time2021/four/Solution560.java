package leetcode.time2021.four;

import java.util.HashMap;
import java.util.Map;

/** 和为k的子数组
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 *
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/4/1 16:55
 */
public class Solution560 {

    public int subarraySum3(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int count = 0;
        int preSum = 0;
        for (int num : nums) {
            preSum += num;
            //满足 preSum - (preSum - k) == k，那么preSum - k前缀和的个数就是当前能组合乘的个数
            if (map.containsKey(preSum-k)){
                count += map.get(preSum-k);
            }
            map.put(preSum,map.getOrDefault(preSum,0)+1);
        }
        return count;
    }

    public int subarraySum2(int[] nums, int k) {
        int n = nums.length;
        //前缀和
        int[] preSum = new int[n+1];
        for (int i = 0; i < n; i++) {
            preSum[i+1] = preSum[i] + nums[i];
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (preSum[j+1] - preSum[i] == k) count++;
            }
        }
        return count;
    }


    public int subarraySum1(int[] nums, int k) {
        int count = 0;
        int n = nums.length;
        //固定左边界，枚举右边界
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == k) count++;
            }
        }
        return count;
    }



}
