package com.aorez.leetcode.剑指Offer;

/*
定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
示例:
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
限制：
0 <= 节点个数 <= 5000
 */
public class 剑指Offer24反转链表 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    class Solution {
        //递归
        //1 > 2 > 3 > 4 > null
        //3 > 4 > null
        //4 > 3
        //3 > 2
        //2 > 1
        //1 > null
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode newHead = reverse(head);
            head.next = null;
            return newHead;
        }

        public ListNode reverse(ListNode node) {
            if (node.next.next == null) {
                node.next.next = node;
                return node.next;
            }

            ListNode head = reverse(node.next);
            node.next.next = node;
            return head;
        }
    }
}
