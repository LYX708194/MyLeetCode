package leecode.entity;

/**
 * @author lyx
 * @date 2020/10/20 10:29
 */
public class ListNode {

    public int val;
    public ListNode next;
    public ListNode(int x) {
        val = x;
        next = null;
    }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
