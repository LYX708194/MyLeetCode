package leetcode.time2021.three;

import leetcode.entity.TreeNode;

/**
 * @author lyx
 * @date 2021/3/9 22:07
 */
public class Solution236 {


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)   return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null)   return right;
        if (right == null)  return left;
        return root;
    }


}
