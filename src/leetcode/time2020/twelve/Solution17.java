package leetcode.time2020.twelve;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 17 电话号码的字母组合
 *
 *  给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2020/12/14 21:52
 */
public class Solution17 {


    char[][] letter = new char[][]{{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits.length() == 0)  return res;
        back(res,0,digits,new StringBuilder());
        return res;
    }
    private void back(List<String> list,int index,String digits,StringBuilder sb){
        if (index == digits.length()){
            list.add(sb.toString());
        }else{
            char c = digits.charAt(index);
            char[] chars = letter['c'-2];
            int length = chars.length;
            for (int i = 0; i < length; i++) {
                sb.append(chars[i]);
                back(list, index + 1, digits, sb);
                sb.deleteCharAt(index);
            }
        }
    }

    /**
     * 队列实现
     * @author lyx
     * @date 2020/12/14 22:16
     * @return
     */
    public List<String> letterCombinations2(String digits) {
        List<String> res = new ArrayList<>();
        Deque<String> deque = new ArrayDeque<>();
        for (char c:letter[digits.charAt(0)-'2']) {
            deque.addFirst(String.valueOf(c));
        }
        for (int i = 1; i < digits.length(); i++) {
            int index = digits.charAt(i) - '2';
            char[] chars = letter[index];
            int curSize = deque.size();
            for (int j = 0; j < curSize; j++) {
                for (char c:chars) {
                    String str = deque.peekFirst() + c;
                    deque.addLast(str);
                }
                deque.pollFirst();
            }
        }
        while (!deque.isEmpty()){
            res.add(deque.pollFirst());
        }
        return res;
    }



}
