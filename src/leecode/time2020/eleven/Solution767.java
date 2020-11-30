package leecode.time2020.eleven;

import java.util.PriorityQueue;

/**
 *
 * 767 重构字符串
 *
 *
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 *
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 *
 * 示例 1:
 *
 * 输入: S = "aab"
 * 输出: "aba"
 * 示例 2:
 *
 * 输入: S = "aaab"
 * 输出: ""
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorganize-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/30 11:48
 */
public class Solution767 {

    /**
     * 基于计数的贪心算法
     * 当 n 是奇数且出现最多的字母的出现次数是 (n+1)/2 时，出现次数最多的字母必须全部放置在偶数下标，否则一定会出现相邻的字母相同的情况。
     * 其余情况下，每个字母放置在偶数下标或者奇数下标都是可行的。
     *
     * @param S
     * @return
     */
    public String reorganizeString(String S) {
        int[] count = new int[26];
        int maxCount = 0;
        int n = S.length();
        for (Character c:S.toCharArray()) {
            count[c-'a']++;
            maxCount = Math.max(maxCount,count[c-'a']);
        }
        if (maxCount > (n + 1) / 2) return "";
        char[] res = new char[n];
        int evenIndex = 0,oddIndex = 1,half = n / 2;
        for (int i = 0; i < 26; i++) {
            char c = (char) (i + 'a');
            while (count[i] > 0 && count[i] <= half && evenIndex < n){
                res[evenIndex] = c;
                count[i]--;
                evenIndex += 2;
            }
            //当 n 是奇数且出现最多的字母的出现次数是 (n+1)/2 时,会跳过上面的来到这一层，保证一定不会重复
            while (count[i] > 0){
                res[oddIndex] = c;
                count[i]--;
                oddIndex += 2;
            }
        }
        return new String(res);
    }

    /**
     * 基于最大堆的贪心算法
     * @param S
     * @return
     */
    public String reorganizeString2(String S) {
        if (S.length() < 2) return S;
        int n = S.length();
        int[] count = new int[26];
        int maxCount = 0;
        for (Character c:S.toCharArray()) {
            count[c-'a']++;
            maxCount = Math.max(maxCount,count[c-'a']);
        }
        if (maxCount > (n + 1) / 2) return "";
        PriorityQueue<Character> queue = new PriorityQueue<>(((o1, o2) -> {
            return count[o2-'a'] - count[o1-'a'];
        }));
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0)   queue.offer((char) (i+'a'));
        }
        StringBuilder str = new StringBuilder();
        while (queue.size() > 1){
            char o1 = queue.poll();
            char o2 = queue.poll();
            str.append(o1);
            str.append(o2);
            int index1 = o1 - 'a',index2 = o2 - 'a';
            count[index1]--;
            count[index2]--;
            if (count[index1] > 0){
                queue.offer(o1);
            }
            if (count[index2] > 0){
                queue.offer(o2);
            }
        }
        if (queue.size() > 0){
            str.append(queue.poll());
        }
        return str.toString();
    }

}
