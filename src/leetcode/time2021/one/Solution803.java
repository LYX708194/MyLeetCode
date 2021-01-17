package leetcode.time2021.one;

/**
 * 803 打砖头
 * 有一个 m x n 的二元网格，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：
 *
 * 一块砖直接连接到网格的顶部，或者
 * 至少有一块相邻（4 个方向之一）砖块 稳定 不会掉落时
 * 给你一个数组 hits ，这是需要依次消除砖块的位置。每当消除 hits[i] = (rowi, coli) 位置上的砖块时，对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而掉落。一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。
 *
 * 返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。
 *
 * 注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
 * 输出：[2]
 * 解释：
 * 网格开始为：
 * [[1,0,0,0]，
 *  [1,1,1,0]]
 * 消除 (1,0) 处加粗的砖块，得到网格：
 * [[1,0,0,0]
 *  [0,1,1,0]]
 * 两个加粗的砖不再稳定，因为它们不再与顶部相连，也不再与另一个稳定的砖相邻，因此它们将掉落。得到网格：
 * [[1,0,0,0],
 *  [0,0,0,0]]
 * 因此，结果为 [2] 。
 * 示例 2：
 *
 * 输入：grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
 * 输出：[0,0]
 * 解释：
 * 网格开始为：
 * [[1,0,0,0],
 *  [1,1,0,0]]
 * 消除 (1,1) 处加粗的砖块，得到网格：
 * [[1,0,0,0],
 *  [1,0,0,0]]
 * 剩下的砖都很稳定，所以不会掉落。网格保持不变：
 * [[1,0,0,0],
 *  [1,0,0,0]]
 * 接下来消除 (1,0) 处加粗的砖块，得到网格：
 * [[1,0,0,0],
 *  [0,0,0,0]]
 * 剩下的砖块仍然是稳定的，所以不会有砖块掉落。
 * 因此，结果为 [0,0] 。
 *  
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * grid[i][j] 为 0 或 1
 * 1 <= hits.length <= 4 * 104
 * hits[i].length == 2
 * 0 <= xi <= m - 1
 * 0 <= yi <= n - 1
 * 所有 (xi, yi) 互不相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bricks-falling-when-hit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/1/17 11:21
 */
public class Solution803 {


    public int[] hitBricks(int[][] grid, int[][] hits) {
        int h = grid.length, w = grid[0].length;

        UnionFind uf = new UnionFind(h * w + 1);
        //初始化status数组
        int[][] status = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                status[i][j] = grid[i][j];
            }
        }
        //将要打掉的砖头状态设置为0
        for (int i = 0; i < hits.length; i++) {
            status[hits[i][0]][hits[i][1]] = 0;
        }
        //构建并查集
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (status[i][j] == 1) {
                    //位于第一列的是稳定的，直接与
                    if (i == 0) {
                        uf.merge(h * w, i * w + j);
                    }
                    //向上合并
                    if (i > 0 && status[i - 1][j] == 1) {
                        uf.merge(i * w + j, (i - 1) * w + j);
                    }
                    //向左合并
                    if (j > 0 && status[i][j - 1] == 1) {
                        uf.merge(i * w + j, i * w + j - 1);
                    }
                }
            }
        }
        int[][] directions = {{0, 1},{1, 0},{0, -1},{-1, 0}};
        int[] ret = new int[hits.length];
        //从后往前遍历
        for (int i = hits.length - 1; i >= 0; i--) {
            int r = hits[i][0], c = hits[i][1];
            //此位置没有砖块，故 res 为0
            if (grid[r][c] == 0) {
                continue;
            }
            //记录原先的与顶层相连，即稳定的砖头的数量，
            int prev = uf.size(h * w);
            //此砖头是顶层的，直接与顶层合并
            if (r == 0) {
                uf.merge(c, h * w);
            }
            //遍历各个方向的砖头，看是否能够合并到一起
            for (int[] direction : directions) {
                int dr = direction[0], dc = direction[1];
                int nr = r + dr, nc = c + dc;

                if (nr >= 0 && nr < h && nc >= 0 && nc < w) {
                    if (status[nr][nc] == 1) {
                        uf.merge(r * w + c, nr * w + nc);
                    }
                }
            }
            //再次获取稳定的砖头的数量
            int size = uf.size(h * w);
            ret[i] = Math.max(0, size - prev - 1);
            status[r][c] = 1;
        }
        return ret;
    }


    private class UnionFind {
        //父亲数组
        int[] f;
        //此集合的数量
        int[] sz;

        public UnionFind(int n) {
            f = new int[n];
            sz = new int[n];
            for (int i = 0; i < n; i++) {
                f[i] = i;
                sz[i] = 1;
            }
        }

        public int find(int x) {
            if (f[x] == x) {
                return x;
            }
            int newf = find(f[x]);
            f[x] = newf;
            return f[x];
        }

        public void merge(int x, int y) {
            int fx = find(x), fy = find(y);
            if (fx == fy) {
                return;
            }
            f[fx] = fy;
            sz[fy] += sz[fx];
        }

        public int size(int x) {
            return sz[find(x)];
        }
    }


    // 标记与顶部相连
    private static final int TOP = 2;
    // 标记有砖块
    private static final int BRICK = 1;
    // 标记无砖块
    private static final int EMPTY = 0;
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int[] hitBricks2(int[][] grid, int[][] hits) {
        int[] res = new int[hits.length];
        // 移除所有hits位置的砖块
        for (int[] hit : hits) {
            grid[hit[0]][hit[1]]--;
        }

        // 把所有与top相连的标记为2
        for (int i = 0; i < grid[0].length; i++) {
            dfs(0, i, grid);
        }

        // Add back the hited Bricks
        for (int i = hits.length - 1; i >= 0; i--) {
            int x = hits[i][0], y = hits[i][1];
            grid[x][y]++;
            // 加回去之后的情况为0或1，为1说明原来这里确实有砖块
            if (grid[x][y] == BRICK && isConnected(x, y, grid)) {
                // 当前位置有砖块，而且与顶部相连，做dfs
                res[i] = dfs(x, y, grid) - 1;
            }
        }

        return res;
    }

    private int dfs(int i, int j, int[][] grid) {
        // grid[i][j] == BRICK 代表有砖块
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != BRICK) {
            return 0;
        }
        grid[i][j] = 2;
        return dfs(i + 1, j, grid)
                + dfs(i - 1, j, grid)
                + dfs(i, j + 1, grid)
                + dfs(i, j - 1, grid) + 1;
    }
    // isConnected用来判断当前坐标是否和顶部相连
    private boolean isConnected(int i, int j, int[][] grid) {
        // 在第0行必然相连
        if (i == 0) {
            return true;
        }
        for (int[] d : DIRS) {
            int x = i + d[0], y = j + d[1];
            // 如果周围的四个点有与顶部相连的，那这个点也是与顶部相连的
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == TOP) {
                return true;
            }
        }
        return false;
    }

}
