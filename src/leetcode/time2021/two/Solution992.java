package leetcode.time2021.two;

/**
 * 992 k个不同整数的子数组
 * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
 *
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
 *
 * 返回 A 中好子数组的数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：A = [1,2,1,2,3], K = 2
 * 输出：7
 * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * 示例 2：
 *
 * 输入：A = [1,2,1,3,4], K = 3
 * 输出：3
 * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= A.length
 * 1 <= K <= A.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/2/9 12:34
 */
public class Solution992 {


    public int subarraysWithKDistinct(int[] A, int K) {
        //恰好k个不同整数的子数组个数 = 最多k个不同整数子数组个数 - 最多k-1个不同整数的子数组个数
        return atMostKDistinct(A,K) - atMostKDistinct(A,K - 1);
    }
    /**
     * 滑动窗口
     * 最多包含 K 个不同整数的子区间的个数
     */
    private int atMostKDistinct(int[] A, int K) {
        int len = A.length;
        int[] freq = new int[len + 1];

        int left = 0;
        int right = 0;
        // [left, right) 里不同整数的个数
        int count = 0;
        int res = 0;
        // [left, right) 包含不同整数的个数小于等于 K
        while (right < len) {
            if (freq[A[right]] == 0) {
                count++;
            }
            freq[A[right]]++;
            right++;

            while (count > K) {
                freq[A[left]]--;
                if (freq[A[left]] == 0) {
                    count--;
                }
                left++;
            }
            // [left, right) 区间的长度就是对结果的贡献,举个例子，当满足条件的子数组从 [A,B,C] 增加到 [A,B,C,D] 时，
            //新子数组的长度为 4，同时增加的子数组为 [D], [C,D], [B,C,D], [A,B,C,D] 也为4。
            res += right - left;
        }
        return res;
    }

    /**
     * 双指针
     */
    public int subarraysWithKDistinct2(int[] A, int K) {
        int n = A.length;
        int left = 0,right = K-1;
        int count = 0;
        //用数组代替map计数
        int[] map = new int[n+1];
        int ans = 0;
        for (int i = 0; i < K; i++) {
            if (map[A[i]]++ == 0){
                count++;
            }
        }
        while (left <= n - K){
            if (count == K){
                ans++;
            }
            // [left,n-1]内结果数
            while (right < n-1 && count <= K){
                right++;
                if (map[A[right]]++ == 0){
                    count++;
                }
                if (count == K){
                    ans++;
                }
            }
            //left右移,right左移到 count == k-1
            map[A[left]]--;
            if (map[A[left++]] == 0){
                count--;
            }else {
                while (count > K - 1){
                    if (map[A[right]] == 1) {
                        count--;
                    }
                    map[A[right]]--;
                    right--;
                }
            }
        }
        return ans;
    }

}
