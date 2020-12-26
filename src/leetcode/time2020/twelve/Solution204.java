package leetcode.time2020.twelve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 204 计数质数
 *
 * 统计所有小于非负整数 n 的质数的数量。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 示例 2：
 *
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 *
 * 输入：n = 1
 * 输出：0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-primes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/12/3 10:39
 */
public class Solution204 {

    /**
     * 枚举法，当一个数是质数时，它能不被 sqrt（x）以下的数整除
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 2; i < n; i++) {
            ans += isPrimes(i) ? 1 : 0;
        }
        return ans;
    }

    boolean isPrimes(int x){
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 埃氏筛
     * 如果 x 是质数，那么大于 x 的 x 的倍数 2x,3x,… 一定不是质数，因此我们可以从这里入手。
     * 我们设 isPrime[i] 表示数 i 是不是质数，如果是质数则为 1，否则为 0。从小到大遍历每个数，
     * 如果这个数为质数，则将其所有的倍数都标记为合数（除了该质数本身），即 0，这样在运行结束的时候我们即能知道质数的个数。
     * 当然这里还可以继续优化，对于一个质数 x，如果按上文说的我们从 2x 开始标记其实是冗余的，应该直接从 x⋅x 开始标记，因为 2x,3x,…
     * 这些数一定在 x 之前就被其他数的倍数标记过了，例如 2 的所有倍数，3 的所有倍数等。
     * @param n
     * @return
     */
    public int countPrimes2(int n) {
        int[] isPrimes = new int[n];
        Arrays.fill(isPrimes,1);
        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (isPrimes[i] == 1){
                ans += 1;
                if ( (long)i * i < n){
                    for (int j = i*i; j < n; j += i) {
                        isPrimes[j] = 0;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 线性筛
     * 埃氏筛其实还是存在冗余的标记操作，比如对于 15 这个数，它会同时被 3,5 两个数标记为合数，
     * 因此我们优化的目标是让每个合数只被标记一次，这样时间复杂度即能保证为 O(n)，这就是我们接下来要介绍的线性筛。
     *如果 x 可以被 primes[i]整除，那么对于合数 y=x * primes[i+1]而言，
     * 它一定在后面遍历到  x / primes[i] * primes[i+1]这个数的时候会被标记，
     * 其他同理，这保证了每个合数只会被其「最小的质因数」筛去，即每个合数被标记一次。
     * @param n
     * @return
     */
    public int countPrimes3(int n) {
        int[] isPrimes = new int[n];
        Arrays.fill(isPrimes,1);
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (isPrimes[i] == 1){
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; j++) {
                isPrimes[i * primes.get(j)] = 0;
                if (i % primes.get(j) == 0){
                    break;
                }
            }
        }
        return primes.size();
    }

}
