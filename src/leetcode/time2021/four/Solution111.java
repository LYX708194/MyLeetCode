package leetcode.time2021.four;

import leetcode.entity.TreeNode;

/**
 * @author lyx
 * @date 2021/4/17 13:13
 */
public class Solution111 {

    public int minDepth(TreeNode root) {
        if(root == null)    return 0;
        if(root.left == null) return minDepth(root.right) + 1;
        else if(root.right == null) return minDepth(root.left) + 1;
        else return Math.min(minDepth(root.left),minDepth(root.right)) + 1;
    }

}
