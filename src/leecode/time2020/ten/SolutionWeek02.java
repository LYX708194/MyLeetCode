package leecode.time2020.ten;

import leecode.entity.ListNode;
import leecode.entity.Node;
import leecode.entity.TreeNode;

import java.util.*;

/**
 * @author lyx
 * @date 2020/10/12 19:57
 */
public class SolutionWeek02 {




    /**
     * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
     * java是值传递，变量作为函数参数不能被改变值
     */
    int pre,ans;
    public int getMinimumDifference(TreeNode root) {
        ans = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);
        return ans;
    }
    void dfs(TreeNode treeNode){
        if (treeNode == null)  return;
        dfs(treeNode.left);
        if (pre == -1){
            pre = treeNode.val;
        }else{
            ans = Math.min(ans,treeNode.val-pre);
            pre = treeNode.val;
        }
        dfs(treeNode.right);
    }



    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * 示例:给定 1->2->3->4, 你应该返回 2->1->4->3.
     */
    public ListNode swapPairs(ListNode head) {
        if(head == null) return head;
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        if(head.next!=null){
            next = head.next;
            head = next;
        }
        while(cur!=null&&cur.next!=null){
            cur.next = next.next;
            next.next = cur;
            if (pre!=null) pre.next = next;
            if(cur.next == null) break;
            pre = cur;
            cur = pre.next;
            next = cur.next;
        }
        return head;
    }


    /**
     * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
     * 例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
     * 你可以按任意顺序返回答案。
     * 示例 1：
     * 输入：["bella","label","roller"]
     * 输出：["e","l","l"]
     * 示例 2：
     * 输入：["cool","lock","cook"]
     * 输出：["c","o"]
     */
    public List<String> commonChars(String[] A) {
        //最终每个字母出现的次数
        int[] minCount = new int[26];
        Arrays.fill(minCount,Integer.MAX_VALUE);
        for (String word:A) {
            //单个单词每个字母出现的次数
            int[] count = new int[26];
            for (int i = 0; i < word.length(); i++) {
                count[word.charAt(i)-'a']++;
            }
            for (int i = 0; i < 26; i++) {
                //覆盖，取两者间最小值
                minCount[i] = Math.min(minCount[i],count[i]);
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < minCount[i]; j++) {
                ans.add(String.valueOf((char) (i+'a')));
            }
        }
        return ans;
    }

    /**
     * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     * 初始状态下，所有 next 指针都被设置为 NULL。
     */
    public Node connect(Node root) {
        if(root == null) return null;
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            Node left = null;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                //完美二叉树，所以只要判断一个就行
                if(node.left!=null){
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
                if (left!=null){
                    left.next = node;
                }
                left = node;
            }
        }
        return root;
    }
    //递归解法，太妙了
    public Node connect2(Node root) {
        dfs(root, null);
        return root;
    }

    private void dfs(Node curr, Node next) {
        if (curr == null)
            return;
        curr.next = next;
        dfs(curr.left, curr.right);
        dfs(curr.right, curr.next == null ? null : curr.next.left);
    }

    /**
     * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
     */
    public int[] sortedSquares(int[] A) {
        //双指针解法
        int n = A.length;
        int[] ans = new int[n];
        for (int i = 0,j = n-1; i <= j ; ) {
            if(A[i]*A[i]>=A[j]*A[j]){
                ans[--n] = A[i]*A[i];
                i++;
            }else{
                ans[--n] = A[j]*A[j];
                j--;
            }
        }
        return ans;
//         for(int i=0;i<A.length;i++){
//             A[i] = A[i]*A[i];
//         }
//         Arrays.sort(A);
//        return A;
    }


    /**
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
     * 输入: 4
     * 输出: 2
     * 解释: 4 皇后问题存在如下两个不同的解法。
     * [
     *  [".Q..",  // 解法 1
     *   "...Q",
     *   "Q...",
     *   "..Q."],
     *  ["..Q.",  // 解法 2
     *   "Q...",
     *   "...Q",
     *   ".Q.."]
     * ]
     */
