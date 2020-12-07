package leecode.time2020.ten;

import leecode.entity.ListNode;
import leecode.entity.TreeNode;

import java.util.*;

/**
 * @author lyx
 * @date 2020/10/1 0:07
 */
public class SolutionWeek01 {

    /**
     * 小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves， 字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
     * 出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。
     *示例 1：
     * 输入：leaves = "rrryyyrryyyrr"
     * 输出：2
     * 解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"
     *
     *我们用 f[i][j]表示对于第 0 片到第 i 片叶子（进行调整操作，并且第 i 片叶子处于状态 j 时的最小操作次数。在推导状态转移方程时，我们可以分别对于每一种状态进行分析。
     *
     * 当 j=0 时，我们需要将第 i 片叶子变成红色，并且第 i−1 片叶子也只能处于 j=0 的状态（因为没有编号更小的状态了），因此有状态转移方程：
     * f[i][0]=f[i−1][0]+isYellow(i)
     * 其中 isYellow(i) 为示性函数，当第 i 片叶子为黄色时为 1，红色时为 0。
     *
     * 当 j=1 时，我们需要将第 i 片叶子变成黄色，而第 i−1 片叶子既可以处于 j=1 的状态，也可以处于 j=0 的状态，我们选择其中的较小值，因此有状态转移方程：
     * f[i][1]=min{f[i−1][0],f[i−1][1]}+isRed(i)
     *
     * 其中 isRed(i) 为示性函数，当第 i 片叶子为红色时为 1，黄色时为 0。
     *
     * 当 j=2 时，我们需要将第 i 片叶子变成红色，而第 i-1 片叶子即可以处于 j=2 的状态，也可以处于 j=1 的状态
     * （注意这里不能处于 j=0 的状态，因为每一种状态包含的叶子数量必须至少为 1），我们选择其中的较小值，因此有状态转移方程：
     * f[i][2]=min{f[i−1][1],f[i−1][2]}+isYellow(i)
     *
     * 最终的答案即为 f[n−1][2]，其中 n 是字符串 leaves 的长度，也就是树叶的总数。
     */
    public int minimumOperations(String leaves) {
        int n = leaves.length();
        /*
            状态数组，state[i][j]中：i表示终止下标   j表示：0为左半边，1为中间部分，2为右半边
            state[i][j] 表示 从0到i需要调整的叶子数
         */
        int[][] f = new int[n][3];
        /*
            记录 已知状态数组元素：1、第一个叶子，必须是左半部分，所以只需判断是不是 黄色叶子 即可
                                2、第一个叶子，必须是左半部分，所以 state[0][1] 和 state[0][2] 都是无效的
                                  3、第二个叶子，可以是左半部分，也可以是中间部分，但是不能是右半部分(每个区间必须有叶子)， 因此 state[1][2]是无效的
        */
        f[0][0] = leaves.charAt(0) == 'y' ? 1 : 0;
        f[0][1] = f[0][2] = f[1][2] = Integer.MAX_VALUE;
        //遍历 原叶集，生成状态数组
        for (int i = 1; i < n; ++i) {
            int isRed = leaves.charAt(i) == 'r' ? 1 : 0;
            int isYellow = leaves.charAt(i) == 'y' ? 1 : 0;
            f[i][0] = f[i - 1][0] + isYellow;
            f[i][1] = Math.min(f[i - 1][0], f[i - 1][1]) + isRed;
            if (i >= 2) {  // 右半部分 的叶子 必须是第2个元素之后的元素
                f[i][2] = Math.min(f[i - 1][1], f[i - 1][2]) + isYellow;
            }
        }
        /*
            最终结果 为 state[length - 1][2]
            因为 state[i][j]最终结果的 i 必须为 length - 1，state[length - 1][j] 中的 j 必须为2
         */
        return f[n - 1][2];
    }

