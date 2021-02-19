package leetcode.time2021.two;

/**
 * 1004 最大连续1的个数3
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 *
 * 返回仅包含 1 的最长（连续）子数组的长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * 示例 2：
 *
 * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] 为 0 或 1 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-consecutive-ones-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/2/19 13:20
 */
public class Solution1004 {

    public int longestOnes(int[] A, int K) {
        int l = 0, r = 0;
        while (r < A.length) {
            //窗口只变长不变段，其中r一直在右移，当k小于0时平移
            if (A[r++] == 0) K--;
            if (K < 0 && A[l++] == 0) K++;
        }
        return r - l;
    }

    public int longestOnes2(int[] A, int K) {
        int left = 0,right = 0;
        int n = A.length;
        int max = 0;
        while (right < n){
            if (A[right] == 1){
                //右指针为1
                right++;
            }else if (K > 0){
                //右指针处为0，k还有剩余
                right++;
                K--;
            }else{
                //没有多余的K可以修改，左指针移动，且看是否有退回
                K = A[left] == 1 ? K : K + 1 ;
                left++;
            }
            max = Math.max(max,right - left);
        }
        return max;
    }

    /**
     * 二分查找法
     */
    public int longestOnes3(int[] A, int K) {
        int n = A.length;
        //前缀和，记录包含0的个数
        int[] P = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            P[i] = P[i - 1] + (1 - A[i - 1]);
        }
        int ans = 0;
        //P[right]−P[left−1]≤K，即 P[left−1]≥P[right]−K
        for (int right = 0; right < n; ++right) {
            int left = binarySearch(P, P[right + 1] - K);
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public int binarySearch(int[] P, int target) {
        int low = 0, high = P.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (P[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }


}
