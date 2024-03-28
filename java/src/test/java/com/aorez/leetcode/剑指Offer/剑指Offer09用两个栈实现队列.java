package com.aorez.leetcode.剑指Offer;

import java.util.ArrayDeque;
import java.util.Deque;

/*
用两个栈实现一个队列。队列的声明如下，
请实现它的两个函数 appendTail 和 deleteHead ，
分别完成在队列尾部插入整数和在队列头部删除整数的功能。
(若队列中没有元素，deleteHead 操作返回 -1 )
示例 1：
输入：
["CQueue","appendTail","deleteHead","deleteHead","deleteHead"]
[[],[3],[],[],[]]
输出：[null,null,3,-1,-1]
示例 2：
输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]
提示：
1 <= values <= 10000
最多会对 appendTail、deleteHead 进行 10000 次调用
 */
public class 剑指Offer09用两个栈实现队列 {
    class CQueue {
        //入队相当于栈1的push
        //出队是栈2的pop，如果栈2为空，则将栈1内容按顺序转移到栈2，再进行pop
        //如果仍然为空，则返回-1
        Deque<Integer> stackAppend;
        Deque<Integer> stackDelete;

        public CQueue() {
            stackAppend = new ArrayDeque<>();
            stackDelete = new ArrayDeque<>();
        }

        public void appendTail(int value) {
            stackAppend.push(value);
        }

        public int deleteHead() {
            if (stackDelete.isEmpty()) {
                while (!stackAppend.isEmpty()) {
                    stackDelete.push(stackAppend.pop());
                }
            }

            return stackDelete.isEmpty()? -1 : stackDelete.pop();
        }
    }
}