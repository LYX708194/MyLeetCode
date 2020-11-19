package leecode.time2020.eleven;

import java.util.*;

/**
 * @author lyx
 * @date 2020/11/17 10:30
 */
public class Solution1030 {

    int[] row = {0,1,0,-1};
    int[] col = {1,0,-1,0};

    /**
     * 广度优先排序
     */
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] res = new int[R * C][2];
        int i = 0;
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> queue = new ArrayDeque<>();
        int[] first = new int[]{r0,c0};
        queue.add(first);
        res[i++] = first;
        visited[r0][c0] = true;
        while (!queue.isEmpty()){
            int[] one = queue.poll();
            for (int j = 0; j < 4; j++) {
                int r = one[0] + row[j];
                int c = one[1] + col[j];
                if (r >= 0 && r < R && c >= 0 && c < C && !visited[r][c]){
                    int[] cur = new int[]{r,c};
                    res[i++] = cur;
                    queue.add(cur);
                    visited[r][c] = true;
                }
            }
        }
        return res;
    }

    /**
     * 直接排序
     */
    public int[][] allCellsDistOrder2(int R, int C, int r0, int c0) {
        int[][] res = new int[R * C][];
        int index = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                res[index++] = new int[]{i,j};
            }
        }
        Arrays.sort(res,(o1, o2) -> {
            return (Math.abs(o1[0] - r0) + Math.abs(o1[1] - c0)) - (Math.abs(o2[0] - r0) + Math.abs(o2[1] - c0));
        });
        return res;
    }

    /**
     * 桶排序
     * 实际在枚举所有点时，我们可以直接按照哈曼顿距离分桶。这样我们就可以实现线性的桶排序。
     */
    public int[][] allCellsDistOrder3(int R, int C, int r0, int c0) {
        int maxDist = Math.max(r0,R - 1 - r0) + Math.max(c0, C - 1 - c0);
        List<List<int[]>> bucket = new ArrayList<>();
        for (int i = 0; i <= maxDist; i++) {
            bucket.add(new ArrayList<>());
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int d = dist(i,j,r0,c0);
                bucket.get(d).add(new int[]{i,j});
            }
        }
        int index = 0;
        int[][] res = new int[R * C][];
        for (int i = 0; i <= maxDist; i++) {
            for (int[] one:bucket.get(i)) {
                res[index++] = one;
            }
        }
        return res;
    }
    public int dist(int r1,int c1,int r2,int c2){
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    public static void main(String[] args) {
        Solution1030 solution1030 = new Solution1030();
        solution1030.allCellsDistOrder(1,2,0,0);


    }

}
