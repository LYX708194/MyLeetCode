package leetcode.time2020.twelve;

/**
 *
 * 861 翻转矩阵后的得分
 *
 * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
 *
 * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
 *
 * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
 *
 * 返回尽可能高的分数。
 *
 *  
 *
 * 示例：
 *
 * 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * 输出：39
 * 解释：
 * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
 * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 20
 * 1 <= A[0].length <= 20
 * A[i][j] 是 0 或 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/score-after-flipping-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/12/7 10:14
 */
public class Solution861 {

    public int matrixScore(int[][] A) {
        int m = A.length,n = A[0].length;
        //先行转，令每一行的开头都是1
        for (int i = 0; i < m; i++) {
            if (A[i][0] != 1){
                for (int j = 0; j < n; j++) {
                    A[i][j] = (A[i][j] == 1) ? 0 : 1;
                }
            }
        }
        //除了第一列，对其他的列计算1的个数，如果少于一半则列转
        for (int j = 1; j < n; j++) {
            int count = 0;
            for (int i = 0; i < m; i++) {
                if (A[i][j] == 1){
                    count++;
                }
            }
            if (count < (m + 1) / 2){
                for (int i = 0; i < m; i++) {
                    A[i][j] = (A[i][j] == 1) ? 0 : 1;
                }
            }
        }
        //计算矩阵的值
        int res = 0;
        for (int j = 0; j < n; j++) {
            int temp = 1 << (n-j-1);
            for (int i = 0; i < m; i++) {
                res += A[i][j] * temp;
            }
        }
        return res;
    }

    /**
     * 不需要移动矩阵，直接计算
     * @param A
     * @return
     */
    public int matrixScore2(int[][] A) {
        int m = A.length,n = A[0].length;
        //第一位全是0
        int res = m * (1 << (n-1));
        for (int j = 1; j < n; j++) {
            int count = 0;
            for (int i = 0; i < m; i++) {
                //算出 1 的个数，注意如果此行第一个数是 0 那么此行必发生行转，所以要算出 1 的个数直接与行首数比较即可
                if (A[i][j] == A[i][0]){
                    count++;
                }
            }
            //取 0 或 1 中个数多者进行计算，多的那个最终列转必为1
            res += Math.max(count,m-count) * (1 << (n-j-1));
        }
        return res;
    }


    public static void main(String[] args) {
        Solution861 solution861 = new Solution861();
        int[][] a = new int[][]{{0,1},{0,1},{0,1},{0,0}};
        solution861.matrixScore(a);
    }

}
