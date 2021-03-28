package leetcode.interview;

import java.util.*;

/**
 * @author lyx
 * @date 2021/3/27 19:24
 */
public class Jingdong {

    public static void main(String[] args) {
        String s = "18007777000888880007";
        int len = 4,n = s.length();
        int[] dp = new int[n];


    }



    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Map<Character,Integer> map = new HashMap<>();
        map.put(' ',0);
        map.put('C',12);
        map.put('H',1);
        map.put('O',16);
        map.put('N',14);
        int sum = 0;
        int num = 0;
        char pre = ' ';
        for (char c:str.toCharArray()) {
            if (Character.isDigit(c)){
                num = num * 10 + c - '0';
            }else{
                sum += map.get(pre) * (num == 0 ? 1 : num);
                pre = c;
                num = 0;
            }
        }
        sum += map.get(pre) * (num == 0 ? 1 : num);
        System.out.println(sum);
    }

}
