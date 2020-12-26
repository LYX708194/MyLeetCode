package leetcode.time2020.twelve;

import leetcode.entity.TreeNode;

import java.util.*;

/**
 * 103 二叉树的锯齿形层次遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2020/12/22 10:26
 */
public class Solution103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)   return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                list.add(treeNode.val);
                if (treeNode.left != null)   queue.offer(treeNode.left);
                if (treeNode.right != null)   queue.offer(treeNode.right);
            }
            if (res.size() % 2 == 1){
                Collections.reverse(list);
            }
            res.add(list);
        }
        return res;
    }

    /**
     * 栈+双端队列
     * @author lyx
     * @date 2020/12/22 10:45
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)   return res;
        Queue<TreeNode> queue = new LinkedList<>();
        boolean left = true;
        queue.offer(root);
        while (!queue.isEmpty()){
            Deque<Integer> deque = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if (left){
                    deque.addLast(treeNode.val);
                }else{
                    deque.addFirst(treeNode.val);
                }
                if (treeNode.left != null)   queue.offer(treeNode.left);
                if (treeNode.right != null)   queue.offer(treeNode.right);
                left = !left;
            }
            res.add(new ArrayList<>(deque));
            left = !left;
        }
        return res;
    }


}
