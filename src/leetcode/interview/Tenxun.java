package leetcode.interview;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lyx
 * @date 2021/3/21 14:48
 */
public class Tenxun {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String s = sc.nextLine();
        int num1 = 0,num0 = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') num1++;
            else num0++;
        }
        System.out.println(Math.abs(num0 - num1));
    }

    public static void main3(String[] args) {
        int k = 4;
        int[] nums = new int[]{1,4,8};
        Arrays.sort(nums);
        int n = nums.length;
        int min = nums[0],max = nums[n-1];
        int root = (int) Math.pow(2,k-1);
        while (true){
            if (min <= root && max >= root){
                System.out.println(root);
                break;
            }else{
                if (min > root){
                    root += root/2;
                }else{
                    root -= root/2;
                }
            }
        }

    }

    static int ans = 0;

    public static void main4(String[] args) {
        int n = 6;
        int i = 0,num = 1;
        while (num < n){
            num <<= 1;
            i++;
        }
        num = 1;
        int[] coins = new int[i*2];
        for (int j = 0; j < i*2; j += 2) {
            coins[j] = coins[j+1] = num;
            num <<= 1;
        }
        boolean[] used = new boolean[i*2];
        back(n,coins,0,used,0);
        System.out.println(ans);
    }
    private static void back(int n,int[] coins,int index,boolean[] used,int toTarget){
        if (n == toTarget){
            ans++;
            return;
        }
        for (int i = index; i < coins.length; i++) {
            if (!used[i]){
                int sum = toTarget + coins[i];
                if (sum > n) break;
                if (i > 0 && coins[i] == coins[i-1] && !used[i-1]) continue;
                used[i] = true;
                back(n,coins,i+1,used,sum);
                used[i] = false;
            }
        }
    }





}
