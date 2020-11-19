package leecode.time2020.ten;

import leecode.time2020.entity.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author lyx
 * @date 2020/10/19 19:11
 */
public class SolutionWeek03 {

    /**
     * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
     * 注意：如果对空文本输入退格字符，文本继续为空。
     */
    public boolean backspaceCompare(String S, String T) {
        String s1 = getLastStr(S);
        String s2 = getLastStr(T);
        return s1.equals(s2);
    }
    String getLastStr(String s){
        StringBuilder s1 = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '#'){
                if (s1.length() == 0)   continue;
                s1.deleteCharAt(s1.length()-1);
            }else{
                s1.append(s.charAt(i));
            }
        }
        return s1.toString();
    }

    /**
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     */
    public void reorderList(ListNode head) {
        if (head == null)   return;
        Stack<ListNode> stack = new Stack<>();
        ListNode node  = head;
        while (node!=null){
            stack.push(node);
            node = node.next;
        }
        int size = stack.size()/2+1;
        node = head;
        ListNode out = null;
        while(stack.size()>=size){
            out = stack.pop();
            node.next = out;
            out.next = node.next;
            node = out.next;
        }
        node.next = null;
    }
    public void reorderList2(ListNode head) {
        if (head == null)   return;
        List<ListNode> listNodes = new ArrayList<>();
        ListNode node  = head;
        while (node!=null){
            listNodes.add(node);
            node = node.next;
        }
        int i = 0,j = listNodes.size()-1;
        while(i<j){
            listNodes.get(i).next = listNodes.get(j);
            i++;
            listNodes.get(j).next = listNodes.get(i);
            j--;
        }
        listNodes.get(i).next = null;
    }

    /**
     * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
     * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
     */
    public boolean isLongPressedName(String name, String typed) {
        int i = 0,j = 0;
        while(j<typed.length()){
            if(i<name.length()&&name.charAt(i) == typed.charAt(j)){
                i++;
                j++;
            }else if(i>0&&name.charAt(i-1) == typed.charAt(j)){
                j++;
            }else{
                return false;
            }
        }
        return i == name.length();
    }

    /**
     * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。
     */
    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        int length = S.length();
        for (int i = 0; i < length; i++) {
            last[S.charAt(i)-'a'] = i;
        }
        List<Integer> ans = new ArrayList<>();
        int begin = 0,end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end,last[S.charAt(i)-'a']);
            if (i == end){
                ans.add(end - begin + 1);
                begin = end + 1;
            }
        }
        return ans;
    }

    /**
     * 请判断一个链表是否为回文链表。
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null) return true;
        List<Integer> nodes = new ArrayList<>();
        ListNode node = head;
        while(node != null){
            nodes.add(node.val);
            node = node.next;
        }
        int i = 0,j = nodes.size()-1;
        while(i<j){
            if(!nodes.get(i++).equals(nodes.get(j--))){
                return false;
            }
        }
        return true;
    }

    /**
     * 你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
     * 视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
     * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
     */
    public int videoStitching(int[][] clips, int T) {
        // 用于保存 以当前数字(下标)为起点 的区间的 最大的结束位置
        int[] maxEnd = new int[T];
        for (int[] clip:clips) {
            if (clip[0]<T){
                maxEnd[clip[0]] = Math.max(maxEnd[clip[0]],clip[1]);
            }
        }
        int pre = 0,last = 0,ans = 0;
        for (int i = 0; i < T; i++) {
            last = Math.max(maxEnd[i],last);
            // 当前元素 == 本区间最大元素(无法到达后续位置)
            if (i == last){
                return -1;
            }
            // 当前元素 == 上一个区间的最大元素
            if (i == pre){
                ans++;
                pre = last;
            }
        }
        return ans;
    }

    /**
     * 我们令dp[i]表示将区间[0,i)覆盖所需的最少子区间的数量。由于我们希望子区间的数目尽可能少，因此可以将所有dp[i]的初始值设为一个大整数，并将dp[0]（即空区间）的初始值设为0。
     *
     * 我们可以枚举所有的子区间来依次计算出所有的 dp 值。我们首先枚举 i，同时对于任意一个子区间 [a_j,b_j)，若其满足 a_j < i < b_j，
     * 那么它就可以覆盖区间 [0,i) 的后半部分，而前半部分则可以用 dp[a_j] 对应的最优方法进行覆盖，因此我们可以用 dp[a_j] + 1来更新 dp[i]。状态转移方程如下：
     *dp[i]=min{dp[a_j]}+1,(a_j<i<b_j)
     * 最终的答案即为dp[T]。
     */
    public int videoStitching2(int[][] clips, int T) {
        int[] dp = new int[T+1];
        Arrays.fill(dp,Integer.MAX_VALUE-1);
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            for (int[] clip:clips) {
                if (clip[0] <i && i<=clip[1]){
                    dp[i] = Math.min(dp[i],dp[clip[0]]+1);
                }
            }
        }
        return dp[T] == Integer.MAX_VALUE-1? -1:dp[T];
    }

    /**
     * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：B.length >= 3
     * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
     * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
     * 给出一个整数数组 A，返回最长 “山脉” 的长度。
     * 如果不含有 “山脉” 则返回 0。
     * 动态规划
     */
    public int longestMountain(int[] A) {
        int n = A.length;
        if (n == 0) {
            return 0;
        }
        int[] left = new int[n];
        for (int i = 1; i < n; ++i) {
            left[i] = A[i - 1] < A[i] ? left[i - 1] + 1 : 0;
        }
        int[] right = new int[n];
        for (int i = n - 2; i >= 0; --i) {
            right[i] = A[i + 1] < A[i] ? right[i + 1] + 1 : 0;
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (left[i] > 0 && right[i] > 0) {
                ans = Math.max(ans, left[i] + right[i] + 1);
            }
        }
        return ans;
    }

    /**
     * 给定两个整数 L 和 R ，找到闭区间 [L, R] 范围内，计算置位位数为质数的整数个数。
     * （注意，计算置位代表二进制表示中1的个数。例如 21 的二进制表示 10101 有 3 个计算置位。还有，1 不是质数。）
     */
    public int countPrimeSetBits(int L, int R) {
        int ans = 0;
        for (int i = L; i <= R; i++) {
            if (isSmallPrime(Integer.bitCount(i)))  ans++;
        }
        return ans;
    }
    public boolean isSmallPrime(int x) {
        return (x == 2 || x == 3 || x == 5 || x == 7 ||
                x == 11 || x == 13 || x == 17 || x == 19);
    }

    /**
     * 给你一个长度为 n 的字符串 keysPressed ，其中 keysPressed[i] 表示测试序列中第 i 个被按下的键。releaseTimes 是一个升序排列的列表，其中 releaseTimes[i] 表示松开第 i 个键的时间。字符串和数组的 下标都从 0 开始 。第 0 个键在时间为 0 时被按下，接下来每个键都 恰好 在前一个键松开时被按下。
     * 测试人员想要找出按键 持续时间最长 的键。第 i 次按键的持续时间为 releaseTimes[i] - releaseTimes[i - 1] ，第 0 次按键的持续时间为 releaseTimes[0] 。
     * 注意，测试期间，同一个键可以在不同时刻被多次按下，而每次的持续时间都可能不同。
     * 请返回按键 持续时间最长 的键，如果有多个这样的键，则返回 按字母顺序排列最大 的那个键。
     */
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        char ans = keysPressed.charAt(0);
        int longest = releaseTimes[0];
        for(int i = 1; i < releaseTimes.length ; i++ ){
            if(releaseTimes[i]-releaseTimes[i-1] > longest ){
                longest = releaseTimes[i]-releaseTimes[i-1];
                ans = keysPressed.charAt(i);
            }else if (releaseTimes[i]-releaseTimes[i-1] == longest){
                ans = keysPressed.charAt(i)-ans>0?keysPressed.charAt(i):ans;
            }
        }
        return ans;
    }

    /**
     * 检查子序列是否是等差数列
     */
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            int[] num = new int[r[i]-l[i]+1];
            for (int j = l[i]; j <= r[i]; j++) {
                num[j-l[i]] = nums[j];
            }
            ans.add(new Boolean(isArithmetic(num)));
        }
        return ans;
    }
    boolean isArithmetic(int[] nums){
        Arrays.sort(nums);
        for (int i = 1; i < nums.length-1; i++) {
            if (nums[i]-nums[i-1] == nums[i+1]-nums[i]){
                continue;
            }else{
                return false;
            }
        }
        return true;
    }

    /**
     * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
     * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
     * 请你返回从左上角走到右下角的最小 体力消耗值 。
     */
    public int minimumEffortPath(int[][] heights) {
        int max = 0;
        int[][] dp = new int[heights.length][heights[0].length];
        int[][] dps = new int[heights.length][heights[0].length];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (i>0) dp[i][j] = Math.min(dp[i][j],Math.abs(heights[i][j]-heights[i-1][j]));
                if (j>0) dp[i][j] = Math.min(dp[i][j],Math.abs(heights[i][j]-heights[i][j-1]));
                if (i<heights.length-1) dp[i][j] = Math.min(dp[i][j],Math.abs(heights[i][j]-heights[i+1][j]));
                if (j<heights[0].length-1) dp[i][j] = Math.min(dp[i][j],Math.abs(heights[i][j]-heights[i][j+1]));
                dps[i][j] = dp[i][j];
            }
        }
        dps[0][0] = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (i == 0 && j == 0) continue;
                dps[i][j] = Math.min(dps[i][j],dp[i][j]);
                max = dp[i][j] = Math.max(dps[i][j],dp[i][j]);
            }
        }
        return heights.length == 1?max:dps[heights.length-1][heights[0].length-1];
    }

    public static void main(String[] args) {
        SolutionWeek03 solutionWeek03 = new SolutionWeek03();

        solutionWeek03.minimumEffortPath(new int[][]{{1,2,2},{3,8,2},{5,3,5}});
    }
}
