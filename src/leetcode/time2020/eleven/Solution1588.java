package leetcode.time2020.eleven;

/**
 *
 * 给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。
 *
 * 子数组 定义为原数组中的一个连续子序列。
 *
 * 请你返回 arr 中 所有奇数长度子数组的和 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-all-odd-length-subarrays
 *
 * @author lyx
 * @date 2020/11/2 19:22
 */
public class Solution1588 {

    public int sumOddLengthSubarrays(int[] arr) {
        int ans = 0;
        int n = arr.length;
        int cur = 1;
        while (cur <= n){
            for (int i = 0; i < n; i++) {
                if (i+cur > n) break;
                for (int j = i; j < i+cur; j++) {
                    ans += arr[j];
                }
            }
            cur += 2;
        }
        return ans;
    }

    /**
     对于一个数字，它所在的数组，可以在它前面再选择 0, 1, 2, ... 个数字，一共有 left = i + 1 个选择；

     可以在它后面再选择 0, 1, 2, ... 个数字，一共有 right = n - i 个选择。

     odd奇数，even偶数
     对于每个元素i(数组中下标为i)来说，要构成奇数长度的子数组
     即 i左边的元素个数left+i本身自己一个+右边元素的个数right=奇数
     即 left+right=偶数
     满足a+b=偶数就只有两种情况
        1. 奇数+奇数=偶数
        2. 偶数+偶数=偶数
     1. 所以只需要求得i左边可以选择奇数长度的可能有多少种，即left_odd,同样求右边奇数right_odd
     就可以求出策略1有多少种可能
     2. 所以只需要求得i左边可以选择偶数长度的可能有多少种，即left_odd,同样求右边偶数right_odd
     就可以求出策略1有多少种可能，注意0也算选择的一种可能
     即元素i在所有奇数长度子数组出现的次数总和是
     left_odd*right_odd+left_even*right_even
     */
    public int sumOddLengthSubarrays2(int[] arr) {
        int ans = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int left = i,right = n-i-1;
            int left_even = (left+1)/2,right_even = (right+1)/2;
            int left_odd = left/2,right_odd = right/2;
            ans += (left_even * right_even + left_odd * right_odd) * arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution1588 solution1588 = new Solution1588();
        solution1588.sumOddLengthSubarrays(new int[]{1,4,2,5,3});
    }

}
