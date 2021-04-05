package leetcode.LCP;

import java.math.BigInteger;

/**
 * 某乐团的演出场地可视作 num * num 的二维矩阵 grid（左上角坐标为 [0,0])，每个位置站有一位成员。乐团共有 9 种乐器，乐器编号为 1~9，每位成员持有 1 个乐器。
 *
 * 为保证声乐混合效果，成员站位规则为：自 grid 左上角开始顺时针螺旋形向内循环以 1，2，...，9 循环重复排列。例如当 num = 5 时，站位如图所示
 *
 *
 *
 * 请返回位于场地坐标 [Xpos,Ypos] 的成员所持乐器编号。
 *
 * 示例 1：
 *
 * 输入：num = 3, Xpos = 0, Ypos = 2
 *
 * 输出：3
 *
 * 解释：
 *
 *
 * 示例 2：
 *
 * 输入：num = 4, Xpos = 1, Ypos = 2
 *
 * 输出：5
 *
 * 解释：
 *
 *
 * 提示：
 *
 * 1 <= num <= 10^9
 * 0 <= Xpos, Ypos < num
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/SNJvJP
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/4/5 22:12
 */
public class Solution29 {

    public int orchestraLayout(int num, int xPos, int yPos) {
        int half = num / 2;
        //计算目标外围有多少层
        long cnt = Math.min(xPos <= half ? xPos : num - xPos - 1,yPos <= half ? yPos : num - yPos - 1);
        //计算外cnt层的数量
        long itemIdx = (cnt * (long)(num - cnt)) * 4;
        //若是最内层的中间
        if(itemIdx + 1 == (long)num * num)  return ((itemIdx + 1) % 9) == 0 ? 9 : (int) ((itemIdx + 1) % 9);

        int top = (int) cnt,bottom = (int) (num - cnt - 1),left = (int) cnt,right = bottom;
        //向左,若目标在上方那一行
        if(top == xPos) {
            //加上该行从开始列到目标列的数量
            itemIdx += yPos - left + 1;
            //取余得到乐器的种类，但能够整除时乐器号是9，所以需要判断一下
            return (itemIdx % 9) == 0 ? 9 : (int) (itemIdx % 9);
        }
        //不在上方那一行，则需要加上该行的数量
        itemIdx += right - left + 1;
        ++top;
        //向下,若目标在右侧那一行
        if(right == yPos) {
            itemIdx += xPos - top + 1;
            return (itemIdx % 9) == 0 ? 9 : (int) (itemIdx % 9);
        }
        itemIdx += bottom - top + 1;
        --right;
        //向右,若目标在下方那一行
        if(bottom == xPos) {
            itemIdx += right - yPos + 1;
            return (itemIdx % 9) == 0 ? 9 : (int) (itemIdx % 9);
        }
        itemIdx += right - left + 1;
        --bottom;
        //向上,若目标在左侧那一行
        if(left == yPos) {
            itemIdx += bottom - xPos + 1;
            return (itemIdx % 9) == 0 ? 9 : (int) (itemIdx % 9);
        }
        return (itemIdx % 9) == 0 ? 9 : (int) (itemIdx % 9);
    }

    public int orchestraLayout2(int num, int xPos, int yPos) {
        int cur = 0;
        int top = 0,left = 0,right = num-1,bottle = num-1;
        int x1 = num - xPos - 1,y1 = num - yPos - 1;
        int min = Math.min(x1,Math.min(y1,Math.min(xPos,yPos))),max = num - min * 2;
        //越界
//        cur = (num * num - max * max) % 9;
//        long a = num * num;
//        long b = max * max;
//        a -= b;
//        cur = (int) (a % 9);
        BigInteger temp = new BigInteger(String.valueOf(num));
        temp = temp.multiply(temp);
        BigInteger a = new BigInteger(String.valueOf(max));
        a = a.multiply(a);
        BigInteger res = temp.subtract(a).mod(new BigInteger("9"));
        cur = res.intValue();
        top += min;left += min;bottle -= min;right -= min;
        cur++;
        if (xPos == top){
            cur = (cur + yPos - left) % 9;
        }else if (xPos == bottle){
            //cur = (cur + (right - left)*2 + right - yPos) % 9;
            cur = (cur + right - left) % 9;
            cur = (cur + right - left) % 9;
            cur = (cur + right - yPos) % 9;
        }else if (yPos == left){
            //cur = (cur + (right - left)*3 + bottle - xPos) % 9;
            cur = (cur + right - left) % 9;
            cur = (cur + right - left) % 9;
            cur = (cur + right - left) % 9;
            cur = (cur + bottle - xPos) % 9;
        }else{
            //cur = (cur + right - left + xPos - top) % 9;
            cur = (cur + right - left) % 9;
            cur = (cur + xPos - top) % 9;
        }
        return cur == 0 ? 9: cur;
    }

}
