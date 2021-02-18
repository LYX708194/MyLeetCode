package leetcode.time2021.two;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 995 K连续位的最小翻转次数
 * 在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
 *
 * 返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：A = [0,1,0], K = 1
 * 输出：2
 * 解释：先翻转 A[0]，然后翻转 A[2]。
 * 示例 2：
 *
 * 输入：A = [1,1,0], K = 2
 * 输出：-1
 * 解释：无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。
 * 示例 3：
 *
 * 输入：A = [0,0,0,1,0,1,1,0], K = 3
 * 输出：3
 * 解释：
 * 翻转 A[0],A[1],A[2]: A变成 [1,1,1,1,0,1,1,0]
 * 翻转 A[4],A[5],A[6]: A变成 [1,1,1,1,1,0,0,0]
 * 翻转 A[5],A[6],A[7]: A变成 [1,1,1,1,1,1,1,1]
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 30000
 * 1 <= K <= A.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/2/18 14:03
 */
public class Solution995 {

    public int minKBitFlips(int[] A, int K) {
        int res = 0;
        //队列模拟滑动窗口，如果当前位置需要翻转，存储该位置到队列中
        Deque<Integer> que = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            //超出滑动窗口的移出队列
            if (que.size() > 0 && i > que.peek() + K - 1) {
                que.removeFirst();
            }
            //1.本来是1，翻转奇数次变为0，所以需要再次翻转，放入队列
            //2.本来是0，翻转偶数次还是0，所以需要再次翻转，放入队列
            if (que.size() % 2 == A[i]) {
                //后面数组长度不够翻转了
                if (i + K > A.length) return -1;
                que.add(i);
                res += 1;
            }
        }
        return res;
    }


    /**
     * 差分数组
     */
    public int minKBitFlips2(int[] A, int K) {
        int n = A.length;
        //表示两个相邻元素的翻转次数的差
        int[] diff = new int[n + 1];
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; ++i) {
            revCnt += diff[i];
            //需要翻转区间 [i,i+K-1]，我们可以直接将 revCnt 增加 1，diff[i+K] 减少 1。
            if ((A[i] + revCnt) % 2 == 0) {
                //剩下数组长度不够翻转
                if (i + K > n) {
                    return -1;
                }
                ++ans;
                ++revCnt;
                //将这个位置减一，后面累加的时候就是这个这个范围内的翻转不影响了。相当于revcnt--；
                --diff[i + K];
            }
        }
        return ans;
    }

    /**
     * 上面方法的优化，用 A[i] 范围之外的数来表达「是否翻转过」
     */
    public int minKBitFlips3(int[] A, int K) {
        int n = A.length;
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; ++i) {
            //翻转过
            if (i >= K && A[i - K] > 1) {
                revCnt ^= 1;
                // 复原数组元素，若允许修改数组 A，则可以省略
                A[i - K] -= 2;
            }
            if (A[i] == revCnt) {
                if (i + K > n) {
                    return -1;
                }
                ++ans;
                revCnt ^= 1;
                A[i] += 2;
            }
        }
        return ans;
    }

}
