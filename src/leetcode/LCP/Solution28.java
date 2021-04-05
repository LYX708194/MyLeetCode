package leetcode.LCP;

import java.util.Arrays;

/**
 * 小力将 N 个零件的报价存于数组 nums。小力预算为 target，假定小力仅购买两个零件，要求购买零件的花费不超过预算，请问他有多少种采购方案。
 *
 * 注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1
 *
 * 示例 1：
 *
 * 输入：nums = [2,5,3,5], target = 6
 *
 * 输出：1
 *
 * 解释：预算内仅能购买 nums[0] 与 nums[2]。
 *
 * 示例 2：
 *
 * 输入：nums = [2,2,1,9], target = 10
 *
 * 输出：4
 *
 * 解释：符合预算的采购方案如下：
 * nums[0] + nums[1] = 4
 * nums[0] + nums[2] = 3
 * nums[1] + nums[2] = 3
 * nums[2] + nums[3] = 10
 *
 * 提示：
 *
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i], target <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4xy4Wx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/4/5 22:11
 */
public class Solution28 {

    public int purchasePlans(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0,right = nums.length-1;
        int ans = 0;
        while (left < right){
            if (nums[left] + nums[right] <= target){
                ans = (ans + right - left) % 1000000007;
                left++;
            }else{
                right--;
            }
        }
        return ans;
    }

}
