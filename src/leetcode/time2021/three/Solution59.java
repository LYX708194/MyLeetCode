package leetcode.time2021.three;

/**螺旋矩阵2
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：[[1]]
 *  
 *
 * 提示：
 *
 * 1 <= n <= 20
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/16 10:18
 */
public class Solution59 {

    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int top = 0,bottle = n-1,left = 0,right = n-1;
        int i = 1;
        while (i <= n * n){
            for (int j = left; j <= right; j++) {
                ans[top][j] = i++;
            }
            top++;
            for (int j = top; j <= bottle; j++) {
                ans[j][right] = i++;
            }
            right--;
            for (int j = right; j >= left; j--) {
                ans[bottle][j] = i++;
            }
            bottle--;
            for (int j = bottle; j >= top; j--) {
                ans[j][left] = i++;
            }
            left++;
        }
        return ans;
    }


    public static void main(String[] args) {
        Solution59 solution59 = new Solution59();
        solution59.generateMatrix(3);
    }

}
