package leetcode.time2020.eleven;

/**
 * 493题  ： 翻转对
 *
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 *
 * 你需要返回给定数组中的重要翻转对的数量。
 *
 * 示例 1:
 *
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * 示例 2:
 *
 * 输入: [2,4,3,5,1]
 * 输出: 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 注意:
 *
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 *
 * @author lyx
 * @date 2020/11/28 12:52
 */
public class Solution493 {

    /**
     * 归并排序      与「327. 区间和的个数」非常相像。
     *
     * 假设对于数组 nums[l..r] 而言，我们已经分别求出了子数组 nums[l..m] 与 nums[m+1..r] 的翻转对数目，并已将两个子数组分别排好序，
     * 则 nums[l..r] 中的翻转对数目，就等于两个子数组的翻转对数目之和，加上左右端点分别位于两个子数组的翻转对数目。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/reverse-pairs/solution/fan-zhuan-dui-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        if (nums.length == 0)   return 0;
        return reversePairsRecursive(nums,0,nums.length-1);
    }

    public int reversePairsRecursive(int[] nums,int left,int right){
        if (left == right)  return 0;
        int mid = (left + right) / 2;
        int n1 = reversePairsRecursive(nums,left,mid);
        int n2 = reversePairsRecursive(nums, mid + 1, right);
        int ret = n1 + n2;

        //统计下标对的数量
        int i = left,j = mid + 1;
        while (i <= mid){
            while (j <= right && (long)nums[i] > (long)2 * nums[j]){
                j++;
            }
            ret += j - mid - 1;
            i++;
        }

        //合并数组
        int[] sorted = new int[right - left + 1];
        int p1 = left,p2 = mid + 1;
        int p = 0;
        while (p1 <= mid || p2 <= right){
            if (p1 > mid){
                sorted[p++] = nums[p2++];
            }else if (p2 > right){
                sorted[p++] = nums[p1++];
            }else{
                if (nums[p1] < nums[p2]){
                    sorted[p++] = nums[p1++];
                }else{
                    sorted[p++] = nums[p2++];
                }
            }
        }
        for (int k = 0; k < sorted.length; k++) {
            nums[left + k] = sorted[k];
        }
        return ret;
    }

}
