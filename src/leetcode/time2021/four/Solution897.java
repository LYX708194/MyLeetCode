package leetcode.time2021.four;

import leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyx
 * @date 2021/4/25 10:21
 */
public class Solution897 {

    private TreeNode pre;
    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummy = new TreeNode(-1);
        pre = dummy;
        inorder(root);
        return dummy.right;
    }
    private void inorder(TreeNode node){
        if (node == null)   return;
        inorder(node.left);
        pre.right = node;
        node.left = null;
        pre = node;
        inorder(node.right);
    }


    private List<TreeNode> list = new ArrayList<>();
    public TreeNode increasingBST2(TreeNode root) {
        dfs(root);
        TreeNode dummy = new TreeNode(-1);
        TreeNode cur = dummy;
        for (TreeNode node : list) {
            cur.right = node;
            node.left = null;
            cur = node;
        }
        return dummy.right;
    }
    private void dfs(TreeNode node){
        if (node == null)   return;
        dfs(node.left);
        list.add(node);
        dfs(node.right);
    }

}
