package leetcode.interview;

import java.util.*;

/**
 * @author lyx
 * @date 2021/3/4 19:02
 */
public class xiecheng {

    static int ans = Integer.MAX_VALUE;
    static Map<String,Integer> map1 = new HashMap<>();
    static Map<String,LinkedList<String>> map2 = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] packs = sc.nextLine().split("\\s+");
        String[] prices = sc.nextLine().split("\\s+");
        String[] value = sc.nextLine().split("\\s+");
        for (int i = 0; i < packs.length; i++) {
            map1.put(packs[i], Integer.valueOf(prices[i]));
        }
        for (int i = 0; i < packs.length; i++) {
            for (String s:packs[i].split(",")) {
                LinkedList<String> list = map2.getOrDefault(s,new LinkedList<>());
                list.add(packs[i]);
                map2.put(s,list);
            }
        }
        dfs(new boolean[value.length],value,0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
    private static void dfs(boolean[] has,String[] value,int sum){
        int i = 0;
        while (i < has.length && has[i++]) ;
        if (i == has.length)    ans = Math.min(sum,ans);
        for (int j = 0; j < value.length; j++) {
            if (has[j]) continue;
            for (String s:map2.get(value[j])) {
                sum += map1.get(s);
                has[j] = true;
                dfs(has,value,sum);
                sum -= map1.get(s);
                has[j] = false;
            }
        }
    }

    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            str.append(sc.nextLine().trim() + " ");
        }
        String[] strs = str.toString().split("\\s+");
        Set<String> set = new HashSet<>();
        int i = 0,n = strs.length;
        while (i < n){
            if (strs[i].equals("from") || strs[i].equals("join") || strs[i].equals("on")){
                if(i < n-1 && strs[i+1].equals("(")){
                    i++;
                    continue;
                }
                for (String s:strs[i+1].split(",")) {
                    if (!set.contains(s)){
                        System.out.println(s);
                        set.add(s);
                    }
                }
            }else if (strs[i].equals(")")){
                for (String s:strs[i+1].split(",")) {
                    if (!set.contains(s)){
                        System.out.println(s);
                        set.add(s);
                    }
                }
            }
            i++;
        }
    }


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

    public static void main2(String[] args){
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
