package leecode.time2020.eleven;

import java.util.Arrays;

/**
 *
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 *
 * 如果数组元素个数小于 2，则返回 0。
 *
 * 示例 1:
 *
 * 输入: [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 * 示例 2:
 *
 * 输入: [10]
 * 输出: 0
 * 解释: 数组元素个数小于 2，因此返回 0。
 * 说明:
 *
 * 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-gap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/26 12:09
 */
public class Solution164 {



    public int maximumGap(int[] nums) {
        int len = nums.length,max = Integer.MIN_VALUE;
        if (len < 2)    return 0;
        Arrays.sort(nums);
        for (int i = 1; i < len; i++) {
            max = Math.max(max,nums[i] - nums[i - 1]);
        }
        return max;
    }

    /**
     * 基数排序
     * 每次按照位数将数放到不同桶里，排完之后增加位数，例如从个位数到十位数
     */
    public int maximumGap2(int[] nums){
        int n = nums.length;
        if (n < 2)    return 0;
        long exp = 1;
        int[] buf = new int[n];
        int maxVal = Arrays.stream(nums).max().getAsInt();
        while (exp <= maxVal){
            int cnt[] = new int[10];
            for (int i = 0; i < n; i++) {
                int digit = (nums[i] / (int) exp) % 10;
                cnt[digit]++;
            }
            //计算总个数，方便按nums顺序放到buf数组中
            for (int i = 1; i < 10; i++) {
                cnt[i] += cnt[i-1];
            }
            for (int i = n - 1; i >= 0; i--) {
                int digit = (nums[i] / (int) exp) % 10;
                buf[cnt[digit] - 1] = nums[i];
                cnt[digit]--;
            }
           // System.arraycopy(buf,0,nums,0,n);
            nums = Arrays.copyOf(buf,n);
            exp *= 10;
        }
        int ret = 0;
        for (int i = 1; i < n; i++) {
            ret = Math.max(ret,nums[i] - nums[i-1]);
        }
        return ret;
    }






}
