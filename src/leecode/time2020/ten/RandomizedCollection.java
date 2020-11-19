package leecode.time2020.ten;

import java.util.*;

/**
 *
 *
 * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 *
 * 注意: 允许出现重复元素。
 *
 * insert(val)：向集合中插入元素 val。
 * remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 *
 * 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed
 *
 * @author lyx
 * @date 2020/10/31 11:01
 */
public class RandomizedCollection {

    Map<Integer, Set<Integer>> map;
    List<Integer> list;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        list.add(val);
        Set<Integer> set = map.getOrDefault(val,new HashSet<Integer>());
        set.add(list.size()-1);
        map.put(val,set);
        return set.size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)){
            return false;
        }
        Iterator<Integer> it = map.get(val).iterator();
        int i = it.next();
        int lastNum = list.get(list.size()-1);
        list.set(i,lastNum);
        map.get(val).remove(i);
        map.get(val).remove(list.size()-1);
        if ( i < list.size()-1){
            map.get(lastNum).add(i);
        }
        if ( map.get(val).size() == 0){
            map.remove(val);
        }
        list.remove(list.size()-1);
        return true;
    }

    /**
     if (map.get(val) == null || map.get(val).size() == 0){
     //如果不存在就不需要删除了
     return true;
     }else{
     int left = map.get(val).iterator().next();
     int right = list.size() - 1;
     int rightVal = list.get(right);
     //交换数组当前位置和最后一个位置的数，同时交换hash表的位置
     swapList(left,right);
     swapMap(left,val,right,rightVal);
     list.remove(list.size()-1);
     map.get(val).remove(right);
     return true;
     }
     */

    public void swapList(int left,int right){
        int t = list.get(left);
        list.set(left,list.get(right));
        list.set(right,t);
    }

    public void swapMap(int left,int leftVal,int right, int rightVal){
        map.get(leftVal).remove(left);
        map.get(leftVal).add(right);
        map.get(rightVal).remove(right);
        map.get(rightVal).add(left);
    }


    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get((int) (Math.random()*list.size()));
    }


}
