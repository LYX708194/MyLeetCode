package leecode.time2020.eleven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *  
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/27 12:13
 */
public class Solution15 {

    /**
     * 排序+双指针
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        if (nums == null || n < 3)  return res;
        Arrays.sort(nums);
        for (int i = 0; i < n - 3; i++) {
            //第一个数都大于0，后面肯定加起来不等于0
            if (nums[i] > 0)    break;
            //去重
            if (i > 0 && nums[i] == nums[i-1])  continue;
            int left = i + 1,right = n - 1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0){
                    res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    //去重
                    while (left < right && nums[left] == nums[left + 1])  left++;
                    while (left < right && nums[right] == nums[right - 1])  right--;
                    left++;
                    right--;
                }else if (sum < 0){
                    left++;
                }else if (sum > 0){
                    right--;
                }
            }
        }
        return res;
    }


}
