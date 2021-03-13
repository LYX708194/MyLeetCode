package leetcode.interview;

import java.util.*;

/**
 * @author lyx
 * @date 2021/3/13 16:03
 */
public class Meituan {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(),m = sc.nextInt();
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = sc.nextInt();
        }
        boolean[][] connects = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            int r = sc.nextInt(),c = sc.nextInt();
            connects[r][c] = connects[c][r] = true;
        }
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            maxLen = Math.max(maxLen,dfs(i,connects,height,new boolean[n][n]));
        }
        System.out.println(maxLen);
    }
    private static int dfs(int index,boolean[][] connects,int[] height,boolean[][] isVisted){
        int ans = 0;
        for (int i = 0; i < connects.length; i++) {
            //连通的，未访问的且高度小于等于当前的
            if (connects[index][i] && !isVisted[index][i] && height[index] >= height[i]){
                isVisted[index][i] = true;
                ans = Math.min(ans,dfs(i,connects,height,isVisted));
            }
        }
        return ans;
    }



    /**
     * 91%AC
     */
    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(),k = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            map.put(nums[i],map.getOrDefault(nums[i],0) + 1);
        }
        System.out.println(getNum(map));
        for (int i = k; i < n; i++) {
            map.put(nums[i-k],map.get(nums[i-k]) - 1);
            map.put(nums[i],map.getOrDefault(nums[i],0) + 1);
            System.out.println(getNum(map));
        }
    }
    private static int getNum(Map<Integer,Integer> map){
        int minNum = Integer.MAX_VALUE;
        int maxNum = 0;
        for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
            maxNum = Math.max(maxNum,entry.getValue());
        }
        for (Map.Entry<Integer,Integer> entry:map.entrySet()) {
            if (entry.getValue() == maxNum){
                minNum = Math.min(minNum,entry.getKey());
            }
        }
        return minNum;
    }



    /**
     * ac64%
     * @param args
     */
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();
        int i = 0;
        //判断是否是负数
        boolean flag = true;
        long num = -1l;
        List<Long> ans = new ArrayList<>();
        while (i < n){
            char c = s.charAt(i);
            //不是数字
            if (!Character.isDigit(c)){
                if (c == '-')   flag = false;
                if (num != -1){
                    if (!flag){
                        ans.add(-num);
                    }else{
                        ans.add(num);
                    }
                }
                flag = true;
                num = -1;
            }else {
                if (num == -1)  num = 0;
                num = num * 10 + (c - '0');
            }
            i++;
        }
        if (num >= 0){
            ans.add(num);
        }
        Collections.sort(ans);
        for (int j = 0; j < ans.size(); j++) {
            System.out.println(ans.get(j));
        }
    }


    public static void main1(String[] args){
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(),n = in.nextInt();
        int[][] nums = new int[m][n];
        int i = 0,j = 0;
        while (in.hasNext()){
            if (j < n){
                nums[i][j++] = in.nextInt();
            }else{
                i++;
                j = 0;
                nums[i][j++] = in.nextInt();
            }
        }
        for (j = 0; j < n; j++) {
            for (i = 0; i < m; i++) {
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }
    }

}
