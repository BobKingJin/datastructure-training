package com.bobking.leetcode.training;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author BobKing
 * @create 2022-04-30 17:32
 */
public class Number295 {

    private class MaxHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            if (o2 > o1) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    private class MinHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            if (o2 < o1) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    // 参考：程序猿代码指南P518
    private class MedianFinder {

        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;

        public MedianFinder() {
            maxHeap = new PriorityQueue<Integer>(new MaxHeapComparator());
            minHeap = new PriorityQueue<Integer>(new MinHeapComparator());
        }

        public void addNum(int num) {

            if (this.maxHeap.isEmpty()) {
                this.maxHeap.add(num);
                return;
            }

            if (this.maxHeap.peek() >= num) {
                this.maxHeap.add(num);
            } else {
                if (this.minHeap.isEmpty()) {
                    this.minHeap.add(num);
                    return;
                }

                if (this.minHeap.peek() > num) {
                    this.maxHeap.add(num);
                } else {
                    this.minHeap.add(num);
                }
            }

            modifyTwoHeapsSize();
        }

        private void modifyTwoHeapsSize() {

            if (this.maxHeap.size() == this.minHeap.size() + 2)
                this.minHeap.add(this.maxHeap.poll());

            if (this.minHeap.size() == this.maxHeap.size() + 2)
                this.maxHeap.add(this.minHeap.poll());
        }

        public double findMedian() {

            int maxHeapSize = this.maxHeap.size();
            int minHeapSize = this.minHeap.size();

            if (maxHeapSize + minHeapSize == 0)
                return 0.0;

            Integer maxHeapHead = this.maxHeap.peek();
            Integer minHeapHead = this.minHeap.peek();

            if (((maxHeapSize + minHeapSize) & 1) == 0)
                return (maxHeapHead + minHeapHead) / 2;

            return maxHeapSize > minHeapSize ? maxHeapHead : minHeapHead;
        }
    }
}
