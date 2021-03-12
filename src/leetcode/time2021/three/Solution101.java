package leetcode.time2021.three;

import leetcode.entity.TreeNode;

import java.util.Stack;

/**
 * 101 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 *  
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *  
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *  
 *
 * 进阶：
 *
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/12 16:46
 */
public class Solution101 {

    public boolean isSymmetric(TreeNode root) {
        return root == null ? true : recur(root.left,root.right);
    }
    private boolean recur(TreeNode left,TreeNode right){
        if (left == null && right == null)  return true;
        if (left == null || right == null || left.val != right.val) return false;
        return recur(left.left,right.right) && recur(left.right,right.left);
    }

    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        //    构建 堆栈：先将 根节点的左右子节点 入栈
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curNode = root;
        stack.push(curNode.left);
        stack.push(curNode.right);

        /*
            遍历 整棵树：
                1、将 当前栈中 最上面两个节点 弹出，这两个节点对于整棵树来说 一定是 应该对称的
                2、判断 两节点是否都为空，若是，则当前两节点相同，满足对称性质，跳过本轮循环
                3、判断 两节点值是否相等：
                    若不等，返回false
                    若相等，将 两节点的左右子节点，按照对称性质入栈
        */
        while (!stack.isEmpty()) {
            TreeNode leftNode = stack.pop();
            TreeNode rightNode = stack.pop();
            if (leftNode == null && rightNode == null) {
                continue;
            }
            if (leftNode == null || rightNode == null
                    || leftNode.val != rightNode.val) {
                return false;
            }
            stack.push(leftNode.left);
            stack.push(rightNode.right);
            stack.push(leftNode.right);
            stack.push(rightNode.left);
        }

        return true;
    }

}
