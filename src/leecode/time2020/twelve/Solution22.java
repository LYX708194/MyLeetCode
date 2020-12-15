package leecode.time2020.twelve;

import java.util.ArrayList;
import java.util.List;

/**
 * 22 括号生成
 *
 *数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/12/15 10:55
 */
public class Solution22 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        back(res,0,0,n,new StringBuilder());
        return res;
    }
    void back(List<String> res,int leftNum,int rightNum,int n,StringBuilder sb){
        int length = n * 2;
        if (sb.length() == length){
            res.add(sb.toString());
            return;
        }
        if (leftNum < n){
            sb.append('(');
            back(res, leftNum + 1, rightNum, n, sb);
            sb.deleteCharAt(sb.length()-1);
        }
        if (rightNum < leftNum){
            sb.append(')');
            back(res, leftNum, rightNum + 1, n, sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        dfs("",n,n,res);
        return res;
    }
    private void dfs(String cur,int left,int right,List<String> res){
        if (left == 0 && right == 0){
            //字符串的特殊性，可以直接添加
            res.add(cur);
        }
        //剪枝，左括号必然要比右括号先添加
        if (left > right){
            return;
        }
        if (left > 0){
            dfs(cur + '(', left - 1, right, res);
        }
        if (right > 0){
            dfs(cur + ')', left, right - 1, res);
        }
    }



    public static void main(String[] args) {
        Solution22 solution22 = new Solution22();
        solution22.generateParenthesis(3);
    }

}
