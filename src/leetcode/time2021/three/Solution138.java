package leetcode.time2021.three;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyx
 * @date 2021/3/13 15:51
 */
public class Solution138 {


    public Node copyRandomList(Node head) {
        if (head == null)   return null;
        Map<Node,Node> map = new HashMap();
        Node node = head;
        while (node != null){
            map.put(node,new Node(node.val));
            node = node.next;
        }
        node = head;
        while (node != null){
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);
    }

    public Node copyRandomList2(Node head) {
        if (head == null)   return null;
        Node cur = head;
        //在节点后面构造新节点
        while (cur != null){
            Node node = new Node(cur.val);
            node.next = cur.next;
            cur.next = node;
            cur = node.next;
        }
        cur = head;
        //构造新节点的random指向
        while (cur != null){
            if (cur.random != null){
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        cur = head.next;
        Node pre = head,res = head.next;
        //将新旧节点拆分
        while (cur.next != null){
            pre.next = pre.next.next;
            cur.next = cur.next.next;
            pre = pre.next;
            cur = cur.next;
        }
        //单独修改原始的结尾节点
        pre.next = null;
        return res;
    }



    private class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

}
