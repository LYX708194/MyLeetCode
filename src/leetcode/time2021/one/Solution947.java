package leetcode.time2021.one;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 947 移除最多的同行或同列石头
 * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 *
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 *
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * 输出：5
 * 解释：一种移除 5 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
 * 2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
 * 3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
 * 4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
 * 5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。
 * 示例 2：
 *
 * 输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * 输出：3
 * 解释：一种移除 3 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
 * 2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
 * 3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
 * 示例 3：
 *
 * 输入：stones = [[0,0]]
 * 输出：0
 * 解释：[0,0] 是平面上唯一一块石头，所以不可以移除它。
 *  
 *
 * 提示：
 *
 * 1 <= stones.length <= 1000
 * 0 <= xi, yi <= 104
 * 不会有两块石头放在同一个坐标点上
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/most-stones-removed-with-same-row-or-column
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/1/15 12:08
 */
public class Solution947 {

    /**
     * 深度优先遍历
     * @author lyx
     * @date 2021/1/15 12:28
     * @return 
     */
    public int removeStones(int[][] stones) {
        int n = stones.length;
        List<List<Integer>> edge = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edge.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    edge.get(i).add(j);
                }
            }
        }
        boolean[] vis = new boolean[n];
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                num++;
                dfs(i, edge, vis);
            }
        }
        return n - num;
    }
    public void dfs(int x, List<List<Integer>> edge, boolean[] vis) {
        vis[x] = true;
        for (int y : edge.get(x)) {
            if (!vis[y]) {
                dfs(y, edge, vis);
            }
        }
    }

    /**
     * 
     * @author lyx
     * @date 2021/1/15 12:29
     * @return 
     */
    public int removeStones2(int[][] stones) {
        int n = stones.length;
        List<List<Integer>> edge = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; i++) {
            edge.add(new ArrayList<Integer>());
        }
        Map<Integer, List<Integer>> rec = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < n; i++) {
            if (!rec.containsKey(stones[i][0])) {
                rec.put(stones[i][0], new ArrayList<Integer>());
            }
            rec.get(stones[i][0]).add(i);
            //每一个石子的横坐标与纵坐标的范围均在 [1,10^4]，因此可以使用同一张哈希表，只需要令纵坐标加 10^4，以区别横坐标与纵坐标即可。
            if (!rec.containsKey(stones[i][1] + 10000)) {
                rec.put(stones[i][1] + 10000, new ArrayList<Integer>());
            }
            rec.get(stones[i][1] + 10000).add(i);
        }
        for (Map.Entry<Integer, List<Integer>> entry : rec.entrySet()) {
            List<Integer> list = entry.getValue();
            int k = list.size();
            for (int i = 1; i < k; i++) {
                edge.get(list.get(i - 1)).add(list.get(i));
                edge.get(list.get(i)).add(list.get(i - 1));
            }
        }
        boolean[] vis = new boolean[n];
        int num = 0;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                num++;
                dfs(i, edge, vis);
            }
        }
        return n - num;
    }

    /**
     * 并查集
     * @author lyx
     * @date 2021/1/15 12:41
     * @return
     */
    public int removeStones3(int[][] stones) {
        UnionFind unionFind = new UnionFind();

        for (int[] stone : stones) {
             unionFind.union(stone[0], stone[1] + 10000);
        }
        return stones.length - unionFind.getCount();
    }

    private class UnionFind {

        private Map<Integer, Integer> parent;
        private int count;

        public UnionFind() {
            this.parent = new HashMap<>();
            this.count = 0;
        }

        public int getCount() {
            return count;
        }

        public int find(int x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                // 并查集集中新加入一个结点，结点的父亲结点是它自己，所以连通分量的总数 +1
                count++;
            }

            if (x != parent.get(x)) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent.put(rootX, rootY);
            // 两个连通分量合并成为一个，连通分量的总数 -1
            count--;
        }
    }

}
