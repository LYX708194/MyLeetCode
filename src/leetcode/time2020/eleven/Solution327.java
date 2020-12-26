package leetcode.time2020.eleven;

/**
 *
 * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 *
 * 说明:
 * 最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
 *
 * 示例:
 *
 * 输入: nums = [-2,5,-1], lower = -2, upper = 2,
 * 输出: 3
 * 解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-of-range-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/7 12:06
 */
public class Solution327 {

    //暴力解法
    public int countRangeSum(int[] nums, int lower, int upper) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            long sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (lower <= sum && sum <= upper){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 归并排序
     设前缀和数组为 preSum，则问题等价于求所有的下标对 (i,j)，满足
     preSum[j]−preSum[i]∈[lower,upper]
     我们先考虑如下的问题：给定两个升序排列的数组 n_1, n_2，试找出所有的下标对 (i,j)，满足
     n_2[j] - n_1[i] ∈[lower,upper]
     在已知两个数组均为升序的情况下，这一问题是相对简单的：我们在 n_2中维护两个指针 l,rl,r。起初，它们都指向 n_2的起始位置。
     随后，我们考察 n_1的第一个元素。首先，不断地将指针 l 向右移动，直到 n_2[l] ≥n_1[0]+lower 为止
     此时， l 及其右边的元素均大于或等于 n_1[0] + +lower；随后，再不断地将指针 r 向右移动，
     直到 n_2[r] > n_1[0] + upper 为止，则 rr 左边的元素均小于或等于 n_1[0] + upper。
     故区间 [l,r)中的所有下标 j，都满足
     n_2[j] - n_1[0]∈[lower,upper]
     接下来，我们考察 n_1的第二个元素。由于 n_1是递增的，不难发现 l,r 只可能向右移动。
     因此，我们不断地进行上述过程，并对于 n_1中的每一个下标，都记录相应的区间 [l,r) 的大小。
     最终，我们就统计得到了满足条件的下标对 (i,j)的数量。
     在解决这一问题后，原问题就迎刃而解了：我们采用归并排序的方式，能够得到左右两个数组排序后的形式，以及对应的下标对数量。
     对于原数组而言，若要找出全部的下标对数量，只需要再额外找出左端点在左侧数组，同时右端点在右侧数组的下标对数量，而这正是我们此前讨论的问题。



     首先求出PreSum数组，我们需要的是PreSum中任意两点i<j，其中后面PreSum[j] - PreSum[i]在区间内，差的顺序很重要!
     已知，如果从两个单独的顺序序列各任意取一个点来实现差在区间内，很容易实现
     那么假如把PreSum分成两个数组，两个数组都是有序的，然后从两个数组各取一个点，实现差值在范围内，那么这个也是容易实现的
     回到题目，我们需要从PreSum任意取两点，因此，除了3中提到的情况外，还有另外两种情况：
     两个点都在前半段中
     两个点都在后半段中
     因此通过递归实现4中的两种情况，并且每次递归完成3的情况后，使用归并合并两个有序数组为一个有序数组
     */
    public int countRangeSum2(int[] nums, int lower, int upper) {
        long s = 0;
        long[] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            sum[i + 1] = s;
        }
        return countRangeSumRecursive(sum, lower, upper, 0, sum.length - 1);
    }

    public int countRangeSumRecursive(long[] sum, int lower, int upper, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            int mid = (left + right) / 2;
            int n1 = countRangeSumRecursive(sum, lower, upper, left, mid);
            int n2 = countRangeSumRecursive(sum, lower, upper, mid + 1, right);
            int ret = n1 + n2;

            // 首先统计下标对的数量
            int i = left;
            int l = mid + 1;
            int r = mid + 1;
            while (i <= mid) {
                while (l <= right && sum[l] - sum[i] < lower) {
                    l++;
                }
                while (r <= right && sum[r] - sum[i] <= upper) {
                    r++;
                }
                ret += r - l;
                i++;
            }

            // 随后合并两个排序数组
            int[] sorted = new int[right - left + 1];
            int p1 = left, p2 = mid + 1;
            int p = 0;
            while (p1 <= mid || p2 <= right) {
                if (p1 > mid) {
                    sorted[p++] = (int) sum[p2++];
                } else if (p2 > right) {
                    sorted[p++] = (int) sum[p1++];
                } else {
                    if (sum[p1] < sum[p2]) {
                        sorted[p++] = (int) sum[p1++];
                    } else {
                        sorted[p++] = (int) sum[p2++];
                    }
                }
            }
            for (int j = 0; j < sorted.length; j++) {
                sum[left + j] = sorted[j];
            }
            return ret;
        }
    }


}
