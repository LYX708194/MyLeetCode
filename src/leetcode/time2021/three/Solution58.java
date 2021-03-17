package leetcode.time2021.three;

/**
 * @author lyx
 * @date 2021/3/17 11:11
 */
public class Solution58 {

    public int lengthOfLastWord(String s) {
        int n = s.length();
        int i = n-1;
        int ans = 0;
        while(i >= 0){
            if(s.charAt(i--) == ' '){
                if(ans > 0) break;
            }else{
                ans++;
            }
        }
        return ans;
    }


    public int lengthOfLastWord2(String s) {
        String[] ss = s.split("\\s+");
        int n = ss.length;
        if(n == 0)  return 0;
        return ss[n-1].length();
    }

}
