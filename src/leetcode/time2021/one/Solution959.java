package leetcode.time2021.one;

import java.util.HashSet;
import java.util.Set;

/**
 * 959 由斜杆划分区域
 * 在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
 *
 * （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
 *
 * 返回区域的数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：
 * [
 *   " /",
 *   "/ "
 * ]
 * 输出：2
 * 解释：2x2 网格如下：
 *
 * 示例 2：
 *
 * 输入：
 * [
 *   " /",
 *   "  "
 * ]
 * 输出：1
 * 解释：2x2 网格如下：
 *
 * 示例 3：
 *
 * 输入：
 * [
 *   "\\/",
 *   "/\\"
 * ]
 * 输出：4
 * 解释：（回想一下，因为 \ 字符是转义的，所以 "\\/" 表示 \/，而 "/\\" 表示 /\。）
 * 2x2 网格如下：
 *
 * 示例 4：
 *
 * 输入：
 * [
 *   "/\\",
 *   "\\/"
 * ]
 * 输出：5
 * 解释：（回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。）
 * 2x2 网格如下：
 *
 * 示例 5：
 *
 * 输入：
 * [
 *   "//",
 *   "/ "
 * ]
 * 输出：3
 * 解释：2x2 网格如下：
 *
 *  
 *
 * 提示：
 *
 * 1 <= grid.length == grid[0].length <= 30
 * grid[i][j] 是 '/'、'\'、或 ' '。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regions-cut-by-slashes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/1/25 10:10
 */
public class Solution959 {

    /**
     * 并查集
     * 把一个单元格用双对角线分割为逻辑上的4个
     *  0
     * 3 1
     *  2
     */
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int[] f = new int[n * n * 4];
        for (int i = 0; i < n * n * 4; i++) {
            f[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int idx = i * n + j;
                // 如果该网格不在最后一行,则有网格在其下方与它相邻
                if (i < n - 1) {
                    // 计算与当前网格相邻的下方网格的idx
                    int bottom = idx + n;
                    merge(f, idx * 4 + 2, bottom * 4);
                }
                // 如果该网格不在最右一列，则有网格在其右边与它相邻
                if (j < n - 1) {
                    int right = idx + 1;
                    merge(f, idx * 4 + 1, right * 4 + 3);
                }
                if (grid[i].charAt(j) == '/') {
                   //如果当前的网格的字符是'/',则将左上角的0号和3号三角形相连，将右下角的1号和2号三角形相连
                    merge(f, idx * 4, idx * 4 + 3);
                    merge(f, idx * 4 + 1, idx * 4 + 2);
                } else if (grid[i].charAt(j) == '\\') {
                    // 如果当前的网格的字符是'\',则将左下角的2号和3号三角形相连，将右上角的0号和1号三角形相连
                    merge(f, idx * 4, idx * 4 + 1);
                    merge(f, idx * 4 + 2, idx * 4 + 3);
                } else {
                    //如果当前的网格的字符是空格，则将四个三角形互相连接起来
                    merge(f, idx * 4, idx * 4 + 1);
                    merge(f, idx * 4 + 1, idx * 4 + 2);
                    merge(f, idx * 4 + 2, idx * 4 + 3);
                }
            }
        }
        Set<Integer> fathers = new HashSet<Integer>();
        for (int i = 0; i < n * n * 4; i++) {
            int fa = find(f, i);
            fathers.add(fa);
        }
        return fathers.size();
    }

    public int find(int[] f, int x) {
        if (f[x] == x) {
            return x;
        }
        int fa = find(f, f[x]);
        f[x] = fa;
        return fa;
    }

    public void merge(int[] f, int x, int y) {
        int fx = find(f, x);
        int fy = find(f, y);
        f[fx] = fy;
    }


}
