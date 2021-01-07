package leetcode.time2021.one;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 547 省份数量
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 *
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 *
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 *
 * 返回矩阵中 省份 的数量。
 * 示例 1：
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 * 示例 2：
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 * 提示：
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] 为 1 或 0
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-provinces
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/1/7 14:45
 */
public class Solution547 {

    /**
     * 深度优先遍历
     * @author lyx
     * @date 2021/1/7 16:10
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int ans = 0;
        boolean[] isVisit = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!isVisit[i]){
                dfs(isConnected,isVisit,n,i);
                ans++;
            }
        }
        return ans;
    }
    public void dfs(int[][] isConnected,boolean[] isVisit,int n,int i){
        for (int j = 0; j < n; j++) {
            if (isConnected[i][j] == 1 && !isVisit[j]){
                isVisit[j] = true;
                dfs(isConnected,isVisit,n,j);
            }
        }
    }

    /**
     * 广度优先遍历
     * @author lyx
     * @date 2021/1/7 16:14
     * @return
     */
    public int findCircleNum2(int[][] isConnected) {
        int n = isConnected.length;
        int ans = 0;
        boolean[] isVisit = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (!isVisit[i]){
                queue.offer(i);
                while (!queue.isEmpty()){
                    int j = queue.poll();
                    isVisit[j] = true;
                    for (int k = 0; k < n; k++) {
                        if (isConnected[j][k] == 1 && !isVisit[k]){
                            queue.offer(k);
                        }
                    }
                }
                ans++;
            }
        }
        return ans;
    }

    /**
     * 并查集
     * @author lyx
     * @date 2021/1/7 16:15
     * @return
     */
    public int findCircleNum3(int[][] isConnected) {
        int provinces = isConnected.length;
        int[] parent = new int[provinces];
        for (int i = 0; i < provinces; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < provinces; i++) {
            for (int j = i + 1; j < provinces; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        int circles = 0;
        for (int i = 0; i < provinces; i++) {
            if (parent[i] == i) {
                circles++;
            }
        }
        return circles;
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }


}
