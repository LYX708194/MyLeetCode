package leetcode.time2020.twelve;

import leetcode.entity.ListNode;

/**
 * 24 两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 *
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1]
 * 输出：[1]
 *  
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/12/16 22:54
 */
public class Solution24 {

    /**
     * 递归
     * @author lyx
     * @date 2020/12/16 22:56
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        //第一个节点连接后面的链表
        head.next = swapPairs(newHead.next);
        //翻转
        newHead.next = head;
        //返回新的头结点
        return newHead;
    }

    /**
     * 迭代实现
     * @author lyx
     * @date 2020/12/16 23:00
     * @return
     */
    public ListNode swapPairs2(ListNode head) {
        //虚拟头结点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        //当只剩一个节点或者没有节点时退出
        while (temp.next != null && temp.next.next != null) {
            //第一个节点
            ListNode node1 = temp.next;
            //第二个节点
            ListNode node2 = temp.next.next;
            //翻转
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            //temp指向第二个节点处
            temp = node1;
        }
        return dummyHead.next;
    }


}
