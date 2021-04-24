package leetcode.time2021.four;

import java.util.Arrays;

/**
 * @author lyx
 * @date 2021/4/24 10:04
 */
public class Solution377 {

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num:nums) {
                if (num <= i){
                    dp[i] += dp[i-num];
                }
            }
        }
        return dp[target];
    }

    //超时
    int ans;
    public int combinationSum4_2(int[] nums, int target) {
        Arrays.sort(nums);
        ans = 0;
        back(nums,target,0);
        return ans;
    }
    private void back(int[] nums,int target,int sum){
        if (sum == target){
            ans++;
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] > target) break;
            sum += nums[i];
            back(nums, target, sum);
            sum -= nums[i];
        }
    }

}
