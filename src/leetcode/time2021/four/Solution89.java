package leetcode.time2021.four;

import java.util.ArrayList;
import java.util.List;

/**
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 *
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
 *
 * 格雷编码序列必须以 0 开头。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 *
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 *
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * 示例 2:
 *
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 *      给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
 *      因此，当 n = 0 时，其格雷编码序列为 [0]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gray-code
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/4/10 12:11
 */
public class Solution89 {


    public List<Integer> grayCode(int n) {
        /**
         * 设 n 阶格雷码集合为 G(n)，则 G(n+1) 阶格雷码为：
         * 给 G(n) 阶格雷码每个元素二进制形式前面添加 0，得到 G'(n)
         * 设 G(n) 集合倒序（镜像）为 R(n)，给 R(n) 每个元素二进制形式前面添加 1，得到 R'(n)
         * G(n+1) = G'(n) ∪ R'(n) 拼接两个集合即可得到下一阶格雷码。
         */
        //初始化0的解
        List<Integer> res = new ArrayList<Integer>() {{ add(0); }};
        int head = 1;
        for (int i = 0; i < n; i++) {
            //前面加0原数字不变，只需要添加倒序后加1的数，倒序添加
            for (int j = res.size() - 1; j >= 0; j--)
                res.add(head + res.get(j));
            //要加的数，每次在前面加1，后面需要移位
            head <<= 1;
        }
        return res;
    }

    public List<Integer> grayCode2(int n) {
        List<Integer> res = new ArrayList<>();
        backTrack(res,n,0,0,true);
        return res;
    }
    private void backTrack(List<Integer> res,int n,int pos,int num,boolean first){
        if (pos == n){
            res.add(num);
            return;
        }
        //第一条分值，先加0后加1
        if (first){
            backTrack(res,n,pos+1,num<<1,true);
            backTrack(res,n,pos+1,(num<<1)+1,false);
        }else{
            //第一条分值，先加1后加0
            backTrack(res,n,pos+1,(num<<1)+1,true);
            backTrack(res,n,pos+1,num<<1,false);
        }
    }

}
