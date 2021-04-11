package leetcode.time2021.four;

import java.util.LinkedList;
import java.util.List;

/** 复原ip地址
 * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 *
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 *
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 *
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 示例 4：
 *
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 示例 5：
 *
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *  
 *
 * 提示：
 *
 * 0 <= s.length <= 3000
 * s 仅由数字组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/4/11 13:20
 */
public class Solution93 {

    List<String> ans;
    public List<String> restoreIpAddresses(String s) {
        this.ans = new LinkedList<>();
        back(s,0,new LinkedList<String>(),0);
        return ans;
    }
    private void back(String s,int index,LinkedList<String> path,int segment){
        if (index == s.length()){
            if (segment == 4){
                ans.add(String.join(".",path));
            }
            return;
        }
        //剩下的不够剪或者剩下的太多了，剪枝
        if (s.length() - index < (4 - segment) || s.length() - index > 3 * (4 - segment))   return;
        //不能有前导0，所以这一段只能为0
        if (s.charAt(index) == '0'){
            path.add("0");
            back(s,index+1,path,segment+1);
            path.removeLast();
        }
        int num = 0;
        for (int i = 0; i < 3; i++) {
            if(index + i == s.length())   break;
            num = num * 10 + (s.charAt(index+i) - '0');
            if (num > 0 && num <= 0xFF){
                path.add(num+"");
                back(s, index+i+1, path, segment+1);
                path.removeLast();
            }else{
                break;
            }
        }
    }


    public static void main(String[] args) {
        Solution93 solution93 = new Solution93();
        solution93.restoreIpAddresses("25525511135");
    }

}
