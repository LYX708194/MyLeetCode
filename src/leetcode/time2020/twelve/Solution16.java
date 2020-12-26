package leetcode.time2020.twelve;

import java.util.Arrays;

/**
 * 16 最接近的三数之和
 *
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 *  
 *
 * 示例：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *  
 *
 * 提示：
 *
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/12/14 21:23
 */
public class Solution16 {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for(int i = 0; i < nums.length; i++){
            int start = i + 1,end = nums.length - 1;
            while(start < end){
                int sum = nums[i] + nums[start] + nums[end];
                if(Math.abs(target - ans) > Math.abs(target - sum)){
                    ans = sum;
                }
                if(target < sum){
                    end--;
                }else if(target > sum){
                    start++;
                }else{
                    return sum;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution16 solution16 = new Solution16();
        int[] a = new int[]{1,2,4,8,16,64,128};
        solution16.threeSumClosest(a,82);
    }

}
