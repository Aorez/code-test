package com.aorez.leetcode.剑指Offer;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class 剑指Offer59II队列的最大值 {
    //Humdrum queue
    class MaxQueue {
        Deque<Integer> humdrum;
        Deque<Integer> queue;

        public MaxQueue() {
            humdrum = new LinkedList<>();
            queue = new LinkedList<>();
        }

        public int max_value() {
            return humdrum.isEmpty() ? -1 : humdrum.peekFirst();
        }

        public void push_back(int value) {
            queue.offer(value);
            while (!humdrum.isEmpty() && humdrum.peekLast() < value) {
                humdrum.pollLast();
            }
            humdrum.offerLast(value);
        }

        public int pop_front() {
            if (queue.isEmpty()) {
                return -1;
            }
            if (humdrum.peekFirst().equals(queue.peek())) {
                humdrum.pollFirst();
            }
            return queue.poll();
        }
    }

    //push 2
    //queue 2
    //humdrum 2
    //
    //push 2
    //queue 2 2
    //humdrum 2 2
    //
    //push 1
    //queue 1 2 2
    //humdrum 1 2 2
    //因为本质是队列，所以不会出错
    //如果是栈，先加入22，最后加入1，那1不应该在humdrum中
    //因为如果queue pop 1，没有humdrum pop 1
    //但是这是队列，1不会在两个2之前出队
    @Test
    public void test() {
        MaxQueue maxQueue = new MaxQueue();
        System.out.println("push 1");
        maxQueue.push_back(1);
        System.out.println("max: " + maxQueue.max_value());

        System.out.println("push 2");
        maxQueue.push_back(2);
        System.out.println("push 2");
        maxQueue.push_back(2);
        System.out.println("max: " + maxQueue.max_value());

        System.out.println("push 1");
        maxQueue.push_back(1);
    }
}