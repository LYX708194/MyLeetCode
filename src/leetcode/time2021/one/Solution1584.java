package leetcode.time2021.one;

import java.util.*;

/**
 * 1584 连接所有点的最小费用
 *给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 *
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 *
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * 输出：20
 * 解释：
 *
 * 我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
 * 注意到任意两个点之间只有唯一一条路径互相到达。
 * 示例 2：
 *
 * 输入：points = [[3,12],[-2,5],[-4,1]]
 * 输出：18
 * 示例 3：
 *
 * 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
 * 输出：4
 * 示例 4：
 *
 * 输入：points = [[-1000000,-1000000],[1000000,1000000]]
 * 输出：4000000
 * 示例 5：
 *
 * 输入：points = [[0,0]]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= points.length <= 1000
 * -106 <= xi, yi <= 106
 * 所有点 (xi, yi) 两两不同。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-cost-to-connect-all-points
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/1/19 11:50
 */
public class Solution1584 {

    /**
     * prim算法，以点为基础，每次找出最近的顶点，生成最小生成树
     * @param points
     * @return
     */
    public int minCostConnectPoints(int[][] points) {
        int len = points.length;
        int distance[] = new int[len];
        boolean isVisited[] = new boolean[len];
        int graph[][] = new int[len][len];
        int res = 0;
        for(int i = 0; i < len; i++){
            for(int j = 1; j < len; j++){
                graph[i][j] = dist(points,i,j);
                graph[j][i] = graph[i][j];
            }
        }
        //初始化第一个顶点的状态
        isVisited[0] = true;
        for(int i = 1; i < len; i++){
            distance[i] = graph[0][i];
        }
        for(int i = 1; i < len; i++){
            int index = -1;
            int min = Integer.MAX_VALUE;
            //找出对应边的权值最小的那个顶点
            for(int j = 1; j < len; j++){
                if(!isVisited[j] && distance[j] < min){
                    min = distance[j];
                    index = j;
                }
            }
            res += min;
            //更新该节点为已访问
            isVisited[index] = true;
            //更新distance数组
            for(int j = 0; j < len; j++){
                int temp;
                if(!isVisited[j] && (temp = graph[index][j]) < distance[j]){
                    distance[j] = temp;
                }
            }
        }
        return res;
    }

    /**
     * Kruskal算法,以边为基础，每次从边集合中寻找最小的边，然后判断该边的两个顶点是否属于同一个连通图。
     * @param points
     * @return
     */
    public int minCostConnectPoints2(int[][] points) {
        int n = points.length;
        DisjointSetUnion dsu = new DisjointSetUnion(n);
        List<Edge> edges = new ArrayList<Edge>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges.add(new Edge(dist(points, i, j), i, j));
            }
        }
        Collections.sort(edges, (e1,e2)->e1.len - e2.len);
        int ret = 0, num = 1;
        for (Edge edge : edges) {
            int len = edge.len, x = edge.x, y = edge.y;
            if (dsu.unionSet(x, y)) {
                ret += len;
                num++;
                if (num == n) {
                    break;
                }
            }
        }
        return ret;
    }

    public int dist(int[][] points, int x, int y) {
        //获得两点间距离
        return Math.abs(points[x][0] - points[y][0]) + Math.abs(points[x][1] - points[y][1]);
    }

    private class Edge {
        int len, x, y;

        public Edge(int len, int x, int y) {
            this.len = len;
            this.x = x;
            this.y = y;
        }
    }
    private class DisjointSetUnion {
        int[] f;
        int[] rank;
        int n;

        public DisjointSetUnion(int n) {
            this.n = n;
            this.rank = new int[n];
            Arrays.fill(this.rank, 1);
            this.f = new int[n];
            for (int i = 0; i < n; i++) {
                this.f[i] = i;
            }
        }

        public int find(int x) {
            return f[x] == x ? x : (f[x] = find(f[x]));
        }

        public boolean unionSet(int x, int y) {
            int fx = find(x), fy = find(y);
            if (fx == fy) {
                return false;
            }
            if (rank[fx] < rank[fy]) {
                int temp = fx;
                fx = fy;
                fy = temp;
            }
            rank[fx] += rank[fy];
            f[fy] = fx;
            return true;
        }
    }
}
