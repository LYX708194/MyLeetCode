package leetcode.interview;

import leetcode.entity.ListNode;
import leetcode.entity.TreeNode;

import java.util.*;

/**
 * @author lyx
 * @date 2021/3/21 20:03
 */
public class Tenxun2 {

    public static ListNode[] solve(TreeNode root, int[] b) {
        int m = b.length;
        ListNode[] ans = new ListNode[m];
        int k = 0;
        List<Integer> tree = new LinkedList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.pollFirst();
            tree.add(node.val);
            if (node.left != null) queue.addLast(node.left);
            if (node.right != null) queue.addLast(node.right);
        }
        int n = tree.size();
        for (int i = 0; i < m; i++) {
            int index = tree.indexOf(b[i]);
            boolean flag = index == 0 ? true : false;
            ListNode head = null,node = null;
            while (!flag){
                head = new ListNode(tree.get(index));
                head.next = node;
                node = head;
                index = (index-1)/2;
                if (flag)   break;
                if (index == 0) flag = true;
            }
            head = new ListNode(tree.get(0));
            head.next = node;
            ans[k++] = head;
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        int[] b = new int[]{1,2,3,4,5,6,7};
        solve(root,b);
    }


    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<int[]> nums = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int size = sc.nextInt();
            int[] num = new int[size];
            for(int j = 0; j < size; j++){
                num[j] = sc.nextInt();
            }
            nums.add(num);
        }
        int q = sc.nextInt();
        for(int i = 0;i < q;i++){
            int arrSize = sc.nextInt();
            List<Integer> list = new ArrayList();
            for(int j = 0; j < arrSize; j++){
                int arrIndex = sc.nextInt();
                int[] num = nums.get(arrIndex-1);
                for(int k = 0;k < num.length;k++){
                    list.add(num[k]);
                }
            }
            Collections.sort(list);
            int k = sc.nextInt();
            System.out.println(list.get(k-1));
        }
    }
    private int setZero(int num){
        if (num == 1)  return 1;
        int ans = 0;
        if (num % 3 == 0)   ans = setZero(num/3) + 1;
        else if (num % 2 == 0)  ans = Math.min(setZero(num/2),setZero(num-1)) + 1;
        else ans = setZero(num-1)+1;
        return ans;
    }

    public static void main5(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        for(int i = 0; i < size; i++){
            int n = sc.nextInt(),m = sc.nextInt();
            int[] pro = new int[n];
            for(int j = 0;j < n;j++){
                pro[j] = sc.nextInt();
            }
            int all = Arrays.stream(pro).sum();
            
        }
    }
    private static int getMaxSum(int[] pro,int m){
        int max = 0;
        for (int i = 0; i < pro.length; i++) {
            for (int j = 0; j < pro.length; j++) {

            }
        }
        return 0;
    }


}
