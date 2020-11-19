package leecode.time2020.eleven;

import java.util.*;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * 说明：
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break-ii
 *
 *
 * @author lyx
 * @date 2020/11/1 12:04
 */
public class Solution140 {

    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<Integer,List<List<String>>> map = new HashMap<>();
        List<List<String>> wordBreaks = backTrack(s,s.length(),0,new HashSet<>(wordDict),map);
        List<String> ans = new LinkedList<>();
        for (List<String> wordBreak:wordBreaks) {
            ans.add(String.join(" ",wordBreak));
        }
        return ans;
    }
    //backtrack函数返回的相当于起始下标为index到结尾的答案
    //记忆化搜索具体体现在map上，map记录的key为起始index，value为相对应的答案
    public List<List<String>> backTrack(String s,int length,int index, Set<String> wordSet,Map<Integer, List<List<String>>> map){
        //记忆化搜索，使用哈希表存储字符串 ss 的每个下标和从该下标开始的部分可以组成的句子列表，在回溯过程中如果遇到已经访问过的下标，则可以直接从哈希表得到结果，而不需要重复计算。
        // 如果到某个下标发现无法匹配，则哈希表中该下标对应的是空列表，因此可以对不能拆分的情况进行剪枝优化。
        if (!map.containsKey(index)){
            List<List<String>> wordBreaks = new LinkedList<>();
            if (index == length){
                wordBreaks.add(new LinkedList<>());
            }
            for (int i = index+1; i <= length; i++) {
                String word = s.substring(index,i);
                if (wordSet.contains(word)){
                    List<List<String>> newWordBreaks = backTrack(s,length,i,wordSet,map);
                    for (List<String> newWordBreak:newWordBreaks) {
                        LinkedList<String> wordBreak = new LinkedList<>(newWordBreak);
                        wordBreak.offerFirst(word);
                        wordBreaks.add(wordBreak);
                    }
                }
            }
            map.put(index,wordBreaks);
        }
        return map.get(index);
    }


}
