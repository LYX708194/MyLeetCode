package leecode.time2020.eleven;

import leecode.entity.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 进阶：
 *
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/21 11:51
 */
public class Solution148 {

    /**
     * 同147题
     * 插入排序，时间复杂度是 O(n^2)
     */
    public ListNode sortList(ListNode head) {
        if (head == null)   return null;
        ListNode nulHead = new ListNode(0);
        nulHead.next = head;
        ListNode last = head,cur = last.next;
        while (cur != null){
            if (cur.val >= last.val){
                last = last.next;
            }else{
                ListNode pre = nulHead;
                while (pre.next.val <= cur.val){
                    pre = pre.next;
                }
                last.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;
            }
            cur = last.next;
        }
        return nulHead.next;
    }

    /**
     * 用list存起来再排序
     * @param head
     * @return
     */
    public ListNode sortList2(ListNode head){
        if (head == null)   return null;
        List<ListNode> list = new ArrayList<>();
        while (head != null){
            list.add(head);
            head = head.next;
        }
        Collections.sort(list,(o1,o2) -> o1.val - o2.val);
        for (int i = 1; i < list.size(); i++) {
            list.get(i-1).next = list.get(i);
        }
        list.get(list.size()-1).next = null;
        return list.get(0);
    }

    /**
     * 自顶向下的归并排序（递归方法）
     * @param head
     * @return
     */
    public ListNode sortList3(ListNode head){
        if (head == null || head.next == null)  return head;
        ListNode slow = head,fast = head;
        //找到链表中点
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        //将链表断开成两边
        slow.next = null;
        ListNode leftHead = sortList3(head);
        ListNode rightHead = sortList3(mid);
        return merge(leftHead,rightHead);
    }

    private ListNode merge(ListNode leftNode,ListNode rightNode){
        if (leftNode == null)   return rightNode;
        if (rightNode == null)  return leftNode;
        ListNode pre = new ListNode(0);
        ListNode temp = pre;
        while (leftNode != null && rightNode != null){
            if (leftNode.val < rightNode.val){
                pre.next = leftNode;
                leftNode = leftNode.next;
            }else{
                pre.next = rightNode;
                rightNode = rightNode.next;
            }
            pre = pre.next;
        }
        pre.next = leftNode == null ? rightNode : leftNode;
        return temp.next;
    }

    /**
     * 自底向上的归并排序（迭代）
     * @param head
     * @return
     */
    public ListNode sortList4(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        int itrv = 1;
        while(itrv < len) {
            ListNode pre = dummy;
            ListNode h = dummy.next;
            // 找到合并链表的h1和h2头节点
            while(h!=null) {
                int i = itrv;
                ListNode h1 = h;
                for(; h != null && i > 0; i--) {
                    h = h.next;
                }
                // i>0说明没有链表2直接返回
                if(i > 0) break;
                ListNode h2 = h;
                i = itrv;
                for(; h != null && i > 0; i--) {
                    h = h.next;
                }
                // 求出两个链表的长度
                int c1 = itrv;
                int c2 = itrv - i;

                //合并
                while(c1 > 0 && c2 > 0) {
                    if(h1.val < h2.val) {
                        pre.next = h1;
                        h1 = h1.next;
                        c1--;
                    }else {
                        pre.next = h2;
                        h2 = h2.next;
                        c2--;
                    }
                    pre = pre.next;
                }
                pre.next = c1 > 0 ? h1 : h2;
                while(c1 > 0 || c2 > 0) {
                    pre = pre.next;
                    c1--;
                    c2--;
                }
                pre.next = h;
            }
            itrv*=2;
        }
        return dummy.next;
    }

}
