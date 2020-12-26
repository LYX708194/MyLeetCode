package leetcode.time2020.ten;

import leetcode.entity.TreeNode;

import java.util.*;

/**
 * @author lyx
 * @date 2020/10/26 18:54
 */
public class SolutionWeek04 {

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] ans = new int[nums.length];
        int[] copy = Arrays.copyOf(nums,nums.length);
        Arrays.sort(copy);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] > copy[j])  ans[i] = j;
            }
        }
        return ans;
    }

    /**
     * 设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。
     * 设计一种算法，寻找机器人从左上角移动到右下角的路径。网格中的障碍物和空位置分别用 1 和 0 来表示。
     * 返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为 0 行 0 列。如果没有可行的路径，返回空数组。
     */
    List<List<Integer>> ans = new LinkedList<>();
    int r = 0,c = 0;
    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        r = obstacleGrid.length;
        if (r == 0) return ans;
        c = obstacleGrid[0].length;
        //终点是障碍
        if (obstacleGrid[r-1][c-1] == 1){
            return ans;
        }
        boolean[][] visited = new boolean[r][c];
        pathBack(0,0,obstacleGrid,visited);
        return ans;
    }

    boolean pathBack(int x,int y,int[][] obstacleGrid,boolean[][] visited){
        //越界，不可访问，已被访问
        if (x>=r||y>=c||obstacleGrid[x][y] == 1||visited[x][y]){
            return false;
        }
        //无上述情况，可以访问
        ans.add(Arrays.asList(x,y));
        visited[x][y] = true;
        //是否到达终点
        if (x  == r-1 && y == c-1){
            return true;
        }
        //向右或向下搜索
        if (pathBack(x+1,y,obstacleGrid,visited)||pathBack(x, y+1, obstacleGrid, visited)){
            return true;
        }
        //撤销回溯
        ans.remove(ans.size()-1);
        return false;
    }

    /**
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * 示例:
     * 现有矩阵 matrix 如下：
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     * 给定 target = 20，返回 false。
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int r = matrix.length,c = matrix[0].length;
        int x = 0,y = matrix[0].length-1;
        while(x < r && y >= 0){
            if(matrix[x][y] == target){
                return true;
            }else if(matrix[x][y] > target){
                y--;
            }else if(matrix[x][y] < target){
                x++;
            }
        }
        return false;
    }

    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     */
    public String replaceSpace(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Character c:s.toCharArray()) {
            if (c == ' '){
                stringBuilder.append("%20");
            }else{
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
     * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
     */
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
        }
        for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
            if (!set.add(entry.getValue())){
                return false;
            }
        }
        return true;
    }

    /**
     * 我们从二叉树的根节点 root 开始进行深度优先搜索。
     * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
     * 如果节点只有一个子节点，那么保证该子节点为左子节点。
     * 给出遍历输出 S，还原树并返回其根节点 root。
     */
    public TreeNode recoverFromPreorder(String S) {
        Stack<TreeNode> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            //查看层数
            int level = 0;
            while (S.charAt(i) == '-'){
                level++;
                i++;
            }
            //获得值
            int value = 0;
            while(i<S.length()&&S.charAt(i)!='-'){
                value = value * 10 + (S.charAt(i)-'0');
                i++;
            }
            //创建新节点
            TreeNode node = new TreeNode(value);
            while(stack.size() > level+1){
                stack.pop();
            }
            if (!stack.isEmpty()){
                if (stack.peek().left == null){
                    stack.peek().left = node;
                }else{
                    stack.peek().right = node;
                }
            }
            //入栈
            stack.add(node);
        }
        //除了根节点，全部出栈
        while(stack.size()>1){
            stack.pop();
        }
        //返回根节点
        return stack.peek();
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * dp[i][j] 表示子串 s[i..j] 是否为回文子串  dp[i][j] = (s[i] == s[j]) and dp[i + 1][j - 1]
     * i 和 j 的关系是 i <= j  边界情况 ：表达式 [i + 1, j - 1] 不构成区间，即长度严格小于 2，即 j - 1 - (i + 1) + 1 < 2 ---->j-i<3
     *https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        int maxLength = 1;
        int start = 0;
        boolean[][] dp = new boolean[n][n];
        //初始化
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                //首尾不相等，肯定不是回文串
                if (s.charAt(i) != s.charAt(j)){
                    dp[i][j] = false;
                }else{
                    //表达式 [i + 1, j - 1] 不构成区间，即长度严格小于 2，即 j - 1 - (i + 1) + 1 < 2 ---->j-i<3
                    if (j-i < 3){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i+1][j-1];
                    }
                }

                if (dp[i][j] && j - i + 1 > maxLength){
                    maxLength = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start,start+maxLength);
    }

    /**
     * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
     * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
     * 计算从根到叶子节点生成的所有数字之和。
     * 说明: 叶子节点是指没有子节点的节点。
     */
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        dfs(root,0);
        return sum;
    }
    void dfs(TreeNode root,int num){
        if(root == null)  return;
        if (root.left == null && root.right == null){
            sum += num*10+root.val;
            return;
        }
        dfs(root.left, num*10+root.val);
        dfs(root.right, num*10+root.val);
    }

    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你返回 非空 子序列元素和的最大值，子序列需要满足：子序列中每两个 相邻 的整数 nums[i] 和 nums[j] ，它们在原数组中的下标 i 和 j 满足 i < j 且 j - i <= k 。
     * 数组的子序列定义为：将数组中的若干个数字删除（可以删除 0 个数字），剩下的数字按照原本的顺序排布。
     * dp[i] 为以nums[i]为结尾的子序列的最大元素和，又因为如果nums[i]不为子序列的第一个元素，则子序列中的上一个元素必然在{nums[i - 1],nums[i - 2],...,nums[i - k]}中，所以
     * dp[i] = max(dp[i - 1] + nums[i],dp[i - 2] + nums[i],dp[i - 3] + nums[i],...,dp[i - k] + nums[i],nums[i])
     * dp[i - 1]就是找dp[i- k - 1]到dp[ i - 2]的最大值，dp[i] 就是找dp[i - k]到dp[i - 1]的最大值，那这不就是大小为k的滑动窗口吗,用单调队列即可以O(n)的时间复杂度求出所有窗口的最大值。
     * https://leetcode-cn.com/problems/constrained-subsequence-sum/solution/dong-tai-gui-hua-hua-dong-chuang-kou-dan-diao-dui-/
     */
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        //状态定义： dp[i] 选中i元素的最大值
        //状态转移方程：dp[i]=  Math.max(dp[i-1],dp[i-2],dp[i-k])+nums[i]
        //定义一个双端队列，队列存的是索引。如果当前值比最左侧的大，把左侧元素弹出，始终保持最左侧的是最大值
        Deque<Integer> deque = new LinkedList<>();
        deque.add(0);
        int[] dp = new int[n];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < n; i++) {
            int idx = deque.getFirst();
            dp[i] = Math.max(nums[i],dp[idx] + nums[i]);
            res = Math.max(res,dp[i]);
            while (!deque.isEmpty() && (i - deque.getFirst() >= k || dp[deque.getFirst()] < dp[i])) {
                deque.pollFirst();
            }
            deque.add(i);
        }
        return res;
    }
    /**
     * 超时
     int n = nums.length;
     int[] dp = new int[n];
     dp[0] = nums[0];
     int res = dp[0];
     for (int i = 1; i < n; i++) {
         dp[i] = nums[i];
         for (int j = Math.max(0,i-k); j < i; j++) {
            dp[i] = Math.max(dp[i],dp[j]+nums[i]);
        }
         res = Math.max(res,dp[i]);
     }
     return res;
     */

    /**
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     *
     * 返回滑动窗口中的最大值。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n*k == 0) return new int[0];
        //双向队列，保存当前窗口的最大值的数组位置，保证队列中数组位置的数值按从大到小排序
        Deque<Integer> deque = new ArrayDeque<>();
        int[] ans = new int[n-k+1];
        for (int i = 0; i < n; i++) {
            //保证从大到小，如果前面数小泽需要依次弹出
            while (!deque.isEmpty() && nums[deque.getLast()]<nums[i]){
                deque.removeLast();
            }
            //添加当前值对应的数组下标
            deque.addLast(i);
            //队头不在当前窗口范围内
            if (deque.getFirst() <= i-k){
                deque.removeFirst();
            }
            //当窗口长度为k时，保存当前窗口中最大值
            if (i+1 >= k){
                ans[i+1-k] = nums[deque.getFirst()];
            }
        }
        return ans;
    }
    /**超出时间限制
     int n = nums.length;
     if (n*k == 0) return new int[0];
     int[] ans = new int[n-k+1];
     for (int i = 0; i < n-k+1; i++) {
         int max = Integer.MIN_VALUE;
         for (int j = i; j < i+k; j++) {
             max = Math.max(max,nums[j]);
         }
         ans[i] = max;
     }
     return ans;
     */


    /**
     * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
     * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
     * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
     *
     * 思路：
     * 对于一个陆地格子的每条边，它被算作岛屿的周长当且仅当这条边为网格的边界或者相邻的另一个格子为水域。
     * 因此，我们可以遍历每个陆地格子，看其四个方向是否为边界或者水域，如果是，将这条边的贡献（即 1）加入答案 ans 中即可。
     */
    public int islandPerimeter(int[][] grid) {
        int ans = 0;
        //dx,dy相对应，分别为下，右，上，左
        int[] dx = new int[]{0,1,0,-1};
        int[] dy = new int[]{1,0,-1,0};
        int n = grid.length,m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //此格是岛屿，需要计算
                if (grid[i][j] == 1){
                    int t = 0;
                    //判断各个方向
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if (x < 0 || y < 0 || x >= n || y >= m || grid[x][y] == 0 ){
                            t += 1;
                        }
                    }
                    ans += t;
                }
            }
        }
        return ans;
    }

    /**
     * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
     * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
     * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
     */
    //dx,dy相对应，分别为下，右，上，左
    int[] dx = new int[]{0,1,0,-1};
    int[] dy = new int[]{1,0,-1,0};
    public int maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                ans = Math.max(ans,dfs(grid,i,j));
            }
        }
        return ans;
    }
    int dfs(int[][] grid,int curX,int curY){
        if (curX < 0 || curY < 0 || curX >= grid.length || curY >= grid[0].length ||grid[curX][curY] == 0)  return 0;
        grid[curX][curY] = 0;
        int res = 1;
        for (int i = 0; i < 4; i++) {
            int x = curX + dx[i];
            int y = curY + dy[i];
            res += dfs(grid, x, y);
        }
        return res;
    }

    /**
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     */
    public int numIslands(char[][] grid) {
        int ans = 0;
        if (grid == null || grid.length == 0)   return 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1'){
                    ans += 1;
                    dfs(grid,i,j);
                }
            }
        }
        return ans;
    }
    void dfs(char[][] grid,int x,int y){
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length ||grid[x][y] == '0')  return ;
        //连通的修改为0，这样每个岛屿就只加一次
        grid[x][y] = '0';
        dfs(grid, x+1, y);
        dfs(grid, x-1, y);
        dfs(grid, x, y+1);
        dfs(grid, x, y-1);
    }

    public static void main(String[] args) {
        SolutionWeek04 s = new SolutionWeek04();
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(3);
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());
    }

}
