package leetcode.time2020.twelve;

/**
 * @author lyx
 * @date 2020/12/24 15:07
 */
public class Q04 {

    public int cutbar(int n,int m,int current){
        int res = 0;
        if (current >= n){
            return 0;
        }else if (current < m){
            res = 1 + cutbar(n,m, current*2);
        }else {
            res =  1 + cutbar(n,m, current + m);
        }
        return res;
    }

    public static void main(String[] args) {
        Q04 q04 = new Q04();
        System.out.println(q04.cutbar(100,5,1));
    }
}
