package com.aorez.leetcode.剑指Offer;

/*
输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
示例：
给定一个链表: 1->2->3->4->5, 和 k = 2.
返回链表 4->5.
 */
public class 剑指Offer22链表中倒数第k个节点 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    class Solution {
        //双指针
        public ListNode getKthFromEnd(ListNode head, int k) {
            //Only one node in linked list and to remove the last node
            if (head.next == null && k == 1) {
                return head;
            }

            ListNode first = head, second = head;
            for (int i = 0; i < k; i++) {
                second = second.next;
            }

            while (second != null) {
                first = first.next;
                second = second.next;
            }

            return first;
        }
    }
}
