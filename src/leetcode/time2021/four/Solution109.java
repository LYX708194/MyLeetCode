package leetcode.time2021.four;

import leetcode.entity.ListNode;
import leetcode.entity.TreeNode;

/**
 * @author lyx
 * @date 2021/4/16 13:18
 */
public class Solution109 {

    public TreeNode sortedListToBST(ListNode head) {
        return buildTree(head,null);
    }
    public TreeNode buildTree(ListNode left,ListNode right){
        //左闭右开区间，右区间不存
        if (left == right)  return null;
        ListNode mid = getMid(left,right);
        TreeNode root = new TreeNode(mid.val);
        root.left = buildTree(left,mid);
        root.right = buildTree(mid.next,right);
        return root;
    }
    private ListNode getMid(ListNode left,ListNode right){
        ListNode slow = left,fast = left;
        while (fast != right && fast.next != right){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
