package leetcode.weekMatch.match215;

/**
 * 给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要 修改 数组以供接下来的操作使用。
 *
 * 如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,4,2,3], x = 5
 * 输出：2
 * 解释：最佳解决方案是移除后两个元素，将 x 减到 0 。
 *
 * @author lyx
 * @date 2020/11/15 11:41
 */
public class Solution5602 {

    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int[] pre = new int[n];
        int[] suf = new int[n];
        pre[0] = nums[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + nums[i];
        }
        suf[n-1] = nums[n-1];
        for (int i = n - 2 ; i >= 0 ; i--) {
            suf[i] = suf[i+1] + nums[i];
        }
        int res = n +1;
        int left = 0,right = 0;
        // [0, left) + [right, nums.length)
        for (; left < n; left++) {
            while (right < n && calSum(left,right,pre,suf) > x){
                right++;
            }
            if (calSum(left,right,pre,suf) == x){
                int len = left + n - right;
                res = Math.min(res,len);
            }
        }
        return res > n ? -1 : res;
    }

    private int calSum(int left,int right,int[] pre,int[] suf){
        // [0, left) + [right, nums.length)
        return (left > 0 ? pre[left-1] : 0) + (right < suf.length ? suf[right] : 0);
    }


    public static void main(String[] args) {
        Solution5602 solution5602 = new Solution5602();
        solution5602.minOperations(new int[]{3,2,20,1,1,3},5);
    }

}
