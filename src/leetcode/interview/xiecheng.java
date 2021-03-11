package leetcode.interview;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lyx
 * @date 2021/3/4 19:02
 */
public class xiecheng {

//    public static void main(String[] args) {
//        int ans = 0;
//        Stack<String> stack = new Stack<>();
//        String s;
//        Scanner sc = new Scanner(System.in);
//        while(sc.hasNext()){
//            s = sc.next();
//            if (s.equals(")")){
//                //可能有超过两个操作数
//                List<String> nums    = new LinkedList<>();
//                while (!stack.peek().equals("(")){
//                    nums.add(stack.pop());
//                }
//                //最后将左括号也弹出
//                stack.pop();
//                int size = nums.size();
//                String newNum = "";
//                for (int i = 0; i < size - 2; i++) {
//                    newNum = operate(Integer.valueOf(nums.get(size-2)),Integer.valueOf(nums.get(i)),nums.get(size-1));
//                    nums.set(size - 2,newNum);
//                }
//                stack.add(newNum);
//                if (stack.size() == 1 && stack.peek() != "("){
//                    System.out.println(stack.pop());
//                }
//            }else{
//                stack.add(s);
//            }
//        }
//    }
//    private static String operate(int num1,int num2,String operate){
//        int ans = 0;
//        switch (operate){
//            case "*" :{
//                ans = num1 * num2;
//                break;
//            }
//            case "/" :{
//                ans = num1 / num2;
//                break;
//            }
//            case "+" :{
//                ans = num1 + num2;
//                break;
//            }
//            case "-" :{
//                ans = num1 - num2;
//                break;
//            }
//        }
//        return String.valueOf(ans);
//    }
//(- (* 4 5) 4 5)
//(*(+2 3) (-100 (+ 20 10)))


    static int maxAmount(int[] packets, int n) {
        Arrays.sort(packets);
        int ans = 0;
        int num = 0;
        int len = packets.length;
        int[] sum = new int[n+1];
        int i = len - 1;
        for (;n > 1; i--) {
            sum[n--] = packets[i];
        }
        for (int j = 0; j <= i; j++) {
            sum[0] += packets[i];
        }
        num = Arrays.stream(sum).min().getAsInt();
        ans = num;
        for (int j = 0; j < i; j++) {
            sum[0] -= packets[i];
            sum[1] += packets[i];
            i--;
            num = Arrays.stream(sum).min().getAsInt();
            ans = Math.max(num,ans);
        }
        return ans;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int[] packets = stringToIntegerArray(in.nextLine().trim());
        int n = Integer.parseInt(in.nextLine().trim());

        int res = maxAmount(packets, n);
        System.out.println(res);
    }

    public static int[] stringToIntegerArray(String in) {
        in = in.trim();
        in = in.substring(1, in.length() - 1);
        if (in.length() == 0) {
            return new int[0];
        }

        String[] arr = in.split(",");
        int[] out = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            out[i] = Integer.parseInt(arr[i].trim());
        }
        return out;
    }

}
