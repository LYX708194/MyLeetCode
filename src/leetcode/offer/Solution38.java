package leetcode.offer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/** 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 *
 *  
 *
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 *  
 *
 * 示例:
 *
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *  
 *
 * 限制：
 *
 * 1 <= s 的长度 <= 8
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/14 15:43
 */
public class Solution38 {


    List<String> res = new LinkedList<>();
    boolean[] used;
    StringBuilder str = new StringBuilder();

    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        //排序，方便剪枝
        Arrays.sort(chars);
        used = new boolean[s.length()];

        back(chars,0,s.length());
        return res.toArray(new String[res.size()]);
    }
    private void back(char[] chars,int index,int target){
        if(index == target){
            res.add(str.toString());
            return;
        }
        for (int i = 0; i < target; i++) {
            if (!used[i]){
                //剪枝，元素相同
                if (i > 0 && chars[i] == chars[i-1] && !used[i-1])  continue;
                used[i] = true;
                str.append(chars[i]);
                back(chars,index+1,target);
                str.deleteCharAt(str.length()-1);
                used[i] = false;
            }
        }
    }


}
