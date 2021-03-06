package leetcode.time2020.eleven;

/**
 *
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 *
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 *
 * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
 *
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 *
 * 提示：
 *
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 *
 * @author lyx
 * @date 2020/11/1 12:30
 */
public class Solution08 {

    public int myAtoi(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();
        int index = 0;
        //先消除空格
        while (index < length && chars[index] == ' '){
            index++;
        }
        //针对极端用例，全是空格
        if (index == length)    return 0;
        //如果出现符号字符，仅第一个有效，记录正负
        int sign = 1;
        char firstChar = chars[index];
        if (firstChar == '+'){
            index++;
        }else if(firstChar == '-'){
            index++;
            sign = -1;
        }
        int res = 0;
        while (index < length){
            char currChar = chars[index];
            //不合法情况
            if (currChar > '9' || currChar < '0'){
                break;
            }
            //判断乘以10是否越界
            if (res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE/10 && (currChar - '0') > Integer.MAX_VALUE % 10)){
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE/10 || (res == Integer.MIN_VALUE/10 && (currChar - '0') > -(Integer.MIN_VALUE % 10))){
                return Integer.MIN_VALUE;
            }
            //前面合法的情况下，每一步都把符号位乘进去
            res = res *10 + sign*(currChar - '0');
            index++;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution08 solution08 = new Solution08();
        solution08.myAtoi("-2147483647");
    }

}
