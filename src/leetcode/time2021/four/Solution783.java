package leetcode.time2021.four;

import leetcode.entity.TreeNode;

import java.util.Stack;

/**
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 *
 * 注意：本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 * 示例 2：
 *
 *
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 树中节点数目在范围 [2, 100] 内
 * 0 <= Node.val <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/4/13 10:05
 */
public class Solution783 {

    int pre,ans;
    public int minDiffInBST(TreeNode root) {
        pre = -1;ans = Integer.MAX_VALUE;
        dfs(root);
        return ans;
    }
    private void dfs(TreeNode node){
        if (node == null)   return;
        dfs(node.left);
        if (pre == -1){
            pre = node.val;
        }else{
            ans = Math.min(ans,node.val - pre);
            pre = node.val;
        }
        dfs(node.right);
    }

    public int minDiffInBST2(TreeNode root) {
        pre = -1;ans = Integer.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()){
                TreeNode cur = stack.pop();
                if (pre != -1)  ans = Math.min(ans,cur.val-pre);
                pre = cur.val;
                root = cur.right;
            }
        }
        return ans;
    }

}
