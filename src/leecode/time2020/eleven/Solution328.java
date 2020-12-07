package leecode.time2020.eleven;

import leecode.entity.ListNode;

/**
 *
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 *
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 *
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2020/11/13 10:55
 */
public class Solution328 {


    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null)  return head;
        //pre记录当前更改的最新一个奇数节点，即pre前都已修改，cur为更新的最后一个偶数节点，即cur后的都未修改
        ListNode pre = head;
        ListNode cur = head.next;
        //记录第一个偶数位置
        ListNode even = pre.next;
        while (cur != null  && cur.next != null){
            //将两个奇数位置的节点相连
            pre.next = cur.next;
            //修改pre为当前更改的最新的一个奇数节点
            pre = cur.next;
            //偶数节点相连
            cur.next = cur.next.next;
            //记录后面还没修改的最后一个偶数节点
            ListNode listNode = cur.next;
            //将新修改的最后一个奇数节点指向最开始的偶数节点
            pre.next = even;
            //修改cur为后面还没修改的最后一个偶数节点
            cur = listNode;
        }
        return head;
    }

    //分离节点后合并,把偶数节点列出来连接到最后面的奇数节点
    public ListNode oddEvenList2(ListNode head){
        if (head == null)   return head;
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        while (even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
