package leetcode.time2020.nine;

import leetcode.entity.ListNode;
import leetcode.entity.TreeNode;

import java.util.*;

/**
 * @author lyx
 * @date 2020/9/1 13:30
 */
public class Solution {



    /**
     * 给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。
     * 每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
     * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
     * @param nums
     * @return
     */
    public static boolean PredictTheWinner(int[] nums) {
        return helper(0,nums.length,0,0,true,nums);
    }

    public static boolean helper(int left,int right,int scoreA,int scoreB,boolean A,int[] nums){
        if (left>right){ //左右越界，没有牌了，比较得分，判断胜负（以A为主角
            return scoreA>=scoreB;
        }
        if (A){ //轮到A选牌,A是主角，只要左边或者右边有一种必胜情况，就说明可以必胜
            return helper(left+1,right,scoreA+nums[left],scoreB,false,nums)||helper(left, right-1, scoreA+nums[right], scoreB, false, nums);
        }else{  //轮到B选牌，不管B怎么选，此时只有左右两边都保证A是必胜的，才能保证A最终必胜！
            return helper(left+1,right,scoreA,scoreB+nums[left],true,nums)&&helper(left, right-1, scoreA, scoreB+nums[right], true, nums);
        }
    }

    /**
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
     * @param s
     * @return
     */
    public static boolean isNumber(String s) {
        boolean eFlag = false;
        boolean pointFlag = false;
        boolean numFlag = false;
        s = s.trim();
        if (s.length()==0||s.equals("")) return false;
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i)=='+'||s.charAt(i)=='-')&&(i==0||s.charAt(i-1)=='e'||s.charAt(i-1)=='E')){
                //此情况为 +- 需为第一个字符或者出现在e后面，否则不正确
            }else if (s.charAt(i)<='9'&&s.charAt(i)>='0'){
                //为数字
                numFlag = true;
            }else if (s.charAt(i)=='.'&&!pointFlag&&!eFlag){
                //else if (s.charAt(i)=='.'&&numFlag&&!pointFlag&&!eFlag){  .1为true，那么小数点前面可以没有数字
                //小数点只能出现一次且必须在e之前
                pointFlag = true;
            }else if ((s.charAt(i)=='e'||s.charAt(i)=='E')&&!eFlag&&numFlag){
                //e只能出现一次且前面必须为数字
                eFlag = true;
                //避免出现e后面没有数字，例如 123e 这种情况，将数字标志重置
                numFlag = false;
            }else{
                return false;
            }
        }
        //最后结果需出现数字
        return numFlag;
    }

    /**
     * 判断是否是回文数
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        //小于0或者个位数为0时不回文，x不为0
        if (x<0||(x%10==0&&x!=0)) return false;
        int ret = 0;
        while (x>ret){
            ret = ret*10+x%10;
            x/= 10;
        }
        //数字个数为奇数时，当跳出循环时，ret会比x多出一位，这时候如果x需要再加上刚刚除掉的那一位
        return x==ret||x*10+ret%10==ret;
    }

    /**
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击
     * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
     *
     * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        char[][] chess = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                chess[i][j] = '.';
        List<List<String>> res = new ArrayList<>();
        solve(res, chess, 0);
        return res;
    }

    private void solve(List<List<String>> res, char[][] chess, int row) {
        if (row == chess.length) {
            res.add(construct(chess));
            return;
        }
        for (int col = 0; col < chess.length; col++) {
            if (valid(chess, row, col)) {
                chess[row][col] = 'Q';
                solve(res, chess, row + 1);
                chess[row][col] = '.';
            }
        }
    }

    //row表示第几行，col表示第几列
    private boolean valid(char[][] chess, int row, int col) {
        //判断当前列有没有皇后,因为他是一行一行往下走的，
        //我们只需要检查走过的行数即可，通俗一点就是判断当前
        //坐标位置的上面有没有皇后
        for (int i = 0; i < row; i++) {
            if (chess[i][col] == 'Q') {
                return false;
            }
        }
        //判断当前坐标的右上角有没有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < chess.length; i--, j++) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        //判断当前坐标的左上角有没有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    //把数组转为list
    private List<String> construct(char[][] chess) {
        List<String> path = new ArrayList<>();
        for (int i = 0; i < chess.length; i++) {
            path.add(new String(chess[i]));
        }
        return path;
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int one = 0,sum = 0;
        ListNode listNode =  new ListNode(0);
        ListNode p = l1, q = l2,curr = listNode;
        while(p!=null||q!=null){
            int x = p!=null?p.val:0;
            int y = q!=null?q.val:0;
            sum = x+y+one;
            if (sum>=10){
                one = 1;
            }else{
                one = 0;
            }
            curr.next = new ListNode(sum%10);
            sum/=10;
            curr = curr.next;
            if (p!=null) p = p.next;
            if (q!=null) q = q.next;
        }
        if (sum == 1){
            curr.next = new ListNode(sum);
        }
        return listNode.next;
    }




    /**
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        if(root==null) return ret;
        solve(root, "", ret);
        return ret;
    }
    public void solve(TreeNode root, String cur, List<String> ret){
        if(root==null) return;
        cur += root.val;
        if(root.left==null&&root.right==null){
            ret.add(cur);
        }else{
            solve(root.left, cur+"->", ret);
            solve(root.right, cur+"->", ret);
        }
    }

    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> paths = new ArrayList<String>();
        constructPaths(root, "", paths);
        return paths;
    }
    public void constructPaths(TreeNode root, String path, List<String> paths) {
        if (root != null) {
            StringBuffer pathSB = new StringBuffer(path);
            pathSB.append(Integer.toString(root.val));
            if (root.left == null && root.right == null) {  // 当前节点是叶子节点
                paths.add(pathSB.toString());  // 把路径加入到答案中
            } else {
                pathSB.append("->");  // 当前节点不是叶子节点，继续递归遍历
                constructPaths(root.left, pathSB.toString(), paths);
                constructPaths(root.right, pathSB.toString(), paths);
            }
        }
    }


    /**
     * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
     * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
     * "123" "132" "213""231" "312" "321"
     * 给定 n 和 k，返回第 k 个排列。
     * 说明：给定 n 的范围是 [1, 9]。给定 k 的范围是[1,  n!]。
     */
    public static String getPermutation(int n, int k) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            ans.add(i);
        }
        int[] fact = new int[n];
        // 第一组首元素一定是 1
        fact[0] = 1;
        // 求剩下元素在对于每个 i 情况下的阶乘 n!
        for (int i = 1; i < n; i++) {
            fact[i] = i * fact[i - 1];
        }
        // 转变为找下标是第 k - 1 的排列情况
        k = k - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--) {
            int index = k / fact[i - 1];
            // 去掉上一轮计算过的数，初始下一轮的 k
            k = k % fact[i - 1];
            // 找到的第一个数先加到 sb 后面
            sb.append(ans.get(index));
            // 然后再把原来位置上的这个数去除掉
            ans.remove(index);
        }
        return sb.toString();
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length()==0) return 0;
        int length = 0,left = 0;
        HashMap<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))){
                //重复的那个字母的下一位
                left = Math.max(left,map.get(s.charAt(i))+1);
            }
            //重置重复字母的位序
            map.put(s.charAt(i),i);
            length = Math.max(length,i-left+1);
        }
        return length;
    }


    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root==null) {
            return new ArrayList<List<Integer>>();
        }
        //用来存放最终结果
        LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
        //创建一个队列，将根节点放入其中
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            //每次遍历的数量为队列的长度
            int size = queue.size();
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            //将这一层的元素全部取出，放入到临时数组中，如果节点的左右孩子不为空，继续放入队列
            for(int i=0;i<size;++i) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if(node.left!=null) {
                    queue.offer(node.left);
                }
                if(node.right!=null) {
                    queue.offer(node.right);
                }
            }
            res.add(0,tmp);
        }
        //翻转最终结果并返回  如果用arraylist存就需要反转
        // Collections.reverse(res);
        return res;
    }

    /**
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer,Integer> map = new HashMap();
        for (int i:nums) {
            if (!map.containsKey(i)){
                map.put(i,1);
            }else{
                map.put(i,map.get(i)+1);
            }
        }
        int max = 0;
        //找到次数最大的值
        for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
            if (entry.getValue()>max){
                max = entry.getValue();
            }
        }
        while(k>0){
            for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
                if (entry.getValue() == max){
                    res[k-1] = entry.getKey();
                    k--;
                }
            }
            //如果此次数没有找到，则减少出现次数继续循环
            max--;
        }
        return res;
    }

    /**
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     * @return
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> reg = new ArrayList<>();

        List<Integer> result = new ArrayList<>();
        back(1,k,n,reg,result);

        return  reg;

    }

    public static void back(int begin,int k,int n,List<List<Integer>> reg,List<Integer> result){
        if (result.size()==k){
            reg.add(new ArrayList<>(result));
            return;
        }
        for (int i = begin; i <= n; i++) {
            result.add(i);
            back(i+1,k,n,reg,result);
            result.remove(result.size()-1);
        }
    }

    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取。
     * 说明：
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。 
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        int toTarget = 0;
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        sumBack(candidates,target,toTarget,0,results,result);

        return results;
    }

    public static void sumBack(int[] candidates,int target,int toTarget,int begin,List<List<Integer>> results,List<Integer> result){
        if (toTarget == target){
            results.add(new ArrayList<>(result));
            return;
        }
        for (int i = begin; i <= candidates.length-1; i++) {
            if (candidates[i]+toTarget<=target){
                toTarget += candidates[i];
                result.add(candidates[i]);
            }else if (candidates[i]+toTarget>target){
                continue;
            }
            sumBack(candidates,target,toTarget,i,results,result);
            if (result.contains(candidates[i])){
                toTarget -= candidates[i];
                result.remove(result.size()-1);
            }
        }
    }

    /**
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的每个数字在每个组合中只能使用一次。
     * @return
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int toTarget = 0;
        Arrays.sort(candidates);
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        sumBack2(candidates,target,toTarget,0,results,result);

        return results;
    }
    public static void sumBack2(int[] candidates,int target,int toTarget,int begin,List<List<Integer>> results,List<Integer> result){
        if (toTarget == target){
            results.add(new ArrayList<>(result));
            return;
        }
        for (int i = begin; i <= candidates.length-1; i++) {
            if(i > begin && candidates[i] == candidates[i-1]) continue;
            int rs = candidates[i]+toTarget;
            if (rs<=target){
                result.add(candidates[i]);
                sumBack2(candidates,target,rs,i+1,results,result);
                result.remove(result.size()-1);
            }else{
                break;
            }
        }
    }

    /**
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     * 说明：
     * 所有数字都是正整数。
     * 解集不能包含重复的组合。
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> results = new ArrayList<>();
        sumBack3(k,n,1,new ArrayList<>(),results);
        return results;
    }

    static void sumBack3(int k,int n,int begin,List<Integer> result,List<List<Integer>> results){
        if (result.size()==k || n <= 0 ){
            if (result.size()==k && n == 0 ){
                results.add(new ArrayList<>(result));
            }
            return;
        }
        for (int i = begin; i <= 9; i++) {
            result.add(i);
            sumBack3(k,n-i,i+1,result,results);
            result.remove(result.size()-1);
        }
    }

    /**
     * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                sum +=  cur.val;
                TreeNode left = cur.left,right = cur.right;
                if (left!=null) queue.offer(left);  //判空，不然报空指针
                if (right!=null)queue.offer(right);
            }
            result.add(sum/size);
        }
        return result;
    }

    /**
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     * board =
     * [
     *   ['A','B','C','E'],
     *   ['S','F','C','S'],
     *   ['A','D','E','E']
     * ]
     * 给定 word = "ABCCED", 返回 true
     * 给定 word = "SEE", 返回 true
     * 给定 word = "ABCB", 返回 false
     */
    public boolean exist(char[][] board, String word) {
        int n = board.length,m = board[0].length;
        boolean[][] used = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Boolean flag = exitsBack(word,board,0,used,i,j);
                if (flag){
                    return true;
                }
            }
        }
        return false;
    }

    boolean exitsBack(String word,char[][] board,int index,boolean[][] used,int i,int j){
        if (board[i][j] != word.charAt(index)){
            return false;
        }else if (index == word.length()-1){
            return true;
        }
        //上下左右
        int[][] run = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};
        used[i][j] = true;
        for (int[] one:run) {
            int i2 = i + one[0],j2 = j + one[1];
            if (i2>=0&&i2<board.length&&j2>=0&&j2<board[0].length){
                if (!used[i2][j2]){
                    boolean flag = exitsBack(word, board, index+1, used, i2, j2);
                    if (flag){
                        return true;
                    }
                }else {
                    continue;
                }
            }
        }
        used[i][j] = false;
        return false;
    }

    /**
     * 给定一个二叉树，返回它的中序 遍历。
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root,result);
        return result;
    }

    public void inorderTraversal(TreeNode root,List<Integer> result){
        if (root == null){
            return;
        }
        inorderTraversal(root.left,result);
        result.add(root.val);
        inorderTraversal(root.right,result);
    }


    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 注意：给定 n 是一个正整数。
     */
    public int climbStairs(int n) {
        if (n <=0) return 0;
        if (n <= 2) return n;
        int a = 1,b=2;
        int p = 0;
        for (int i = 3; i <= n; i++) {
            p = a + b;
            a = b;
            b = p;
        }
        return p;
    }


    public void solveSudoku(char[][] board) {
        // 三个布尔数组 表明 行, 列, 还有 3*3 的方格的数字是否被使用过
        boolean[][] rowUsed = new boolean[9][10];
        boolean[][] colUsed = new boolean[9][10];
        boolean[][][] boxUsed = new boolean[3][3][10];
        // 初始化
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col++) {
                int num = board[row][col] - '0';
                if(1 <= num && num <= 9){
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row/3][col/3][num] = true;
                }
            }
        }
        // 递归尝试填充数组
        recusiveSolveSudoku(board, rowUsed, colUsed, boxUsed, 0, 0);
    }
    private boolean recusiveSolveSudoku(char[][]board, boolean[][]rowUsed, boolean[][]colUsed, boolean[][][]boxUsed, int row, int col){
        // 边界校验, 如果已经填充完成, 返回true, 表示一切结束
        if(col == board[0].length){
            col = 0;
            row++;
            if(row == board.length){
                return true;
            }
        }
        // 是空则尝试填充, 否则跳过继续尝试填充下一个位置
        if(board[row][col] == '.') {
            // 尝试填充1~9
            for(int num = 1; num <= 9; num++){
                boolean canUsed = !(rowUsed[row][num] || colUsed[col][num] || boxUsed[row/3][col/3][num]);
                if(canUsed){
                    rowUsed[row][num] = true;
                    colUsed[col][num] = true;
                    boxUsed[row/3][col/3][num] = true;

                    board[row][col] = (char)('0' + num);
                    if(recusiveSolveSudoku(board, rowUsed, colUsed, boxUsed, row, col + 1)){
                        return true;
                    }
                    board[row][col] = '.';

                    rowUsed[row][num] = false;
                    colUsed[col][num] = false;
                    boxUsed[row/3][col/3][num] = false;
                }
            }
        } else {
            return recusiveSolveSudoku(board, rowUsed, colUsed, boxUsed, row, col + 1);
        }
        return false;
    }


    /**
     *翻转一棵二叉树
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 反转一个单链表。
     * 示例:
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */
    public ListNode reverseList(ListNode head) {
        ListNode listNode = null;
        ListNode front = head;
        while (head!=null){
            front = head;
            head = head.next;
            front.next = listNode;
            listNode = front;
        }
        return front;
    }


    /**
     * 在本问题中，有根树指满足以下条件的有向图。该树只有一个根节点，所有其他节点都是该根节点的后继。每一个节点只有一个父节点，除了根节点没有父节点。
     * 输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
     * 结果图是一个以边组成的二维数组。 每一个边 的元素是一对 [u, v]，用以表示有向图中连接顶点 u 和顶点 v 的边，其中 u 是 v 的一个父节点。
     * 返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int nodesCount = edges.length;
        UnionFind uf = new UnionFind(nodesCount + 1);
        int[] parent = new int[nodesCount + 1];
        for (int i = 1; i <= nodesCount; ++i) {
            parent[i] = i;
        }
        //冲突的边
        int conflict = -1;
        //导致环路的边
        int cycle = -1;
        for (int i = 0; i < nodesCount; ++i) {
            int[] edge = edges[i];
            int node1 = edge[0], node2 = edge[1];
            if (parent[node2] != node2) {
                conflict = i;
            } else {
                parent[node2] = node1;
                if (uf.find(node1) == uf.find(node2)) {
                    cycle = i;
                } else {
                    uf.union(node1, node2);
                }
            }
        }
        if (conflict < 0) {
            int[] redundant = {edges[cycle][0], edges[cycle][1]};
            return redundant;
        } else {
            int[] conflictEdge = edges[conflict];
            if (cycle >= 0) {
                int[] redundant = {parent[conflictEdge[1]], conflictEdge[1]};
                return redundant;
            } else {
                int[] redundant = {conflictEdge[0], conflictEdge[1]};
                return redundant;
            }
        }
    }

    class UnionFind {
        int[] ancestor;

        public UnionFind(int n) {
            ancestor = new int[n];
            for (int i = 0; i < n; ++i) {
                ancestor[i] = i;
            }
        }

        public void union(int index1, int index2) {
            ancestor[find(index1)] = find(index2);
        }

        public int find(int index) {
            if (ancestor[index] != index) {
                ancestor[index] = find(ancestor[index]);
            }
            return ancestor[index];
        }
    }

    /**
     * 解法2
     */
    public int[] findRedundantDirectedConnection2(int[][] edges) {
        int[] tp = new int[2];//构成双父的边 添加的边指向树根则不存在双父
        int[] bh = new int[2];//构成闭环的边 不加节点的情况下添加边必然构成闭环
        int[] parents = new int[edges.length+1];//index的父亲
        for(int i = 0 ;i<edges.length;i++) {
            if(tp[0]==0&&parents[edges[i][1]]!=0) {//判断是否购成双父
                tp[0] = edges[i][0];
                tp[1] = edges[i][1];
                if(bh[0]!=0) break;//闭环和双父都出现则无需遍历剩下的边
            }else {
                parents[edges[i][1]] = edges[i][0] ;
            }
            if( bh[0]==0&&bh(parents,edges[i][1],edges[i][0],tp)) {//判断是否购成闭环
                bh[0] = edges[i][0];
                bh[1] = edges[i][1];
                if(tp[0]!=0) break;//闭环和双父都出现则无需遍历剩下的边
            }
        }
        if(tp[0]==0) return  bh;//不存在双父 则返回导致闭环的边
        //存在双父 则哪边父节点在闭环中 哪边为结果
        if(bh(parents,parents[tp[1]],parents[parents[tp[1]]],tp)){
            tp[0] = parents[tp[1]];
        }
        return tp;
    }
    //点begin是否在闭环中
    boolean bh(int[] parents,int begin,int index,int[] tp) {
        if(parents[index]==0) {
            return false;
        }else if(parents[index]==begin||(index==tp[1]&&begin==tp[0])) {
            return true;
        }else {
            return bh(parents,begin,parents[index],tp)||(index==tp[1]?bh(parents,begin,tp[0],tp):false);
        }
    }

    /**
     * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
     *
     * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     *
     * 你可以假设 nums1 和 nums2 不会同时为空。
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    public static int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }


    /**
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
     * 示例:
     * 输入: [1,1,2]
     * 输出:
     * [
     *   [1,1,2],
     *   [1,2,1],
     *   [2,1,1]
     * ]
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        permuteBack(nums,results,new ArrayList<>(),used);
        return results;
    }

    void permuteBack(int[] nums,List<List<Integer>> results,List<Integer> result,boolean[] used){
        if (result.size() == nums.length){
            results.add(new ArrayList<>(result));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]||(i > 0&&nums[i]==nums[i-1]&&!used[i-1])){
                continue;
            }
            result.add(nums[i]);
            used[i] = true;
            permuteBack(nums,results,result,used);
            used[i] = false;
            result.remove(result.size()-1);
        }
    }

    /**
     * 给定只含 "I"（增大）或 "D"（减小）的字符串 S ，令 N = S.length。
     *
     * 返回 [0, 1, ..., N] 的任意排列 A 使得对于所有 i = 0, ..., N-1，都有：
     *
     * 如果 S[i] == "I"，那么 A[i] < A[i+1]
     * 如果 S[i] == "D"，那么 A[i] > A[i+1]
     */
    public int[] diStringMatch(String S) {
        int[] result = new int[S.length()+1];
        int up = 0,down = S.length();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == 'I'){
                result[i] = up;
                up++;
            }else {
                result[i] = down;
                down--;
            }
        }
        result[S.length()] = up;
        return result;
    }

    /**
     * 计算给定二叉树的所有左叶子之和。
     */
    public int sumOfLeftLeaves(TreeNode root) {
        return  root==null?0:sumOfLeftLeaves(root.left)
                                +sumOfLeftLeaves(root.right)
                                +(root.left!=null&&root.left.left==null&&root.left.right==null?root.left.val:0);
    }

    /**
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * 说明：解集不能包含重复的子集。
     * 解法1采用二进制方法
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> t = new ArrayList<Integer>();
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }
    //解法2递归
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        subsetsBack(0,nums,results,new ArrayList<>());
        return results;
    }
    void subsetsBack(int cur,int[] nums,List<List<Integer>> results,List<Integer> result){
        if (cur == nums.length){
            results.add(new ArrayList<>(result));
            return;
        }
        //选择当前位置
        result.add(nums[cur]);
        subsetsBack(cur+1,nums,results,result);
        //不选择当前位置
        result.remove(result.size()-1);
        subsetsBack(cur+1, nums, results, result);
    }

    /**
     * 给你一个整数数组 arr 和一个整数值 target 。
     *
     * 请你在 arr 中找 两个互不重叠的子数组 且它们的和都等于 target 。可能会有多种方案，请你返回满足要求的两个子数组长度和的 最小值 。
     *
     * 请返回满足要求的最小长度和，如果无法找到这样的两个子数组，请返回 -1 。
     */
    public int minSumOfLengths(int[] arr, int target) {
        int right = 0;
        int left = 0;
        int sum = 0;
        ArrayList<int[]> list = new ArrayList<>();

        // 求出所有符合要求的子数组
        while (right < arr.length) {
            sum += arr[right];
            right++;
            if (sum < target) {
                continue;
            }
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            if (sum == target) {
                list.add(new int[] { right - left, left });
            }
        }

        //结果按长度升序排序
        Collections.sort(list, (o1, o2) -> o1[0] - o2[0]);
//        list.sort(((o1, o2) -> o1[0]-o2[0]));

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {

            //ans是两个长度之和，如果遍历到有超过这个长度的，后面的不是最小值无需遍历。
            int[] a = list.get(i);
            if (a[0] * 2 >= ans) {
                break;
            }
            for (int j = i + 1; j < list.size(); j++) {
                int arr1[] = list.get(i);
                int arr2[] = list.get(j);
                //数组重复情况
                if (arr1[1] < arr2[1] && arr1[0] + arr1[1] > arr2[1]) {
                    continue;
                }
                //数组重复情况
                if (arr2[1] < arr1[1] && arr2[0] + arr2[1] > arr1[1]) {
                    continue;
                }
                ans = Math.min(ans, arr1[0] + arr2[0]);

                //长度经过排序之后，后面的长度比当前大不满足最小，所以舍去不要进行遍历
                break;
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    /**
     * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
     * 例如：
     * 输入: 原始二叉搜索树:
     *               5
     *             /   \
     *            2     13
     *
     * 输出: 转换为累加树:
     *              18
     *             /   \
     *           20     13
     */
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root != null){
             convertBST(root.right);
             sum += root.val;
             root.val = sum;
             convertBST(root.left);
        }
        return root;
    }

    /**
     * 你的初始能量为 P，初始分数为 0，只有一包令牌。
     *
     * 令牌的值为 token[i]，每个令牌最多只能使用一次，可能的两种使用方法如下：
     *
     * 如果你至少有 token[i] 点能量，可以将令牌置为正面朝上，失去 token[i] 点能量，并得到 1 分。
     * 如果我们至少有 1 分，可以将令牌置为反面朝上，获得 token[i] 点能量，并失去 1 分。
     * 在使用任意数量的令牌后，返回我们可以得到的最大分数。
     *
     * 贪心算法  只要还有能量，就一直让令牌正面朝上，直到没有能量的时候，让一个令牌反面朝上从而获得能量继续之前的操作。
     *          最终答案一定是在一次让令牌正常朝上操作之后产生的（永远不可能在让令牌反面朝上操作之后产生）
     */
    public int bagOfTokensScore(int[] tokens, int P) {
        Arrays.sort(tokens);
        int i = 0,j = tokens.length-1,score = 0;
        while (i <= j){
            //优先换积分
            if (P >= tokens[i]){
                P -= tokens[i];
                score++;
                i++;
            }else if (score > 0 && i < j ){
                //有得分且可以兑换能量来用，此兑换不是最后一个
                P += tokens[j];
                j--;
                score--;
            }else{
                //加能量确保不是最后一个令牌，最后一个要么得分，要么不用
                break;
            }
        }
        return score;
    }


    /**
     * 给定一个二叉树，我们在树的节点上安装摄像头。
     *
     * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
     *
     * 计算监控树的所有节点所需的最小摄像头数量。
     */
    public int minCameraCover(TreeNode root) {
        if (dfs(root)==1){
            sum++;
        }
        return sum;
    }

    /**
     * 0 没装摄像机但被观测到，上一层是1
     * 1 没装摄像机也没被观测到，需要安装摄像机 在父节点安装优于在子节点安装 即上一层需为2
     * 2 装了摄像机 上一层是0
     */
    int dfs(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = dfs(root.left),right = dfs(root.right);
        if (left == 1 || right == 1){
            sum++;
            return 2;
        }else if (left == 0||right == 0){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
     *
     * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null){
            return t2;
        }
        if (t2 == null){
            return t1;
        }
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    /**
     * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode cur = head;
        ListNode pre = cur;
        Set<Integer> set = new HashSet<>();
        while (cur!=null){
            if (set.contains(cur.val)){
                pre.next = cur.next;
            }else{
                set.add(cur.val);
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
     * 假定 BST 有如下定义：
     * 结点左子树中所含结点的值小于等于当前结点的值
     * 结点右子树中所含结点的值大于等于当前结点的值
     * 左子树和右子树都是二叉搜索树
     * 例如：
     * 给定 BST [1,null,2,2],
     *    1
     *     \
     *      2
     *     /
     *    2             返回[2].
     */
    List<Integer> mList = new ArrayList<>();
    int curent = 0;//表示当前节点的值
    int count = 0;//表示当前节点的数量
    int maxCount = 0;//最大的重复数量

    public int[] findMode(TreeNode root) {
        inOrderTraversal(root);
        int[] res = new int[mList.size()];
        for (int i = 0; i < mList.size(); i++) {
            res[i] = mList.get(i);
        }
        return res;
    }
    void inOrderTraversal(TreeNode treeNode){
        if (treeNode == null) return;
        inOrderTraversal(treeNode.left);
        int nodeVal = treeNode.val;
        if (nodeVal == curent){
            count++;
        }else{
            curent = nodeVal;
            count  = 1;
        }
        if (count == maxCount){
            mList.add(nodeVal);
        }else if (count > maxCount){
            mList.clear();
            mList.add(nodeVal);
            maxCount = count;
        }
        inOrderTraversal(treeNode.right);
    }

    /**
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     * 注意: 你可以假设树中没有重复的元素。
     * 例如，给出
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     * 返回如下的二叉树：
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     */
    Map<Integer,Integer> treeMap = new HashMap<>();
    int[] inorder,postorder;
    int postIndex;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postIndex = inorder.length-1;
        this.inorder = inorder;
        this.postorder = postorder;
        int idx = 0;
        for (Integer i:inorder) {
            treeMap.put(i,idx++);
        }
        return helper(0,postIndex);
    }
    TreeNode helper(int left,int right){
        if (left > right){  //如果这里没有结点构造二叉树
            return null;
        }
        int rootVal = postorder[postIndex];
        TreeNode root = new TreeNode(rootVal); //后序遍历的最后一个节点作为根节点
        int index = treeMap.get(rootVal);  //根据root的中序遍历所在位置分为左右子树
        postIndex--;
        root.right = helper(index+1,right);  //构建右子树
        root.left = helper(left,index-1);  //构建左子树
        return root;
    }

    /**
     * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
     * 说明: 叶子节点是指没有子节点的节点。
     * 示例:给定如下二叉树，以及目标和 sum = 22，
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \    / \
     *         7    2  5   1
     * 返回:
     * [
     *    [5,4,11,2],
     *    [5,8,4,5]
     * ]
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> results = new ArrayList<>();
        pathBack(root,sum,new ArrayList<>(),results);
        return results;
    }
    void pathBack(TreeNode treeNode,int sum, List<Integer> res, List<List<Integer>> results){
        if (treeNode == null) return;
        res.add(treeNode.val);
        sum -= treeNode.val;
        if (treeNode.left == null && treeNode.right == null && sum == 0){
            results.add(new ArrayList<>(res));
        }
        pathBack(treeNode.left,sum,res,results);
        pathBack(treeNode.right,sum,res,results);
        res.remove(res.size()-1);
    }

    /**
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val>Math.max(p.val,q.val)){
            return lowestCommonAncestor(root.left, p, q);
        }else if (root.val<Math.min(p.val,q.val)){
            return lowestCommonAncestor(root.right, p, q);
        }else {
            return root;
        }
    }

    /**
     * 给定一个二叉树
     * struct Node {
     *   int val;
     *   Node *left;
     *   Node *right;
     *   Node *next;
     * }
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。初始状态下，所有 next 指针都被设置为 NULL。
     */
    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            Node cur = null;
            for (int i = 1; i <= size ; i++) {
                Node node = queue.poll();
                if (node.left!=null) queue.offer(node.left);
                if (node.right!=null) queue.offer(node.right);
                if (i!=1) cur.next = node;
                cur = node;
            }
        }
        return root;
    }

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}

    /**
     * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
     * B.length >= 3
     * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
     * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
     * 给出一个整数数组 A，返回最长 “山脉” 的长度。如果不含有 “山脉” 则返回 0
     */
    public int longestMountain(int[] A) {
        int n = A.length;
        int ans =0,start = 0;
        while (start<n){
            int end = start;
            if (end +1 < n && A[end] < A[end+1]){
                while (end +1 < n && A[end] < A[end+1]) end++;

                if (end +1 < n && A[end] > A[end+1]){
                    while (end +1 < n && A[end] > A[end+1]) end++;
                    ans = Math.max(ans,end-start+1);
                }
            }
            start = Math.max(end,start+1);
        }
        return ans;
    }

    /**
     * 给定一个二叉树，返回它的 后序 遍历。
     */
    List<Integer> ans = new LinkedList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return null;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        ans.add(root.val);
        return ans;
    }

    /**
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     * 你可以认为每种硬币的数量是无限的。
     * 动态规划：自下而上
     *  F(i)对应的转移方程应为F(i)=minF(i−c_j)+1
     *其中 c_j代表的是第 j 枚硬币的面值，即我们枚举最后一枚硬币面额是 c_j，那么需要从 i-c_j这个金额的状态 F(i-c_j)F(i−c_j) 转移过来，再算上枚举的这枚硬币数量 11 的贡献，由于要硬币数量最少，所以 F(i)为前面能转移过来的状态的最小值加上枚举的硬币数量 11 。
     * 假设我们知道 F(S)F(S) ，即组成金额 SS 最少的硬币数，最后一枚硬币的面值是 CC。那么由于问题的最优子结构，转移方程应为：F(S) = F(S - C) + 1
     */
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount+1];
        Arrays.fill(dp,max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i){
                    dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
                }
            }
        }
        return dp[amount]==max?-1:dp[amount];
    }

    /**
     * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
     * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null){
            TreeNode treeNode = new TreeNode(val);
            return treeNode;
        }
        if (root.val>val){
            root.left = insertIntoBST(root.left, val);
        } else{
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null){
            return new TreeNode(val);
        }
        TreeNode pre = root;
        while (pre!=null){
            if (pre.val>val){
                if (pre.left==null){
                    pre.left = new TreeNode(val);
                    break;
                }else{
                    pre = pre.left;
                }
            }else{
                if (pre.right==null){
                    pre.right = new TreeNode(val);
                    break;
                }else{
                    pre = pre.right;
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Solution solution09 = new Solution();
        solution09.coinChange(new int[]{1,2,5},11);
    }


}
