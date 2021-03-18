package leetcode.time2021.three;

/**成绩最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/18 15:50
 */
public class Solution152 {


    public int maxProduct(int[] nums) {
        int n = nums.length;
        int ans = nums[0];
        //记录以i结尾的最大乘积
        int[] max = new int[n];
        //以i结尾的最小乘积
        int[] min = new int[n];
        System.arraycopy(nums,0,max,0,n);
        System.arraycopy(nums,0,min,0,n);
        for (int i = 1; i < n; i++) {
            max[i] = Math.max(max[i-1]*nums[i],Math.max(max[i],min[i-1]*nums[i]));
            min[i] = Math.min(min[i-1]*nums[i],Math.min(min[i],max[i-1]*nums[i]));
            ans = Math.max(ans,max[i]);
        }
        return ans;
    }
    public int maxProduct2(int[] nums) {
        int n = nums.length;
        int ans = nums[0];
        //记录以i结尾的最大乘积
        int max = nums[0];
        //以i结尾的最小乘积
        int min = nums[0];
        for (int i = 1; i < n; i++) {
            int mx = max,mn = min;
            max = Math.max(mx*nums[i],Math.max(nums[i],mn*nums[i]));
            min = Math.min(mn*nums[i],Math.min(nums[i],mx*nums[i]));
            ans = Math.max(ans,max);
        }
        return ans;
    }

}
