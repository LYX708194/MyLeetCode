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

    //维持前一半，大顶堆
    private PriorityQueue<Integer> maxHeap;
    //维持后一半，小顶堆
    private PriorityQueue<Integer> minHeap;

    /** initialize your data structure here. */
    public MedianFinder295() {
        //大顶堆，存较小的数
        maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        //默认为从小到大，存较大的数
        minHeap = new PriorityQueue<>();
    }

    /**
     * 保证 maxHeap <= minHeap,minHeap最多只比maxHeap多1
     */
    public void addNum(int num) {
        if (maxHeap.size() != minHeap.size()){
            //minHeap较多，先进小顶堆，再取最小的放进大顶堆
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
        }else{
            //两者一样答，先放大顶堆，取最大的放进小顶堆
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        }
    }

    public double findMedian() {
        return maxHeap.size() != minHeap.size() ? minHeap.peek() : (minHeap.peek() + maxHeap.peek()) / 2.0;
    }

}