//    int ans = 0;
    public int totalNQueens(int n) {
        if (n<=0) return 0;
        boolean[][] isQueen = new boolean[n][n];
        NQueensBack(isQueen,0);
        return ans;
    }
    void NQueensBack(boolean[][] isQueen,int row){
        if (row == isQueen.length){
            ans++;
            return;
        }
        //对该行上每一点进行尝试，可以就放入N皇后进行回溯
        for (int col = 0; col < isQueen.length; col++) {
            if (valid(isQueen,row,col)){
                isQueen[row][col] = true;
                NQueensBack(isQueen, row+1);
                isQueen[row][col] = false;
            }
        }
    }
    boolean valid(boolean[][] isQueen,int row,int col){
        //当前列有没有皇后
        for (int i = 0; i < row; i++) {
            if (isQueen[i][col])    return false;
        }
        //坐对角线有没有皇后
        for (int i = row-1,j = col-1; i >=0 && j >= 0 ; i--,j--) {
            if (isQueen[i][j])  return false;
        }
        //右对角线有没有皇后
        for (int i = row-1,j = col+1; i >=0 && j <isQueen.length ; i--,j++) {
            if (isQueen[i][j])  return false;
        }
        return true;
    }

    /**
     * 列的表示法很直观，一共有 NN 列，每一列的下标范围从 00 到 N-1N−1，使用列的下标即可明确表示每一列。
     * 方向一的斜线为从左上到右下方向，同一条斜线上的每个位置满足行下标与列下标之差相等，例如 (0,0)(0,0) 和 (3,3)(3,3) 在同一条方向一的斜线上。
     * 因此使用行下标与列下标之差即可明确表示每一条方向一的斜线。
     *方向二的斜线为从右上到左下方向，同一条斜线上的每个位置满足行下标与列下标之和相等，例如 (3,0)(3,0) 和 (1,2)(1,2) 在同一条方向二的斜线上。
     * 因此使用行下标与列下标之和即可明确表示每一条方向二的斜线。
     */
    public int totalNQueens2(int n) {
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> diagonals1 = new HashSet<Integer>();
        Set<Integer> diagonals2 = new HashSet<Integer>();
        return backtrack(n, 0, columns, diagonals1, diagonals2);
    }

    public int backtrack(int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            return 1;
        } else {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                count += backtrack(n, row + 1, columns, diagonals1, diagonals2);
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
            return count;
        }
    }

    /**
     * 给你一个字符串 s，请你返回 两个相同字符之间的最长子字符串的长度 ，计算长度时不含这两个字符。如果不存在这样的子字符串，返回 -1 。
     * 子字符串 是字符串中的一个连续字符序列。
     */
    public int maxLengthBetweenEqualCharacters(String s) {
        int n = s.length();
        int ans = -1;
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(s.charAt(i))){
                ans =  Math.max(ans,i-map.get(s.charAt(i)));
            }else{
                map.put(s.charAt(i),i);
            }
        }
        return ans;
    }

    int max = 0;
    public int bestTeamScore(int[] scores, int[] ages) {
        scoreBack(0,0,scores,ages,new LinkedList<>());
        return max;
    }
    void  scoreBack(int index,int curScope,int[] scores,int[] ages,List<Integer> used){
        if (index == scores.length){
            max = Math.max(curScope,max);
            return;
        }
        for (int i = index; i < ages.length; i++) {
            boolean flag = true;
            for (int j = 0; j < used.size(); j++) {
                if (scores[i] > scores[used.get(j)] && ages[i] < ages[used.get(j)]){
                    flag = false;
                    break;
                }
            }
            if (flag){
                used.add(i);
                curScope += scores[i];
            }
            scoreBack(i+1,curScope,scores,ages,used);
            if (flag){
                used.remove(used.size()-1);
            }
//            scoreBack(i+1,curScope,scores,ages,used);
        }
    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dump = new ListNode(0,head);
        dump.next = head;
        ListNode p = dump;
        ListNode q = head;
        //先获得两个结点之间距离
        while(n>0){
            q = q.next;
            n--;
        }
        while (q!=null){
            p = p.next;
            q = q.next;
        }
        p.next = p.next.next;
        return dump.next;
    }



    public static void main(String[] args) {
        SolutionWeek02 solutionWeek02 = new SolutionWeek02();
        solutionWeek02.bestTeamScore(new int[]{1,2,3,5},new int[]{8,9,10,1});
    }


}
