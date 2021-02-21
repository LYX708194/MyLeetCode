package leetcode.time2021.two;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

/**
 * 1438 绝对差不超过限制的最长子数组
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 *
 * 如果不存在满足条件的子数组，则返回 0 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [8,2,4,7], limit = 4
 * 输出：2
 * 解释：所有子数组如下：
 * [8] 最大绝对差 |8-8| = 0 <= 4.
 * [8,2] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4] 最大绝对差 |8-2| = 6 > 4.
 * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
 * [2] 最大绝对差 |2-2| = 0 <= 4.
 * [2,4] 最大绝对差 |2-4| = 2 <= 4.
 * [2,4,7] 最大绝对差 |2-7| = 5 > 4.
 * [4] 最大绝对差 |4-4| = 0 <= 4.
 * [4,7] 最大绝对差 |4-7| = 3 <= 4.
 * [7] 最大绝对差 |7-7| = 0 <= 4.
 * 因此，满足题意的最长子数组的长度为 2 。
 * 示例 2：
 *
 * 输入：nums = [10,1,2,4,7,2], limit = 5
 * 输出：4
 * 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
 * 示例 3：
 *
 * 输入：nums = [4,2,2,2,4,4,2,2], limit = 0
 * 输出：3
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 0 <= limit <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/2/21 11:56
 */
public class Solution1438 {

    public int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer,Integer> map = new TreeMap<>();
        int n = nums.length;
        int ans = 0;
        int left = 0,right = 0;
        while (right < n){
            map.put(nums[right],map.getOrDefault(nums[right],0)+1);
            if (map.lastKey() - map.firstKey() > limit){
                map.put(nums[left],map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0){
                    map.remove(nums[left]);
                }
                left++;
            }
            ans = Math.max(ans,right - left + 1);
            right++;
        }
        return ans;
    }

    public int longestSubarray2(int[] nums, int limit) {
        int n = nums.length;
        int ans = 0;
        Deque<Integer> max = new ArrayDeque<>();
        Deque<Integer> min = new ArrayDeque<>();
        int left = 0,right = 0;
        while (right < n){
            //保证这个队列里的数是递减的，头部最大
            while (!max.isEmpty() && max.peekLast() < nums[right]){
                max.pollLast();
            }
            //保证这个队列里的数是递增的，头部最小
            while (!min.isEmpty() && min.peekLast() > nums[right]){
                min.pollLast();
            }
            max.addLast(nums[right]);
            min.addLast(nums[right]);
            while (!max.isEmpty() && !min.isEmpty() && max.peekFirst() - min.peekFirst() > limit){
                if (nums[left] == min.peekFirst()){
                    min.pollFirst();
                }
                if (nums[left] == max.peekFirst()){
                    max.pollFirst();
                }
                left++;
            }
            ans = Math.max(ans,right - left + 1);
            right++;
        }
        return ans;
    }

}
