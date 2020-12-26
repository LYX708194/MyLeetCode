package leetcode.time2020.twelve;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 85 最大矩形
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 示例 2：
 *
 * 输入：matrix = []
 * 输出：0
 * 示例 3：
 *
 * 输入：matrix = [["0"]]
 * 输出：0
 * 示例 4：
 *
 * 输入：matrix = [["1"]]
 * 输出：1
 * 示例 5：
 *
 * 输入：matrix = [["0","0"]]
 * 输出：0
 *  
 *
 * 提示：
 *
 * rows == matrix.length
 * cols == matrix[0].length
 * 0 <= row, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-rectangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2020/12/26 13:11
 */
public class Solution85 {


    /**
     * 动态规划思想
     * 思路：数组长度为m和n，先求出第i行（0<=i<n）每个元素matrix[i][j]（0<=j<m）左边为1的个数并存放到left二维数组中；
     *      然后对于每个元素，以当前元素为右下角往上依次求最大解。
     * @author lyx
     * @date 2020/12/26 13:21
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        int[][] left = new int[m][n];
        // 求left矩阵
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1'){
                    // 如果是该行的首元素，令其为1,否则等于前一个值加1
                    left[i][j] = (j == 0) ? 1 : left[i][j-1] + 1;
                }
            }
        }
        int ret = 0;
        // 遍历二维矩阵
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果当前值为0，继续判断下一个
                if (matrix[i][j] == '0') continue;

                int width = left[i][j];
                int area = width;
                for (int k = i - 1; k >= 0; k--) {
                    // 如果宽度为0，则没有往上找的必要了
                    if (width == 0) break;
                    // 更新宽度
                    width = Math.min(width, left[k][j]);
                    // 更新面积
                    area = Math.max(area, (i - k + 1) * width);
                }
                // 该元素往上的最大area已经找到，更新结果
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }

    /**
     * 单调栈，参考84题
     * @author lyx
     * @date 2020/12/26 13:39
     * @return
     */
    public int maximalRectangle2(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int[][] left = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }

        int ret = 0;
        for (int j = 0; j < n; j++) { // 对于每一列，使用基于柱状图的方法
            int[] up = new int[m];
            int[] down = new int[m];

            Deque<Integer> stack = new LinkedList<Integer>();
            for (int i = 0; i < m; i++) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int i = m - 1; i >= 0; i--) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                down[i] = stack.isEmpty() ? m : stack.peek();
                stack.push(i);
            }

            for (int i = 0; i < m; i++) {
                int height = down[i] - up[i] - 1;
                int area = height * left[i][j];
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }

}
