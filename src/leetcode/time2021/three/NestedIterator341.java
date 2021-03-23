package leetcode.time2021.three;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author lyx
 * @date 2021/3/23 10:29
 */
public class NestedIterator341  implements Iterator<Integer> {

    //存储列表的当前遍历位置
    private Stack<Iterator<NestedInteger>> stack;

    public NestedIterator341(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return stack.peek().next().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()){
            Iterator<NestedInteger> it = stack.peek();
            if (!it.hasNext()){
                stack.pop();
                continue;
            }
            NestedInteger nest = it.next();
            // 若取出的元素是整数，则通过创建一个额外的列表将其重新放入栈中
            if (nest.isInteger()){
                List<NestedInteger> list = new ArrayList<>();
                list.add(nest);
                stack.push(list.iterator());
                return true;
            }
            //直接把这个列表放进栈中
            stack.push(nest.getList().iterator());
        }
        return false;
    }

    private interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();
        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();
        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
   }


}
