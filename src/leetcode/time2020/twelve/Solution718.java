package leetcode.time2020.twelve;

/**
 * 718 最长重复子数组
 *
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 *
 *  
 *
 * 示例：
 *
 * 输入：
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出：3
 * 解释：
 * 长度最长的公共子数组是 [3, 2, 1] 。
 *  
 *
 * 提示：
 *
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/12/8 22:04
 */
public class Solution718 {

    /**
     * 动态规划
     * dp[i][j]表示以i和j结尾的子数组的长度
     * @param A
     * @param B
     * @return
     */
    public int findLength(int[] A, int[] B) {
        int[] dp = new int[B.length+1];
        int max = 0;
        for (int i = 1; i <= A.length; i++) {
            //改为一维dp，需要将b从后往前覆盖，避免上一层的dp[j-1]被覆盖
            for (int j = B.length; j >= 1; j--) {
                dp[j] = A[i-1] == B[j-1] ? dp[j-1] + 1 : 0;
                max = Math.max(max,dp[j]);
            }
        }
        return max;
    }

    /**
     * 滑动窗口
     * @param A
     * @param B
     * @return
     */
    public int findLength2(int[] A, int[] B){
        int max = 0;
        int n = A.length,m = B.length;
        //B不变，A往前移
        for (int i = 0; i < n; i++) {
            int len = Math.min(m,n-i);
            int maxLen = maxLength(A,B,i,0,len);
            max = Math.max(max,maxLen);
        }
        //A不变，B往前移
        for (int i = 0; i < m; i++) {
            int len = Math.min(n,m-i);
            int maxLen = maxLength(A,B,0,i,len);
            max = Math.max(max,maxLen);
        }
        return max;
    }
    public int maxLength(int[] A,int[] B,int i,int j,int len){
        int count = 0,max = 0;
        for (int k = 0; k < len; k++) {
            if (A[i+k] == B[j+k]){
                count++;
            }else if (count > 0){
                count = 0;
            }
            max = Math.max(count,max);
        }
        return max;
    }



    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,2,1};
        int[] b = new int[]{3,2,1,4,7};
        Solution718 solution718 = new Solution718();
        solution718.findLength(a,b);
    }

}
