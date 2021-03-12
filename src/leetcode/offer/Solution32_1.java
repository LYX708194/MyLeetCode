package leetcode.offer;

import leetcode.entity.TreeNode;

import java.util.*;

/**
 * @author lyx
 * @date 2021/3/12 16:57
 */
public class Solution32_1 {

    public int[] levelOrder(TreeNode root) {
        if(root == null) return new int[0];
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> ans = new ArrayList<>();
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            ans.add(node.val);
            if (node.left != null)  queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

}
