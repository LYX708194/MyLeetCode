package leetcode.time2021.three;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 331 验证二叉树的前序序列化
 * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 *
 *      _9_
 *     /   \
 *    3     2
 *   / \   / \
 *  4   1  #  6
 * / \ / \   / \
 * # # # #   # #
 * 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
 *
 * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
 *
 * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
 *
 * 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
 *
 * 示例 1:
 *
 * 输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * 输出: true
 * 示例 2:
 *
 * 输入: "1,#"
 * 输出: false
 * 示例 3:
 *
 * 输入: "9,#,#,1"
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/12 12:18
 */
public class Solution331 {

    public boolean isValidSerialization(String preorder) {
        int n = preorder.length();
        //记录树还需要多少个节点，初始时需要一个根节点
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        int i = 0;
        while (i < n){
            if (stack.isEmpty())    return false;
            char c = preorder.charAt(i);
            if (c == ','){
                i++;
            }else if (c == '#'){
                int top = stack.pop() - 1;
                //如果还有槽位，继续添加进去
                if (top > 0)    stack.push(top);
                i++;
            }else {
                //读一个数字，可能不止一位
                while (i < n && preorder.charAt(i) != ','){
                    i++;
                }
                int top = stack.pop() - 1;
                if (top > 0)    stack.push(top);
                //没进一个数字，就需要补两个槽位
                stack.push(2);
            }
        }
        return stack.isEmpty();
    }

    public boolean isValidSerialization2(String preorder) {
        Deque<String> stack = new LinkedList<>();
        for (String s:preorder.split(",")) {
            stack.push(s);
            //每次遇到 （数字 # #）的情况，就将他替换成一个#
            while (stack.size() >= 3
                    && ((LinkedList<String>) stack).get(0).equals("#")
                    && ((LinkedList<String>) stack).get(1).equals("#")
                    && !((LinkedList<String>) stack).get(2).equals("#")){
                stack.pop();
                stack.pop();
                stack.pop();
                stack.push("#");
            }
        }
        return stack.size() == 1 && stack.pop().equals("#");
    }

    public boolean isValidSerialization3(String preorder) {
        //计算出度入度，出度==入度
        //diff 表示 出度-入度，#节点会提供0出度，1入度，非#节点会提供2出度，1入度
        //初始化为1是当根节点为#时，结果为0，不为#时结果为2，保证出入度的正确性
        int diff = 1;
        for (String s:preorder.split(",")) {
            //不论空还是非空，都会提供 1 入度
            diff -= 1;
            //还没有到叶子节点，出度一定大于入度
            if (diff < 0)   return false;
            //非叶子节点提供 2 入度
            if (!s.equals("#")){
                diff += 2;
            }
        }
        return diff == 0;
    }

}
