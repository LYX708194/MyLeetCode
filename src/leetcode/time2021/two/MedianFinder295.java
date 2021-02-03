package leetcode.time2021.two;

import java.util.PriorityQueue;

/**
 * 295 数据流的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 *
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lyx
 * @date 2021/2/3 16:55
 */
public class MedianFinder295 {
    //大顶堆和小顶堆个数之和
    private int count;
    //维持前一半，大顶堆
    private PriorityQueue<Integer> maxHeap;
    //维持后一半，小顶堆
    private PriorityQueue<Integer> minHeap;

    /** initialize your data structure here. */
    public MedianFinder295() {
        count = 0;
        maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        //默认为从小到大
        minHeap = new PriorityQueue<>();
    }

    /**
     * 添加数保证，大顶堆最大的数小于小顶堆最小的数
     *          大顶堆元素个数和小顶堆元素个数相等或者多 1
     */
    public void addNum(int num) {
        count += 1;
        maxHeap.add(num);
        minHeap.add(maxHeap.poll());
        // 如果两个堆合起来的元素个数是奇数，小顶堆要拿出堆顶元素给大顶堆，
        if ((count & 1) != 0) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        if ((count & 1) == 0) {
            // 如果两个堆合起来的元素个数是偶数，数据流的中位数就是各自堆顶元素的平均值
            return (double) (maxHeap.peek() + minHeap.peek()) / 2;
        } else {
            // 如果两个堆合起来的元素个数是奇数，数据流的中位数大顶堆的堆顶元素
            return (double) maxHeap.peek();
        }
    }

}
