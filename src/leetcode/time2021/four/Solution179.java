package leetcode.time2021.four;

import java.util.Arrays;

/**
 * @author lyx
 * @date 2021/4/12 15:04
 */
public class Solution179 {

    public String largestNumber2(int[] nums) {
        int n = nums.length;
        Integer[] aa = new Integer[n];
        for (int i = 0; i < n; i++)  aa[i] = nums[i];
        //转换为数字来排序
        Arrays.sort(aa,(a,b)->{
            long x = 10,y = 10;
            while (x <= a)  x *= 10;
            while (y <= b)  y *= 10;
            return (int)(x * b + a -(y * a + b));
        });
        if(aa[0] == 0)    return "0";
        StringBuilder s = new StringBuilder();
        for (Integer num: aa){
            s.append(num);
        }
        return s.toString();
    }


    public String largestNumber(int[] nums) {
        String[] ss = new String[nums.length];
        for (int i = 0; i < nums.length; i++) ss[i] = nums[i]+"";
        Arrays.sort(ss,(a,b)->{
            String s1 = a + b,s2 = b + a;
            return s2.compareTo(s1);
        });
        if(ss[0].equals("0"))    return "0";
        StringBuilder s = new StringBuilder();
        for (String num: ss){
            s.append(num);
        }
        return s.toString();
    }

}
