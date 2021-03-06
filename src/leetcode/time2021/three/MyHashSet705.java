package leetcode.time2021.three;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 705 设计哈希集合
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 *
 * 实现 MyHashSet 类：
 *
 * void add(key) 向哈希集合中插入值 key 。
 * bool contains(key) 返回哈希集合中是否存在这个值 key 。
 * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 *  
 * 示例：
 *
 * 输入：
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * 输出：
 * [null, null, null, true, false, null, true, null, false]
 *
 * 解释：
 * MyHashSet myHashSet = new MyHashSet();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // 返回 True
 * myHashSet.contains(3); // 返回 False ，（未找到）
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // 返回 True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // 返回 False ，（已移除）
 *  
 *
 * 提示：
 *
 * 0 <= key <= 106
 * 最多调用 104 次 add、remove 和 contains 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-hashset
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/3/13 12:22
 */
public class MyHashSet705 {

    private static final int BASE = 769;
    private LinkedList[] data;


    public MyHashSet705() {
        this.data = new LinkedList[BASE];
    }

    public void add(int key) {
        int h = hash(key);
        if (null == data[h]){
            data[h] = new LinkedList();
        }
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()){
            Integer num = iterator.next();
            if (num == key) return ;
        }
        data[h].addLast(key);
    }

    public void remove(int key) {
        int h = hash(key);
        if (null == data[h])    return ;
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()){
            Integer num = iterator.next();
            if (num == key){
                data[h].remove(num);
                return ;
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int h = hash(key);
        if (null == data[h])    return false;
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()){
            Integer num = iterator.next();
            if (num == key) return true;
        }
        return false;
    }
    private int hash(int key){
        //模数的因子会影响数列的冲突，而且因子越多，冲突的可能性就越大。而素数的因子恰好只有1和其本身,就非常适合用于解决冲突。
        return key % BASE;
    }


}
