package leetcode.offer;

import leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/** 二叉搜索树的第k大节点
 * @author lyx
 * @date 2021/3/22 14:33
 */
public class Solution54 {

    int size = 0;
    Integer ans = null;
    public int kthLargest(TreeNode root, int k) {
        size = k;
        dfs(root);
        return ans;
    }
    private void dfs(TreeNode root){
        if (root == null)   return;
        if(ans != null) return;
        dfs(root.right);
        size--;
        if(size == 0){
            ans = root.val;
            return;
        }
        dfs(root.left);
    }


    public int kthLargest2(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        dfs(root,nums);
        return nums.get(nums.size() - 1 - k);
    }
    private void dfs(TreeNode root,List<Integer> nums){
        if (root == null)   return;
        dfs(root.left,nums);
        nums.add(root.val);
        dfs(root.right, nums);
    }



}
