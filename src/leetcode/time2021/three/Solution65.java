package leetcode.time2021.three;

/** 有效数字
 * 有效数字（按顺序）可以分成以下几个部分：
 *
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 小数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 *
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 部分有效数字列举如下：
 *
 * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
 * 部分无效数字列举如下：
 *
 * ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
 * 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/19 15:33
 */
public class Solution65 {

    public boolean isNumber(String s) {
        int state = 0;
        //3,5,6,8状态时可以终止
        boolean[] finals = new boolean[]{false,false,false,true,false,true,true,false,true};
        /*
            state  blank   +/-   0-9   .   e/E   other
初始化       0       0       1     6    2    -1     -1
正负符号后    1      -1      -1     6    2   -1     -1
小数点       2      -1      -1     3   -1   -1     -1
小数点后整数  3       8      -1     3   -1    4     -1
e/E之后      4      -1      7     5   -1    -1     -1
指数整数     5       8      -1     5   -1    -1     -1
小数点前整数  6       8     -1     6    3     4     -1
指数后+/-    7      -1     -1     5   -1     -1    -1
7状态后空格   8       8      -1    -1   -1   -1     -1
         */
        int[][] transfer = new int[][]{
                {0,1,6,2,-1,-1},
                {-1,-1,6,2,-1,-1},
                {-1,-1,3,-1,-1,-1},
                {8,-1,3,-1,4,-1},
                {-1,7,5,-1,-1,-1},
                {8,-1,5,-1,-1,-1},
                {8,-1,6,3,4,-1},
                {-1,-1,5,-1,-1,-1},
                {8,-1,-1,-1,-1,-1}
        };
        for (int i = 0; i < s.length(); i++) {
            state = transfer[state][make(s.charAt(i))];
            if (state < 0)  return false;
        }
        return finals[state];
    }
    private int make(char c){
        //返回状态数组对应下标位置
        switch (c){
            case ' ':return 0;
            case '+':
            case '-':return 1;
            case '.':return 3;
            case 'e':
            case 'E':return 4;
            default: return Character.isDigit(c) ? 2 : 5;
        }
    }



}
