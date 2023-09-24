package com.bobking.leetcode.training;

public class Number622 {

    // 参考：https://leetcode.cn/problems/design-circular-queue/solution/shu-zu-shi-xian-de-xun-huan-dui-lie-by-liweiwei141/
    private class MyCircularQueue {

        // 定义循环变量 front 和 rear 一直保持这个定义，到底是先赋值还是先移动指针就很容易想清楚了
        // front：指向队列头部第 1 个有效数据的位置
        // rear：指向队列尾部（即最后 1 个有效数据）的下一个位置，即下一个从队尾入队元素的位置
        // （说明：这个定义是依据“动态数组”的定义模仿而来。）
        //
        // 为了避免「队列为空」和「队列为满」的判别条件冲突，有意浪费了一个位置
        // 浪费一个位置是指：循环数组中任何时刻一定至少有一个位置不存放有效元素
        //
        // 判别队列为空的条件是：front == rear
        // 判别队列为满的条件是：(rear + 1) % capacity == front
        // 可以这样理解，当 rear 循环到数组的前面，要从后面追上 front，还差一格的时候，判定队列为满
        // 因为有循环的出现，要特别注意处理数组下标可能越界的情况。指针后移的时候，下标 + 1，并且为了防止数组下标越界要取模

        private int front;
        private int rear;
        private int capacity;
        private int[] arr;

        /**
         * Initialize your data structure here. Set the size of the queue to be k.
         */
        public MyCircularQueue(int k) {
            capacity = k + 1;
            arr = new int[capacity];

            // 在 front 出队，故设计在数组的头部，方便删除元素
            // 删除元素的时候，只索引 + 1（注意取模）

            // 在 rear 入队，故设计在数组的尾部，方便插入元素
            // 插入元素的时候，先赋值，后索引 + 1（注意取模）
            front = 0;
            rear = 0;
        }

        /**
         * Insert an element into the circular queue. Return true if the operation is successful.
         */
        public boolean enQueue(int value) {

            if (isFull())
                return false;

            arr[rear] = value;
            rear = (rear + 1) % capacity;
            return true;
        }

        /**
         * Delete an element from the circular queue. Return true if the operation is successful.
         */
        public boolean deQueue() {

            if (isEmpty())
                return false;

            front = (front + 1) % capacity;
            return true;
        }

        /**
         * Get the front item from the queue.
         */
        public int Front() {

            if (isEmpty())
                return -1;

            return arr[front];
        }

        /**
         * Get the last item from the queue.
         */
        public int Rear() {

            if (isEmpty())
                return -1;

            return arr[(rear - 1 + capacity) % capacity];
        }

        /**
         * Checks whether the circular queue is empty or not.
         */
        public boolean isEmpty() {
            return front == rear;
        }

        /**
         * Checks whether the circular queue is full or not.
         */
        public boolean isFull() {
            // 注意：这是这个经典设计的原因
            return (rear + 1) % capacity == front;
        }
    }
}
