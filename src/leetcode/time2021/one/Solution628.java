package leetcode.time2021.one;

import java.util.Arrays;

/**
 * 628 三个数的最大乘积
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: 6
 * 示例 2:
 *
 * 输入: [1,2,3,4]
 * 输出: 24
 * 注意:
 *
 * 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-of-three-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/1/20 7:10
 */
public class Solution628 {

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        //如果数组中全是非负数/全是正数，最大值为排序后的最后三个数的乘积
        //如果数组中有正有负，最大值可能为排序后的最大三个正数的乘积，也可能是两个最大负数乘以一个最大正数
        return Math.max(nums[n-1] * nums[n-2] * nums[n-3],nums[0] * nums[1] * nums[n-1]);
    }

    /**
     * 线性扫描，找出最大的三个数以及最小的两个数
     * @author lyx
     * @date 2021/1/20 7:16
     * @return
     */
    public int maximumProduct2(int[] nums) {
        int min1 = Integer.MAX_VALUE,min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE,max2 = Integer.MIN_VALUE,max3 = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num < min1){
                min2 = min1;
                min1 = num;
            }else if (num < min2){
                min2 = num;
            }
            if (num > max1){
                max3 = max2;
                max2 = max1;
                max1 = num;
            }else if (num > max2){
                max3 = max2;
                max2 = num;
            }else if (num > max3){
                max3 = num;
            }
        }
        return Math.max(max1 * max2 * max3,max1 * min1 * min2);
    }

    public int maximumProduct3(int[] nums) {
        // 前三项为最大的三个数的堆，后两项分别为最小数、第二小数
        int[] heap = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE,
                Integer.MAX_VALUE, Integer.MAX_VALUE};

        for (int num : nums) {
            if (num > heap[0]) {
                heap[0] = num;
                // 获得堆上最小的值与之交换
                int minInd = heap[2] > heap[1] ? 1 : 2;
                if (heap[minInd] < heap[0]) {
                    int t = heap[0];
                    heap[0] = heap[minInd];
                    heap[minInd] = t;
                }
            }

            if (num < heap[3]) {
                heap[4] = heap[3];
                heap[3] = num;
            } else if (num < heap[4]) {
                heap[4] = num;
            }
        }
        return Math.max(heap[0] * heap[1] * heap[2], heap[3] * heap[4] * Math.max(heap[1], heap[2]));
    }

}
