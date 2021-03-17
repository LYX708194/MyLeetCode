package leetcode.time2021.three;

import leetcode.entity.ListNode;

/**
 * 旋转数组
 * @author lyx
 * @date 2021/3/17 11:21
 */
public class Solution61 {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0)   return head;
        int n = 0;
        ListNode node = head,tail = node;
        while (node != null){
            n++;
            tail = node;
            node = node.next;
        }
        tail.next = head;
        k = k % n;
        //右移k位，则新的头节点就是n-k位
        int i = n-k;
        node = head;
        while(--i > 0){
            node = node.next;
        }
        ListNode res = node.next;
        node.next = null;
        return res;
    }

}
