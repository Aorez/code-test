package com.aorez.leetcode.剑指Offer;

/*
输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
示例 1：
输入：head = [1,3,2]
输出：[2,3,1]
限制：
0 <= 链表长度 <= 10000
 */
public class 剑指Offer06从尾到头打印链表 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    class Solution {
        //递归
        public int[] reversePrint(ListNode head) {
            if (head == null) {
                return new int[]{};
            }

            return putIntoArray(head, 1);
        }

        public int[] putIntoArray(ListNode node, int id) {
            if (node.next == null) {
                int[] array = new int[id];
                array[0] = node.val;
                return array;
            }

            int[] array = putIntoArray(node.next, id + 1);
            array[array.length - id] = node.val;
            return array;
        }
    }
}
