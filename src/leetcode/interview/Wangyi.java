package leetcode.interview;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author lyx
 * @date 2021/3/27 15:00
 */
public class Wangyi {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int top = sc.nextInt();
        int de = sc.nextInt();
        List<Integer> goods = new LinkedList<>();
        while (sc.hasNext()){
            goods.add(sc.nextInt());
        }
        addGood(0,top,0,goods);
        System.out.println(max - de);
    }
    private static void addGood(int index,int top,int sum,List<Integer> goods){
        if (sum >= top){
            max = Math.min(max,top);
            return;
        }
        int newSum = sum + goods.get(index);
        for (int i = index; i < goods.size(); i++) {
            sum += goods.get(i);
            addGood(i + 1, top, sum, goods);
            sum -= goods.get(i);
        }
    }


    static int max = -1;

    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        back(0,0,nums);
        System.out.println(max);
    }
    private static void back(int index,int sum,int[] nums){
        if (sum % 6 == 0 && sum != 0)  max = Math.max(max,sum);
        for (int i = index; i < nums.length; i++) {
            sum += nums[i];
            back(i + 1, sum, nums);
            sum -= nums[i];
        }
    }


    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        if (n < 5) System.out.println(0);
        int max = 0;
        int left = 0,right = 1;
        while (nums[right-1] == nums[right]){
            right++;
        }
        left = right - 1;
        boolean up = nums[right] > nums[right-1];
        int cnt = 0;
        while (right < n){
            if (nums[right] == nums[right-1])  {
                left = right;
                right++;
                up = nums[right] > nums[right-1];
                cnt = 1;
                continue;
            }
            if (up){
                if (nums[right] > nums[right-1] && cnt < 2){
                    cnt++;
                    if (cnt == 2){
                        if (right-left >= 4)    max = Math.max(max,right-left + 1);
                        up = !up;
                        cnt = 0;
                    }
                }else{
                    left = right - 1;
                    cnt = 1;
                    up = nums[right] > nums[right-1];
                }
            }else{
                if (nums[right] < nums[right-1] && cnt < 2){
                    cnt++;
                    if (cnt == 2){
                        if (right-left >= 4)    max = Math.max(max,right-left + 1);
                        up = !up;
                        cnt = 0;
                    }
                }else{
                    left = right - 1;
                    cnt = 1;
                    up = nums[right] > nums[right-1];
                }
            }
            right++;
        }
        System.out.println(max);
    }



    static List<List<Integer>> ans = new LinkedList<>();
    static List<Integer> list = new LinkedList<>();

    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        String tree = sc.nextLine();
        int target = sc.nextInt();
        tree = tree.substring(1,tree.length()-1);
        String[] value = tree.split(",");
        int n = value.length;
        int sum = 0;
        dfs(value,0,target,0);
        Collections.sort(ans,(l1,l2)->l1.size() - l2.size());
        if (ans.size() == 0) System.out.println("[]");
        else {
            System.out.print("[");
            for (int i = 0; i < ans.get(0).size(); i++) {
                System.out.print(ans.get(0).get(i));
                if (i != ans.get(0).size() - 1) System.out.print(",");
            }
            System.out.println("]");
        }
    }
    private static void dfs(String[] value,int index,int target,int sum){
        if (sum == target){
            ans.add(new LinkedList<>(list));
            return;
        }
        for (int i = 0; i < 2; i++) {
            int newSum = sum + Integer.valueOf(value[index]);
            if (newSum > target)   continue;
            else    dfs(value,index * 2,target,newSum);
        }
    }

}
