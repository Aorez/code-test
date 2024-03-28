package com.aorez.leetcode.LCR;

import org.junit.Test;

/*
https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/description/?envType=study-plan-v2&envId=coding-interviews
简单
给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
返回删除后的链表的头节点。

示例 1:
输入: head = [4,5,1,9], val = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.

示例 2:
输入: head = [4,5,1,9], val = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.

说明：
题目保证链表中节点的值互不相同
若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点
 */
public class LCR136删除链表的节点 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
        ListNode(int x, ListNode next) {
            this.val = x;
            this.next = next;
        }
        public void print() {
            ListNode node = this;
            while (node != null) {
                System.out.print(node.val + " ");
                node = node.next;
            }
        }
    }

    // 遍历
    class Solution {
        public ListNode deleteNode(ListNode head, int val) {
            if (head == null) return null;
            else if (head.val == val) return head.next;

            ListNode h = head;
            while (head.next != null) {
                if (head.next.val == val) {
                    head.next = head.next.next;
                    break;
                }
                head = head.next;
            }

            return h;
        }
    }

    @Test
    public void test() {
        new Solution().deleteNode(new ListNode(1, new ListNode(2, new ListNode(3))), 2).print();
    }
}
