package com.bobking.leetcode.training;

public class Number641 {

    // 参考：https://leetcode.cn/problems/design-circular-deque/solution/shu-zu-shi-xian-de-xun-huan-shuang-duan-dui-lie-by/
    private class MyCircularDeque {

        // 1、不用设计成动态数组，使用静态数组即可
        // 2、设计 head 和 tail 指针变量
        // 3、head == tail 成立的时候表示队列为空
        // 4、tail + 1 == head

        private int capacity;
        private int[] arr;
        private int front;
        private int rear;

        public MyCircularDeque(int k) {
            capacity = k + 1;
            arr = new int[capacity];

            // 头部指向第 1 个存放元素的位置
            // 插入时，先减，再赋值
            // 删除时，索引 + 1（注意取模）
            front = 0;
            // 尾部指向下一个插入元素的位置
            // 插入时，先赋值，再加
            // 删除时，索引 - 1（注意取模）
            rear = 0;
        }

        public boolean insertFront(int value) {

            if (isFull())
                return false;

            front = (front - 1 + capacity) % capacity;
            arr[front] = value;
            return true;
        }

        public boolean insertLast(int value) {

            if (isFull())
                return false;

            arr[rear] = value;
            rear = (rear + 1) % capacity;
            return true;
        }

        public boolean deleteFront() {

            if (isEmpty())
                return false;

            // front 被设计在数组的开头，所以是 + 1
            front = (front + 1) % capacity;
            return true;
        }

        public boolean deleteLast() {

            if (isEmpty())
                return false;

            // rear 被设计在数组的末尾，所以是 - 1
            rear = (rear - 1 + capacity) % capacity;
            return true;
        }

        public int getFront() {

            if (isEmpty())
                return -1;

            return arr[front];
        }

        public int getRear() {

            if (isEmpty())
                return -1;

            // 当 rear 为 0 时防止数组越界
            return arr[(rear - 1 + capacity) % capacity];
        }

        public boolean isEmpty() {
            return front == rear;
        }

        public boolean isFull() {
            // 注意：这个设计是非常经典的做法
            return (rear + 1) % capacity == front;
        }
    }
}
