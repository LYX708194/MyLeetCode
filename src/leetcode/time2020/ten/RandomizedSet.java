package leetcode.time2020.ten;

import java.util.*;

/**
 *
 * 我们想想如何实现它。从 insert 开始，我们具有两个平均插入时间为 \mathcal{O}(1)O(1) 的选择：
 *
 * 哈希表：Java 中为 HashMap，Python 中为 dictionary。
 * 动态数组：Java 中为 ArrayList，Python 中为 list。
 * 让我们一个个进行思考，虽然哈希表提供常数时间的插入和删除，但是实现 getRandom 时会出现问题。
 *
 * getRandom 的思想是选择一个随机索引，然后使用该索引返回一个元素。而哈希表中没有索引，因此要获得真正的随机值，则要将哈希表中的键转换为列表，这需要线性时间。解决的方法是用一个列表存储值，并在该列表中实现常数时间的 getRandom。
 *
 * 列表有索引可以实现常数时间的 insert 和 getRandom，则接下来的问题是如何实现常数时间的 remove。
 *
 * 删除任意索引元素需要线性时间，这里的解决方案是总是删除最后一个元素。
 * 将要删除元素和最后一个元素交换。
 * 将最后一个元素删除。
 * 为此，必须在常数时间获取到要删除元素的索引，因此需要一个哈希表来存储值到索引的映射。
 *
 * 综上所述，我们使用以下数据结构：
 *
 *      动态数组存储元素值
 *      哈希表存储存储值到索引的映射。
 *
 * 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1/solution/chang-shu-shi-jian-cha-ru-shan-chu-he-huo-qu-sui-j/
 * @author lyx
 * @date 2020/10/31 11:41
 */
public class RandomizedSet {

    Map<Integer, Integer> map;
    List<Integer> list;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)){
            return false;
        }
        map.put(val,list.size());
        list.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)){
            return false;
        }
        int lastNum = list.get(list.size()-1);
        int index = map.get(val);
        list.set(index,lastNum);
        map.put(lastNum,index);
        list.remove(list.size()-1);
        map.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get((int) (Math.random()*list.size()));
    }

}
