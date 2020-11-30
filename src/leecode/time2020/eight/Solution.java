package leecode.time2020.eight;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author lyx
 * @date 2020/8/29 21:42
 */
public class Solution {



    /**
     * 最短回文串，给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串
     */
    public static String shortestPalindrome(String s) {
        //获得最差结果的字符串
        String ss = s + new StringBuffer(s).reverse().toString();
        for (int i=s.length(),j=i; i>0; i--,j++) {
            //比较中间是否有多余的
            if (ss.substring(0, i).equals(ss.substring(j))) {
                return new StringBuilder(s.substring(i)).reverse().append(s).toString();
            }
        }
        return "";
    }


    /**
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     */
    public String addStrings(String num1, String num2) {
        int i = num1.length()-1,j = num2.length()-1,add = 0;
        StringBuffer s = new StringBuffer();
        while(i>=0||j >=0||add!=0){
            int numx = i>=0?num1.charAt(i) - '0':0;
            int numy = j>=0?num2.charAt(j) - '0':0;
            int result = numx+numy + add;
            s.append(result%10);  //将个位数加入字符串
            add = result/10;  //获得进位
            i--;
            j--;
        }
        //结果翻转
        s.reverse();
        return s.toString();
    }

    /**
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] ss = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for (String word:ss) {
            sb.append(new StringBuffer(word).reverse().append(" "));
        }
        return sb.delete(s.length(),s.length()+1).toString();
    }

    /**
     * 有向图广度优先
     * @param rooms
     * @return
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size(), num = 0;
        boolean[] vis = new boolean[n];
        Queue<Integer> que = new LinkedList<Integer>();
        vis[0] = true;
        que.offer(0);
        while (!que.isEmpty()) {
            int x = que.poll();
            num++;
            for (int it : rooms.get(x)) {
                if (!vis[it]) {
                    vis[it] = true;
                    que.offer(it);
                }
            }
        }
        return num == n;
    }


}
