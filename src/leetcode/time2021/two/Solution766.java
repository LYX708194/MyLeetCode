package leetcode.time2021.two;

/**
 * 766 托普利茨矩阵
 * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
 *
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
 * 输出：true
 * 解释：
 * 在上述矩阵中, 其对角线为:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]"。
 * 各条对角线上的所有元素均相同, 因此答案是 True 。
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,2],[2,2]]
 * 输出：false
 * 解释：
 * 对角线 "[1, 2]" 上的元素不同。
 *  
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 20
 * 0 <= matrix[i][j] <= 99
 *  
 *
 * 进阶：
 *
 * 如果矩阵存储在磁盘上，并且内存有限，以至于一次最多只能将矩阵的一行加载到内存中，该怎么办？
 * 如果矩阵太大，以至于一次只能将不完整的一行加载到内存中，该怎么办？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/toeplitz-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/2/22 12:57
 */
public class Solution766 {

    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        //记录上一行的数据
        int[] pre = matrix[0];
        for (int i = 1; i < m; i++) {
            int[] cur = matrix[i];
            for (int j = 1; j < n; j++) {
                //新一行的数据与上一行的数据比较，位序递减
                if (cur[j] != pre[j-1]){
                    return false;
                }
            }
            //更新旧数组
            pre = cur;
        }
        return true;
    }
    public boolean isToeplitzMatrix2(int[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Solution766 solution766 = new Solution766();
        solution766.isToeplitzMatrix(new int[][]{{1,2,3,4},{5,1,2,3},{9,5,1,2}});
    }

}