package leetcode.LCP;

/**
 * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
 *
 * 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
 * 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
 * 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
 * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
 *
 * 示例 1：
 *
 * 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
 *
 * 输出：3
 *
 * 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
 *
 * 示例 2：
 *
 * 输入：n = 3, relation = [[0,2],[2,1]], k = 2
 *
 * 输出：0
 *
 * 解释：信息不能从小 A 处经过 2 轮传递到编号 2
 *
 * 限制：
 *
 * 2 <= n <= 10
 * 1 <= k <= 5
 * 1 <= relation.length <= 90, 且 relation[i].length == 2
 * 0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/chuan-di-xin-xi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/4/5 22:47
 */
public class Solution07 {


    public int numWays2(int n, int[][] relation, int k) {
        //dp[i][j]表示第i轮传给编号j的人的方案数
        int[][] dp = new int[k+1][n];
        dp[0][0] = 1;
        for (int i = 0; i < k; i++) {
            for (int[] a : relation) {
                dp[i+1][a[1]] += dp[i][a[0]];
            }
        }
        return dp[k][n-1];
    }

    public int numWays3(int n, int[][] relation, int k) {
        //dp[i][j]表示第i轮传给编号j的人的方案数,空间优化
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < k; i++) {
            int[] cnt = new int[n];
            for (int[] a : relation) {
                cnt[a[1]] += dp[a[0]];
            }
            dp = cnt;
        }
        return dp[n-1];
    }


    int ans;
    boolean[][] graph;

    public int numWays(int n, int[][] relation, int k) {
        ans = 0;
        graph = new boolean[n][n];
        for (int[] a : relation) {
            graph[a[0]][a[1]] = true;
        }
        dfs(0,k,n);
        return ans;
    }
    private void dfs(int cur,int k,int n){
        if (k == 0){
            if(cur == n-1)  ans++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (graph[cur][i]){
                dfs(i,k-1,n);
            }
        }
    }

}
