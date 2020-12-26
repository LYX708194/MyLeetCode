package leetcode.time2020.eleven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 *
 * 注意：
 * 总人数少于1100人。
 *
 * 示例
 *
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author lyx
 * @date 2020/11/16 19:42
 */
public class Solution406 {
    /**
     * 从低到高考虑
     * 如果我们在初始时建立一个包含 n 个位置的空队列，而我们每次将一个人放入队列中时，会将一个「空」位置变成「满」位置，
     * 那么当我们放入第 i 个人时，我们需要给他安排一个「空」位置，并且这个「空」位置前面恰好还有 k_i个「空」位置，用来安排给后面身高更高的人。
     * 也就是说，第 i 个人的位置，就是队列中从左往右数第 k_i+1个「空」位置。
     */
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) ->  {
                if (o1[0] != o2[0]){
                    //身高不同按照身高来排
                    return o1[0] - o2[0];
                } else{
                    //身高相同则比较排前面人数，少的在后面，这样最后才不会空
                    return o2[1] - o1[1];
                }
            });
        int n = people.length;
        int[][] res = new int[n][];
        for (int[] one: people) {
            int space = one[1] + 1;
            for (int i = 0; i < n; i++) {
                if (res[i] == null){
                    --space;
                    if (space == 0){
                        res[i] = one;
                        break;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 从高到低排序
     * 我们也可以将每个人按照身高从大到小进行排序，处理身高相同的人使用的方法类似，即：按照 h_i为第一关键字降序，k_i为第二关键字升序进行排序。
     * 如果我们按照排完序后的顺序，依次将每个人放入队列中，那么当我们放入第 i 个人时：
     * 第 0,⋯,i−1 个人已经在队列中被安排了位置，他们只要站在第 i 个人的前面，就会对第 i 个人产生影响，因为他们都比第 i 个人高；
     * 而第 i+1,⋯,n−1 个人还没有被放入队列中，并且他们无论站在哪里，对第 i 个人都没有任何影响，因为他们都比第 i 个人矮。
     * 可以发现，后面的人既然不会对第 i 个人造成影响，可以采用「插空」的方法，依次给每一个人在当前的队列中选择一个插入的位置。也就是说，
     * 当我们放入第 i 个人时，只需要将其插入队列中，使得他的前面恰好有 k_i个人即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height/solution/gen-ju-shen-gao-zhong-jian-dui-lie-by-leetcode-sol/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int[][] reconstructQueue2(int[][] people){
        Arrays.sort(people,(o1, o2) -> {
            if (o1[0] != o2[0]){
                return o2[0] - o1[0];
            }else{
                return o1[1] - o2[1];
            }
        });
        List<int[]> ans = new ArrayList<>();
        for (int[] person: people) {
            ans.add(person[1],person);
        }
        return ans.toArray(new int[ans.size()][]);
    }



    public static void main(String[] args) {
        Solution406 solution406 = new Solution406();
        solution406.reconstructQueue(new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}});
    }

}
