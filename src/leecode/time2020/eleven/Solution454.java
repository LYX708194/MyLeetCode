package leecode.time2020.eleven;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 *
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 *
 * 例如:
 *
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 *
 * 输出:
 * 2
 *
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/27 11:56
 */
public class Solution454 {

    /**
     * 分组+哈希表  将题目就变成了两数之和的经典题目了
     *
     *
     *
     * 我们可以将四个数组分成两部分，A 和 B 为一组，C 和 D 为另外一组。
     * 对于 A 和 B，我们使用二重循环对它们进行遍历，得到所有 A[i]+B[j] 的值并存入哈希映射中。
     * 对于哈希映射中的每个键值对，每个键表示一种 A[i]+B[j]，对应的值为 A[i]+B[j] 出现的次数。
     * 对于 C 和 DD，我们同样使用二重循环对它们进行遍历。
     * 当遍历到 C[k]+D[l] 时，如果 −(C[k]+D[l]) 出现在哈希映射中，那么将 −(C[k]+D[l]) 对应的值累加进答案中。
     * 最终即可得到满足 A[i]+B[j]+C[k]+D[l]=0 的四元组数目。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/4sum-ii/solution/si-shu-xiang-jia-ii-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer,Integer> countAB = new HashMap<>();
        for (int x : A) {
            for (int y : B) {
                countAB.put(x + y,countAB.getOrDefault(x + y,0) + 1);
            }
        }
        int ans = 0;
        for (int x : C) {
            for (int y : D) {
                if (countAB.containsKey( - x - y)){
                    ans += countAB.get( - x - y);
                }
            }
        }
        return ans;
    }


}
