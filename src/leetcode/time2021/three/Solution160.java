package leetcode.time2021.three;

import leetcode.entity.ListNode;

/** 相交链表
 * @author lyx
 * @date 2021/3/17 13:48
 */
public class Solution160 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        //最多循环 m+n次，如果不相交，最后会同样是null而退出循环
        while (A != B) {
            //a为空时转b头，b为空时转a头
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }


}
