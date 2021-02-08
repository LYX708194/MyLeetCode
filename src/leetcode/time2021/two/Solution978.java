package leetcode.time2021.two;

/**
 * 978 最长湍流子数组
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 *
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 *
 * 返回 A 的最大湍流子数组的长度。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 * 示例 2：
 *
 * 输入：[4,8,12,16]
 * 输出：2
 * 示例 3：
 *
 * 输入：[100]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 40000
 * 0 <= A[i] <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-turbulent-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/2/8 20:31
 */
public class Solution978 {

    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int max = 1;
        int left = 0,right = 0;
        while (right < n - 1) {
            if (left == right){
                if (arr[left] == arr[left+1]){
                    left++;
                }
                right++;
            }else{
                if (arr[right-1] > arr[right] && arr[right] < arr[right+1]){
                    right++;
                }else if (arr[right-1] < arr[right] && arr[right] > arr[right+1]){
                    right++;
                }else{
                    left = right;
                }
            }
            max = Math.max(max,right - left + 1);
        }
        return max;
    }

    /**
     * 动态规划，dp0记录以 arr[i] 结尾，且 arr[i−1]>arr[i] 的湍流子数组的最大长度
     * dp1记录以 arr[i] 结尾，且 arr[i−1]< arr[i] 的湍流子数组的最大长度
     */
    public int maxTurbulenceSize2(int[] arr) {
        int n = arr.length;
        int ret = 1;
        int dp0 = 1, dp1 = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] > arr[i]) {
                dp0 = dp1 + 1;
                dp1 = 1;
            } else if (arr[i - 1] < arr[i]) {
                dp1 = dp0 + 1;
                dp0 = 1;
            } else {
                dp0 = 1;
                dp1 = 1;
            }
            ret = Math.max(ret, dp0);
            ret = Math.max(ret, dp1);
        }
        return ret;
    }

}
