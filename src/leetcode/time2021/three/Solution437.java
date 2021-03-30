package leetcode.time2021.three;

import leetcode.entity.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyx
 * @date 2021/3/30 11:45
 */
public class Solution437 {


    public int pathSum(TreeNode root, int sum) {
        //key为前缀和，value为出现次数
        Map<Integer,Integer> preMap = new HashMap<>();
        //前缀和为0的一条路径
        preMap.put(0,1);
        return recur(root,preMap,sum,0);
    }
    private int recur(TreeNode node, Map<Integer,Integer> preMap,int target,int curSum){
        if (node == null)   return 0;
        int res = 0;
        curSum += node.val;
        //查找是否有前缀和+target为当前sum的，有的话那个点到起点距离就是target
        res += preMap.getOrDefault(curSum - target,0);
        preMap.put(curSum,preMap.getOrDefault(curSum,0)+1);
        //进入下一层
        res += recur(node.left, preMap, target, curSum);
        res += recur(node.right, preMap, target, curSum);
        //回到本层，恢复状态，去除当前节点的前缀和数量
        preMap.put(curSum,preMap.get(curSum)-1);
        return res;
    }


}
