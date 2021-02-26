package leetcode.time2021.two;

import java.util.*;

/**
 * 1178 猜字谜
 * 外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。
 *
 * 字谜的迷面 puzzle 按字符串形式给出，如果一个单词 word 符合下面两个条件，那么它就可以算作谜底：
 *
 * 单词 word 中包含谜面 puzzle 的第一个字母。
 * 单词 word 中的每一个字母都可以在谜面 puzzle 中找到。
 * 例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）都不能作为谜底。
 * 返回一个答案数组 answer，数组中的每个元素 answer[i] 是在给出的单词列表 words 中可以作为字谜迷面 puzzles[i] 所对应的谜底的单词数目。
 *
 *  
 *
 * 示例：
 *
 * 输入：
 * words = ["aaaa","asas","able","ability","actt","actor","access"],
 * puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * 输出：[1,1,3,2,4,0]
 * 解释：
 * 1 个单词可以作为 "aboveyz" 的谜底 : "aaaa"
 * 1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
 * 3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
 * 2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
 * 4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
 * 没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
 *  
 *
 * 提示：
 *
 * 1 <= words.length <= 10^5
 * 4 <= words[i].length <= 50
 * 1 <= puzzles.length <= 10^4
 * puzzles[i].length == 7
 * words[i][j], puzzles[i][j] 都是小写英文字母。
 * 每个 puzzles[i] 所包含的字符都不重复。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-valid-words-for-each-puzzle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/2/26 19:58
 */
public class Solution1178 {

    /**
     * 暴力解法，超时
     */
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> ans = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        char first;
        boolean hasFirst = false;
        for (String puzzle:puzzles) {
            int count = 0;
            first = puzzle.charAt(0);
            for (int i = 0; i < puzzle.length(); i++) {
                set.add(puzzle.charAt(i));
            }
            for (String word:words) {
                int i = 0;
                for (; i < word.length(); i++) {
                    if (!set.contains(word.charAt(i))){
                        break;
                    }
                    if (word.charAt(i) == first){
                        hasFirst = true;
                    }
                }
                if (hasFirst && i == word.length()){
                    count++;
                }
                hasFirst = false;
            }
            set.clear();
            ans.add(count);
        }
        return ans;
    }


    public List<Integer> findNumOfValidWords2(String[] words, String[] puzzles) {
        Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();

        for (String word : words) {
            int mask = 0;
            for (int i = 0; i < word.length(); ++i) {
                char ch = word.charAt(i);
                mask |= (1 << (ch - 'a'));
            }
            //因为谜语长度只有7，将符合情况的word放进map里
            if (Integer.bitCount(mask) <= 7) {
                frequency.put(mask, frequency.getOrDefault(mask, 0) + 1);
            }
        }
        List<Integer> ans = new ArrayList<Integer>();
        for (String puzzle : puzzles) {
            int total = 0;
            //枚举puzzle的二进制
            for (int choose = 0; choose < (1 << 6); ++choose) {
                int mask = 0;
                for (int i = 0; i < 6; ++i) {
                    if ((choose & (1 << i)) != 0) {
                        mask |= (1 << (puzzle.charAt(i + 1) - 'a'));
                    }
                }
                mask |= (1 << (puzzle.charAt(0) - 'a'));
                if (frequency.containsKey(mask)) {
                    total += frequency.get(mask);
                }
            }
            ans.add(total);
        }
        return ans;
    }

}
