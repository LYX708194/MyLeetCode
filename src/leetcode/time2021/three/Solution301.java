package leetcode.time2021.three;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** 删除无效的括号
 * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
 *
 * 返回所有可能的结果。答案可以按 任意顺序 返回。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: "()())()"
 * 输出: ["()()()", "(())()"]
 * 示例 2:
 *
 * 输入: "(a)())()"
 * 输出: ["(a)()()", "(a())()"]
 * 示例 3:
 *
 * 输入: ")("
 * 输出: [""]
 *  
 *
 * 示例 1：
 *
 * 输入：s = "()())()"
 * 输出：["(())()","()()()"]
 * 示例 2：
 *
 * 输入：s = "(a)())()"
 * 输出：["(a())()","(a)()()"]
 * 示例 3：
 *
 * 输入：s = ")("
 * 输出：[""]
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 25
 * s 由小写英文字母以及括号 '(' 和 ')' 组成
 * s 中至多含 20 个括号
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-invalid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/29 11:20
 */
public class Solution301 {

    private int len;
    char[] charArray;
    private Set<String> set = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        this.len = s.length();
        this.charArray = s.toCharArray();
        int left = 0,right = 0;
        for (char c:s.toCharArray()) {
            if (c == '('){
                left++;
            }else if (c == ')'){
                if (left > 0){
                    left--;
                }else{
                    right++;
                }
            }
        }
        StringBuilder path = new StringBuilder();
        dfs(0,0,0,left,right,path);
        return new ArrayList<>(set);
    }

    /**
     * @param index 当前遍历下标
     * @param leftCount 已经遍历左括号个数
     * @param rightCount 已经遍历右括号个数
     * @param leftRemove 最少应该删除左括号个数
     * @param rightRemove 最少应该删除右括号个数
     * @param path 一个可能的结果
     */
    private void dfs(int index,int leftCount,int rightCount,int leftRemove,int rightRemove,StringBuilder path){
        if (index == len){
            if (leftRemove == 0 && rightRemove == 0){
                set.add(path.toString());
            }
            return;
        }
        char c = charArray[index];
        //当前为左括号且需要删除左括号，则删除当前左括号
        if (c == '(' && leftRemove > 0){
            dfs(index + 1, leftCount, rightCount, leftRemove - 1, rightRemove, path);
        }
        //删除当前右括号
        if (c == ')' && rightRemove > 0){
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove - 1, path);
        }
        //保留当前字符
        path.append(c);
        if (c != '(' && c != ')'){
            //不是左括号也不是右括号
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove, path);
        }else if (c == '('){
            //考虑左括号，个数加1
            dfs(index + 1, leftCount + 1, rightCount, leftRemove, rightRemove, path);
        }else if (rightCount < leftCount){
            //当已选择左括号个数大于右括号个数时，考虑右括号，个数加1
            dfs(index + 1, leftCount, rightCount + 1, leftRemove, rightRemove, path);
        }
        path.deleteCharAt(path.length() - 1);
    }


}
