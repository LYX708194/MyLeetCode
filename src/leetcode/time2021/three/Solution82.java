package leetcode.time2021.three;

import leetcode.entity.ListNode;

/**删除排序链表中的重复元素2
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 *
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/20 11:30
 */
public class Solution82 {


    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null || head.next == null)   return head;
        ListNode prehead = new ListNode(-1);
        prehead.next = head;
        ListNode pre = prehead;
        ListNode cur = head.next;
        int value = head.val;
        while(cur != null){
            //如果两个节点值不一样，各自向前走一步
            if (value != cur.val){
                pre = pre.next;
                value = cur.val;
                cur = cur.next;
            }else{
                //跳过所有相等的节点,然后前置节点修改指向
                while (cur != null && value == cur.val){
                    cur = cur.next;
                }
                pre.next = cur;
                if(cur == null) break;
                value = cur.val;
                cur = cur.next;
            }
        }
        return prehead.next;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)  return head;
        if (head.val == head.next.val){
            //如果相等，则跳过所有相等节点，最后返回后面的节点
            while (head != null && head.next != null && head.val == head.next.val){
                head = head.next;
            }
            return deleteDuplicates(head.next);
        }else{
            //头结点指向后面递归删除后的节点
            head.next = deleteDuplicates(head.next);
            return head;
        }
    }


}
