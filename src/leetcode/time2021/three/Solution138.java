package leetcode.time2021.three;

/**
 * @author lyx
 * @date 2021/3/13 15:51
 */
public class Solution138 {


//    public Node copyRandomList(Node head) {
//
//    }



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
