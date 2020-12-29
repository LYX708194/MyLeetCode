package leetcode.time2020.twelve;

/**
 * 330 按要求补齐数组
 * 给定一个已排序的正整数数组 nums，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。请输出满足上述要求的最少需要补充的数字个数。
 *
 * 示例 1:
 *
 * 输入: nums = [1,3], n = 6
 * 输出: 1
 * 解释:
 * 根据 nums 里现有的组合 [1], [3], [1,3]，可以得出 1, 3, 4。
 * 现在如果我们将 2 添加到 nums 中， 组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。
 * 其和可以表示数字 1, 2, 3, 4, 5, 6，能够覆盖 [1, 6] 区间里所有的数。
 * 所以我们最少需要添加一个数字。
 * 示例 2:
 *
 * 输入: nums = [1,5,10], n = 20
 * 输出: 2
 * 解释: 我们需要添加 [2, 4]。
 * 示例 3:
 *
 * 输入: nums = [1,2,2], n = 5
 * 输出: 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/patching-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2020/12/29 10:34
 */
public class Solution330 {


    /**
     * 贪心
     * 对于正整数 x，如果区间 [1,x-1]内的所有数字都已经被覆盖，且 x 在数组中，则区间 [1,2x-1]内的所有数字也都被覆盖。
     *
     * 如果补充 i ，则i ~ 2i-1 就能被表示出来，接下来我们需要表示的数是2i
     * 1 2 ... i-1 i i+1 ... 2i-1
     *
     * 如果nums数组中存在小于等于i且暂时还未用过的x，则i ~ i+x-1就能被表示出来，接下来我们需要表示的数是i+x
     * 1 2 ... i-1 i i+1 ... i+x-1
     *
     * @author lyx
     * @date 2020/12/29 10:37
     * @return
     */
    public int minPatches(int[] nums, int n) {
        int patches = 0;
        long x = 1;
        int length = nums.length, index = 0;
        while (x <= n) {
            if (index < length && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                x *= 2;
                patches++;
            }
        }
        return patches;
    }


}
