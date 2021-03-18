package leetcode.time2021.three;

import leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [0]
 * 输出：[0]
 *  
 *
 * 提示：
 *
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 *  
 *
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/18 15:13
 */
public class Solution114 {


    public void flatten(TreeNode root) {
        //前序遍历顺序：根节点->左子树->右子树
        TreeNode curr = root;
        while (curr != null) {
            //当前节点的左子树为空，可以不用管
            if (curr.left != null) {
                //左子树不为空，该左子树的最右节点就是右子树的前驱结点
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                //寻找左子树的最有节点
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                //将该左子树的最右节点和右子树相连
                predecessor.right = curr.right;
                //左子树置为空
                curr.left = null;
                //当前节点是原来左子树的前驱结点
                curr.right = next;
            }
            curr = curr.right;
        }
    }


    List<TreeNode> list = new ArrayList<>();

    public void flatten2(TreeNode root) {
        if(root == null)   return ;
        dfs(root);
        int n = list.size();
        TreeNode pre = list.get(0);
        pre.left = null;
        for(int i = 1;i < n;i++){
            TreeNode node = list.get(i);
            pre.right = node;
            pre = node;
            pre.left = null;
        }
    }
    private void dfs(TreeNode root){
        if(root == null)    return;
        list.add(root);
        dfs(root.left);
        dfs(root.right);
    }


}
