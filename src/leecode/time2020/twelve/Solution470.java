package leecode.time2020.twelve;

/**
 * 470 用rand（7）实现rand（10）
 *
 * 已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。
 *
 * 不要使用系统的 Math.random() 方法。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: [7]
 * 示例 2:
 *
 * 输入: 2
 * 输出: [8,4]
 * 示例 3:
 *
 * 输入: 3
 * 输出: [8,1,10]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-rand10-using-rand7
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2020/12/14 14:22
 */
public class Solution470{

    /**
     *
     * https://leetcode-cn.com/problems/implement-rand10-using-rand7/solution/cong-zui-ji-chu-de-jiang-qi-ru-he-zuo-dao-jun-yun-/
     *
     * 已知 rand_N() 可以等概率的生成[1, N]范围的随机数
     * 那么：
     * (rand_X() - 1) × Y + rand_Y() ==> 可以等概率的生成[1, X * Y]范围的随机数
     * 即实现了 rand_XY()
     *
     * @return
     */
    public int rand10() {
//        while (true){
//            //同等概率生成 1-49 的数
//            int num = (rand7() - 1) * 7 + rand7();
//            //取前40个数，超过则重新计算，否则取余然后返回
//            if (num <= 40)  return num % 10 + 1 ;
//        }
        while (true){
            int a = rand7();
            int b = rand7();
            //rand49
            int num = (a - 1) * 7 + b;
            //满足采样条件则返回，否则进入下面的优化
            if (num <= 40)  return num % 10 + 1 ;

            //利用超过的这九个数  rand9
            a = num - 40;
            b = rand7();
            //rand63
            num = (a - 1) * 7 + b;
            if (num <= 60) return num % 10 + 1 ;

            a = num - 60;
            b = rand7();
            //rand21
            num = (a - 1) * 7 + b;
            if (num <= 20) return num % 10 + 1 ;
        }
    }

    public static int rand7(){
        return (int) (Math.random() * 7) + 1;
    }


    public static void main(String[] args) {
        System.out.println(rand7());
    }

}
