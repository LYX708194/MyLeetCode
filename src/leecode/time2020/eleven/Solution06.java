package leecode.time2020.eleven;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/2 20:15
 */
public class Solution06 {

    public String convert(String s, int numRows) {
        if (numRows == 1)   return s;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows,s.length()); i++) {
            rows.add(new StringBuilder());
        }
        boolean down = false;
        int curRow = 0;
        for (Character c:s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows-1)     down = !down;
            curRow += down ? 1:-1;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder stringBuilder:rows) {
            res.append(stringBuilder);
        }
        return res.toString();
    }

    /**
     每一行字母的所有下标其实是有规则的
     我们先假定有 numRows=4 行来推导下，其中 2*numRows-2 = 6 , 我们可以假定为 step=2*numRows-2 ，我们先来推导下规则：
     第0行： 0 - 6 - 12 - 18
     ==> 下标间距 6 - 6 - 6 ==> 下标间距 step - step - step
     第1行： 1 - 5 - 7 - 11 - 13
     ==> 下标间距 4 - 2 - 4 - 2 ==> 下标间距step-2*1(行)-2*1(行)-step-2*1(行)-2*1(行)
     第2行： 2 - 4 - 8 - 10 - 14
     ==> 下标间距 2 - 4 - 2 - 4 ==> 下标间距step-2*2(行)-2*2(行)-step-2*2(行)-2*2(行)
     第3行：3 - 9 - 15 - 21
     ==> 下标间距间距 6 - 6 - 6 ==>下标间距step - step - step

     可以得出以下结论：
      1、起始下标都是行号
      2、第0层和第numRows-1层的下标间距总是step 。
      3、中间层的下标间距总是step-2*行数，2*行数交替。
      4、下标不能超过len(s)-1

     */
    public String convert2(String s, int numRows) {
        if (numRows == 1)   return s;
        int step = 2 * numRows - 2;     //间距
        int index = 0;          //记录s的下标
        int len = s.length();
        int add = 0;            //真实的间距
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numRows; i++) {  //i表示行号
            index = i;
            add = i * 2;
            //超出字符串长度计算下一行
            while (index < len){
                res.append(s.charAt(index));
                //第一次间距是step -2*i，第二次是2*i,
                add = step - add;
                index += (i == 0 || i == numRows - 1) ? step : add;
            }
        }
        return res.toString();
    }




}
