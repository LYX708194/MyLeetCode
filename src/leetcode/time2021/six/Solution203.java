package leetcode.time2021.six;

import leetcode.entity.ListNode;

/**
 * @author lyx
 * @date 2021/6/5 23:07
 */
public class Solution203 {

    public ListNode removeElements(ListNode head, int val) {
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode pre = preHead,cur = head;
        while(cur != null){
            if(cur.val != val){
                pre.next = cur;
                pre = cur;
            }
            cur = cur.next;
        }
        pre.next = cur;
        return preHead.next;
    }

}
