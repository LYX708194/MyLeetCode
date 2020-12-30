package leetcode.time2020.twelve;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1046 最后一块石头的重量
 * 有一堆石头，每块石头的重量都是正整数。
 *
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 *
 *  
 *
 * 示例：
 *
 * 输入：[2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
 * 再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
 * 接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
 * 最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。
 *  
 *
 * 提示：
 *
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/last-stone-weight
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2020/12/30 10:38
 */
public class Solution1046 {

    public int lastStoneWeight(int[] stones) {
        if(stones.length == 1)  return stones[0];
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
        for (int stone : stones) {
            queue.offer(stone);
        }
        while (queue.size() >= 2){
            int stone1 = queue.poll();
            int stone2 = queue.poll();
            if (stone1 != stone2){
                queue.offer(stone1-stone2);
            }
        }
        return queue.isEmpty() ? 0 :queue.peek();
    }


    public int lastStoneWeight2(int[] stones) {
        MyHeap<Integer> myHeap = new MyHeap<>(stones.length, (a, b) -> b - a);
        for (int stone : stones)
            myHeap.add(stone);
        int distant = 0;
        while (myHeap.getSize() > 1)
            if ((distant = myHeap.remove() - myHeap.remove()) > 0)
                myHeap.add(distant);
        return myHeap.getSize() == 0 ? 0 : myHeap.remove();
    }

    public class MyHeap<E> {
        private Object[] data;//数据存放区
        private int size;//堆的大小
        private Comparator<? super E> comparator;//比较器

        public MyHeap(int initialCapacity) {
            this(initialCapacity, null);
        }

        public MyHeap(int initialCapacity, Comparator<? super E> comparator) {
            if (initialCapacity < 1)
                throw new IllegalArgumentException("堆的大小必须大于0");
            this.data = new Object[initialCapacity];
            this.comparator = comparator;
        }

        /**
         * @param e 向堆中添加元素
         * @return
         */
        public boolean add(E e) {
            if (e == null)//不能为空
                throw new NullPointerException();
            if (size >= data.length)//如果堆的空间不够了就扩容，这里是扩大2倍
                data = Arrays.copyOf(data, data.length << 1);
            if (size == 0)//如果堆是空的，直接添加就可以了，不需要调整，因为就一个没法调整
                data[0] = e;
            else//如果堆不是空的，就要往上调整。
                siftUp(e);
            size++;//添加完之后size要加1
            return true;
        }

        public int getSize() {
            return size;
        }

        //删除堆顶元素
        public E remove() {
            if (size == 0)
                return null;
            size--;
            E result = (E) data[0];//获取堆顶的元素
            E x = (E) data[size];//取出数组的最后一个元素
            data[size] = null;//然后把最后一个元素的位置置空
            if (size != 0)
                siftDown(x);//这里实际上是把数组的最后一个元素取出放到堆顶，然后再往下调整。
            return result;
        }

        //访问堆顶元素，不删除
        public E peek() {
            return (size == 0) ? null : (E) data[0];
        }

        /**
         * 返回数组的值
         *
         * @param a
         * @param <T>
         * @return
         */
        public <T> T[] toArray(T[] a) {
            if (a.length < size)
                return (T[]) Arrays.copyOf(data, size, a.getClass());
            System.arraycopy(data, 0, a, 0, size);
            if (a.length > size)
                a[size] = null;
            return a;
        }

        /**
         * 往上调整，往上调整只需要和父节点比较即可，如果比父节点大就不需要在调整
         *
         * @param e
         */
        private void siftUp(E e) {
            int s = size;
            while (s > 0) {
                int parent = (s - 1) >>> 1;//根据子节点的位置可以找到父节点的位置
                Object pData = data[parent];
                //和父节点比较，如果比父节点大就退出循环不再调整
                if (comparator != null) {
                    if (comparator.compare(e, (E) pData) >= 0)
                        break;
                } else {
                    if (((Comparable<? super E>) e).compareTo((E) pData) >= 0)
                        break;
                }
                //如果比父节点小，就和父节点交换，然后再继续往上调整
                data[s] = pData;
                s = parent;
            }
            //通过上面的往上调整，找到合适的位置，再把e放进去
            data[s] = e;
        }

        /**
         * 往下调整，往下调整需要和他的两个子节点（如果有两个子节点）都要比较，哪个最小就和哪
         * 个交换，如果比两个子节点都小就不用再交换
         *
         * @param e
         */
        private void siftDown(E e) {
            int half = size >>> 1;
            int index = 0;
            while (index < half) {
                int min = (index << 1) + 1;//根据父节点的位置可以找到左子节点的位置
                Object minChild = data[min];
                int right = min + 1;//根据左子节点找到右子节点的位置
                if (right < size) {//如果有右子节点就执行这里的代码
                    //如果有右子节点，肯定会有左子节点。那么就需要左右两个子节点比较，把小的赋值给minChild
                    if (comparator != null) {
                        if (comparator.compare((E) minChild, (E) data[right]) > 0)
                            minChild = data[min = right];
                    } else {
                        if (((Comparable<? super E>) minChild).compareTo((E) data[right]) > 0)
                            minChild = data[min = right];
                    }
                }
                //用节点e和他的最小的子节点比较，如果小于他最小的子节点就退出循环，不再往下调整了，
                if (comparator != null) {
                    if (comparator.compare(e, (E) minChild) <= 0)
                        break;
                } else {
                    if (((Comparable<? super E>) e).compareTo((E) minChild) <= 0)
                        break;
                }
                //如果e比它的最小的子节点小，就用最小的子节点和e交换位置，然后再继续往下调整。
                data[index] = minChild;
                index = min;
            }
            data[index] = e;
        }
    }

}