    /**
     *  给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
     * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
     */
    public int numJewelsInStones(String J, String S) {
        int ans = 0;
        Set<Character> set = new HashSet<Character>();
        for(int i = 0;i<J.length();i++){
            set.add(J.charAt(i));
        }
        for(int i = 0;i<S.length();i++){
            if(set.contains(S.charAt(i))){
                ans++;
            }
        }
        return ans;
    }


    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = -1;
        if (nums[n-1] < 0) return 0;
        for (int i = 1; i <= n; i++) {
            for (int j = n-1; j >= 0; j--) {
                if ((j>0&&nums[j]>=i&&nums[j-1]<i&&i==n-j)||(j == 0 && nums[0]>=i&&i==n-j)){
                    ans = Math.max(ans,i);
                }
                if (n-j>i) break;
            }
        }
        return ans;
    }

    public boolean isEvenOddTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        boolean isJi = true;  //是否是奇数
        while (!queue.isEmpty()){
            int left = isJi? 0:Integer.MAX_VALUE;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left!=null)queue.offer(cur.left);
                if (cur.right!=null)queue.offer(cur.right);
                if (isJi){
                    if (cur.val%2 == 0) return false;
                    if (cur.val<left){
                        return false;
                    }else{
                        left = cur.val;
                    }
                }else{
                    if (cur.val%2 > 0) return false;
                    if (cur.val>left){
                        return false;
                    }else{
                        left = cur.val;
                    }
                }
                isJi = !isJi;
            }
        }
        return true;
    }


    /**
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
     * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
     * 注意：
     * 答案中不可以包含重复的四元组。
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        fourSumBack(nums,target,0,new ArrayList<>(),results);
        return results;
    }
    void fourSumBack(int[] nums,int target,int index,List<Integer> res,List<List<Integer>> results){
        if (res.size() == 4){
            if (target==0){
                results.add(new ArrayList<>(res));
            }
            return;
        }
        for (int i = index; i < nums.length; i++) {
            /**
             * 如果剩余可选的数字数量少于 n，则剪掉（递归返回）；
             * 每层递归中，从第二轮循环开始，如果数组中当前数字和前一个相同，则剪掉（进行下一次循环，这条的任务就是去重）；
             * 如果 当前数字 + 已确定数字的和 + (n - 1) * 排序后数组中当前数字的下一个数字 > target，则说明后面的数无论怎么选，加起来都一定大于 target，所以剪掉（递归返回）；
             * 如果 当前数字 + 已确定数字的和 + (n - 1) * 排序后数组最后一个数字 < target，则说明后面的数无论怎么选，加起来都一定小于 target，所以剪掉（进行下一次循环）；
             */
            if(nums.length - i  < 4 - res.size())return;
            if (index!=i && nums[i]==nums[i-1]) continue;
            if(i < nums.length - 1 && target - nums[i] - (3 - res.size()) * nums[i + 1] < 0)return;
            if(i < nums.length - 1 && target - nums[i] - (3 - res.size()) * nums[nums.length - 1] > 0)continue;
            res.add(nums[i]);
            fourSumBack(nums, target-nums[i], i+1, res, results);
            res.remove(res.size()-1);
        }
    }


    /**
     * 详解： https://leetcode-cn.com/problems/sum-of-distances-in-tree/solution/shu-zhong-ju-chi-zhi-he-by-leetcode-solution/
     * 定义 dp[u] 表示以 uu 为根的子树，它的所有子节点到它的距离之和，同时定义 sz[u] 表示以 uu 为根的子树的节点数量，不难得出如下的转移方程：
     * dp[u]= ∑dp[v]+sz[v]（v∈son[u]）
     *其中son[u] 表示 u 的所有后代节点集合。转移方程表示的含义就是考虑每个后代节点 v，已知 v 的所有子节点到它的距离之和为 dp[v]，
     * 那么这些节点到 u 的距离之和还要考虑 u→v 这条边的贡献。考虑这条边长度为 1，一共有 sz[v] 个节点到节点 u 的距离会包含这条边，因此贡献即为 11×sz[v]=sz[v]。
     * 我们遍历整棵树，从叶子节点开始自底向上递推到根节点 root 即能得出最后的答案为 dp[root]。
     *假设 u 的某个后代节点为 v，如果要算 v 的答案，本来我们要以 v 为根来进行一次树形动态规划。
     * 但是利用已有的信息，我们可以考虑树的形态做一次改变，让 v 换到根的位置，uu 变为其孩子节点，同时维护原有的 dp 信息。
     * 在这一次的转变中，我们观察到除了 u 和 v 的 dp 值，其他节点的 dp 值都不会改变，因此只要更新 dp[u] 和 dp[v] 的值即可。
     * 那么我们来看 v 换到根的位置的时候怎么利用已有信息求出 dp[u] 和dp[v] 的值。重新回顾第一次树形动态规划的转移方程，
     * 我们可以知道当 u 变为 v 的孩子的时候 v 不在 u 的后代集合 son[u] 中了，因此此时 dp[u] 需要减去 v 的贡献，即
     * dp[u]=dp[u]−(dp[v]+sz[v])
     * 同时 sz[u] 也要相应减去sz[v]。
     * 而 v 的后代节点集合中多出了 uu，因此 dp[v] 的值要由 u 更新上来，即
     * dp[v]=dp[v]+(dp[u]+sz[u])
     * 同时sz[v] 也要相应加上 sz[u]。
     */
    int[] ans;
    int[] sz;
    int[] dp;
    List<List<Integer>> graph;
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        ans = new int[N];
        sz = new int[N];
        dp = new int[N];
        graph = new ArrayList<List<Integer>>();
        for (int i = 0; i < N; ++i) {
            graph.add(new ArrayList<Integer>());
        }
        for (int[] edge: edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        dfs(0, -1);
        dfs2(0, -1);
        return ans;
    }
    public void dfs(int u, int f) {
        sz[u] = 1;
        dp[u] = 0;
        for (int v: graph.get(u)) {
            if (v == f) {
                continue;
            }
            dfs(v, u);
            dp[u] += dp[v] + sz[v];
            sz[u] += sz[v];
        }
    }

    public void dfs2(int u, int f) {
        ans[u] = dp[u];
        for (int v: graph.get(u)) {
            if (v == f) {
                continue;
            }
            int pu = dp[u], pv = dp[v];
            int su = sz[u], sv = sz[v];

            dp[u] -= dp[v] + sz[v];
            sz[u] -= sz[v];
            dp[v] += dp[u] + sz[u];
            sz[v] += sz[u];

            dfs2(v, u);

            dp[u] = pu;
            dp[v] = pv;
            sz[u] = su;
            sz[v] = sv;
        }
    }

    /**
     * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     * 注意:不能使用代码库中的排序函数来解决这道题。
     * 示例:   输入: [2,0,2,1,1,0]   输出: [0,0,1,1,2,2]
     * 一个直观的解决方案是使用计数排序的两趟扫描算法。
     * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组
     * 双指针
     * 两个指针分别指向 下一个0、2应该存放的位置
     * 遇0则交换 当前元素 和 p0空间的值，并 使得 p0指针 指向 下一个0应该存放的位置，遍历下一个元素
     * 遇2则交换 当前元素 和 p2空间的值，并 使得 p2指针 指向 下一个2应该存放的位置，继续遍历 交换后的当前元素
     */
    public void sortColors(int[] nums) {
        if (nums == null ||nums.length<=1) return;
        int p0 = 0,p2 = nums.length-1;
        for (int i = 0; i <= p2; i++) {
            if (nums[i] == 0){
                nums[i] = nums[p0];
                nums[p0] = 0;
                p0++;
            }else if (nums[i]==2){
                nums[i] = nums[p2];
                nums[p2] = 2;
                p2--;
                i--;    //此时i的位置是p2置换来的，还没验证时什么数，稍后会i++，所以这里先减
            }
        }
    }

    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     */
    public void reverseString(char[] s) {
        int len = s.length-1;
        for(int i = 0;i<len;i++){
            char n = s[i];
            s[i] = s[len];
            s[len--] = n;
        }
    }


    public boolean hasCycle(ListNode head) {
//        if(head == null||head.next == null)  return false;
//        ListNode l1 = head;
//        ListNode l2 = head.next;
//        while(l1!=l2){
//            if (l2==null||l2.next == null)  return false;
//            l1 = l1.next;
//            l2 = l2.next.next;
//        }
//        return true;

        Set<Integer> set = new HashSet<>();
        while(head!=null){
            if(set.contains(head.val)){
                return true;
            }else{
                set.add(head.val);
                head = head.next;
            }
        }
        return false;
    }



    public int maxDepth(String s) {
        int max = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                ans++;
                max = Math.max(ans,max);
            }else if (s.charAt(i)==')'){
                ans--;
            }
        }
        return Math.max(ans,max);
    }

    public int maximalNetworkRank(int n, int[][] roads) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                ans = Math.max(ans,getNetworkRank(i,j,roads));
            }
        }
        return ans;
    }
    int getNetworkRank(int cityA,int cityB,int[][] roads){
        int i = 0;
        for(int[] road:roads){
            if(cityA == road[0]||cityA == road[1]||cityB == road[0]||cityB == road[1]){
                i++;
            }
        }
        return i;
    }


    public boolean checkPalindromeFormation(String a, String b) {
        String prefixA = "";
        String prefixB = "";
        String suffixA = "";
        String suffixB = "";
        int n = a.length()-1;
        if (n<=1) return true;
        for (int i = 0; i < n; i++) {
            prefixA = a.substring(0,i);
            prefixB = b.substring(0,i);
            suffixA = a.substring(i,n+1);
            suffixB = b.substring(i,n+1);
            if (isFormation(prefixA+suffixB)||isFormation(prefixB+suffixA)){
                return true;
            }
        }
        return false;
    }
    boolean isFormation(String s){
        int n = s.length()-1,i = 0;
        while(i<n){
            if (s.charAt(i) == s.charAt(n)){
                i++;
                n--;
            }else{
                return false;
            }
        }
        return true;
    }


    /**
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % 2 != 0) {
            return false;  //和不是偶数，不可能被分为两半
        }
        int target = sum / 2;
        if (maxNum > target) {
            return false;   //最大数大于目标数，所以不可能出现剩下数等于target
        }
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        /**
         * 如果不选取任何正整数，则被选取的正整数等于 0。因此对于所有 0≤i<n，都有 dp[i][0]=true。
         * 当 i==0 时，只有一个正整数nums[0] 可以被选取，因此 dp[0][nums[0]]=true。
         * 对于 i>0 且 j>0的情况，如何确定 dp[i][j] 的值？需要分别考虑以下两种情况。
         * 如果 j≥nums[i]，则对于当前的数字 nums[i]，可以选取也可以不选取，两种情况只要有一个为 true，就有 dp[i][j]=true。
         *
         * 如果不选取 nums[i]，则 dp[i][j]=dp[i−1][j]；
         * 如果选取 nums[i]，则 dp[i][j]=dp[i−1][j−nums[i]]。
         * 如果 j<nums[i]，则在选取的数字的和等于 j 的情况下无法选取当前的数字 nums[i]，因此有 dp[i][j]=dp[i−1][j]。
         * dp[i][j] 表示从数组的 [0,i] 下标范围内选取若干个正整数（可以是 0 个），是否存在一种选取方案使得被选取的正整数的和等于 j
         * 状态转移方程如下：
         * dp[i][j]={   dp[i−1][j] ∣ dp[i−1][j−nums[i]],   j≥nums[i]
         *              dp[i−1][j],                          j<nums[i]
         */
        dp[0][nums[0]] = true;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }






    public static void main(String[] args) {
        SolutionWeek01 solutionWeek01 = new SolutionWeek01();
        System.out.println(solutionWeek01.checkPalindromeFormation("ulacfd","jizalu"));
    }

}
