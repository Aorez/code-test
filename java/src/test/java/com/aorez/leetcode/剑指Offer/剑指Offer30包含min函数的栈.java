package com.aorez.leetcode.剑指Offer;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/*
定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
调用 min、push 及 pop 的时间复杂度都是 O(1)。
示例:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.min();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.min();   --> 返回 -2.
提示：
各函数的调用总次数不超过 20000 次
 */
public class 剑指Offer30包含min函数的栈 {
    class MinStack {
        //min push pop都为O(1)
        //push一个比min大的数，则min栈入栈min值
        //push一个比min小的数，则min栈入栈该数
        Deque<Integer> stack;
        Deque<Integer> min;

        public MinStack() {
            stack = new ArrayDeque<>();
            min = new ArrayDeque<>();
        }

        public void push(int x) {
            stack.push(x);
            if (min.isEmpty()) {
                min.push(x);
            }
            else if (x < min.peek()) {
                min.push(x);
            }
            else {
                min.push(min.peek());
            }
        }

        public void pop() {
            stack.pop();
            min.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return min.peek();
        }
    }

    @Test
    public void test() {
        MinStack minStack = new MinStack();
        minStack.push(2);
        System.out.println(minStack.min());
        minStack.push(1);
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.min());
    }
}
