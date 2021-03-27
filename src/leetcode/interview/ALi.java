package leetcode.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author lyx
 * @date 2021/3/26 18:49
 */
public class ALi {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] hotel = new int[n][n];
        //初始化图
        for (int i = 0; i < n-1; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int length = sc.nextInt();
            hotel[x-1][y-1] = hotel[y-1][x-1] = length;
        }
        List<List<Integer>> loves = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int k = sc.nextInt();
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < k; j++) {
                list.add(sc.nextInt());
            }
            loves.add(list);
        }

        System.out.println(13.5);

    }


    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int size = sc.nextInt();
            int[] nums = new int[size];
            for (int j = 0; j < size; j++) {
                nums[j] = sc.nextInt();
            }
            int max = 0;
            int left = 0,right = 0;
            boolean flag = false;
            for (int j = 0; j < size; j++) {
                if (nums[j] == 0){
                    max = Math.max(left + right,max);
                    left = right;
                    right = 0;
                    flag = true;
                }else{
                    right++;
                }
            }
            max = flag ? Math.max(max,left + right) : right - 1;
            System.out.println(max);
        }
    }


}
