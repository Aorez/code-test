package com.aorez.leetcode.LCR;

import org.junit.Test;

/*
https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/description/?envType=study-plan-v2&envId=coding-interviews
简单
给定一个头节点为 head 的单链表用于记录一系列核心肌群训练编号，请将该系列训练编号 倒序 记录于链表并返回。

示例 1：
输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]

示例 2：
输入：head = [1,2]
输出：[2,1]

示例 3：
输入：head = []
输出：[]

提示：
链表中节点的数目范围是 [0, 5000]
-5000 <= Node.val <= 5000
 */
public class LCR141训练计划III {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
        public void print() {
            ListNode cur = this;
            while (cur != null) {
                System.out.print(cur.val + " ");
                cur = cur.next;
            }
        }
    }

    // 迭代
    // 链表方向颠倒，遍历时一直交换
    class Solution {
        public ListNode trainningPlan(ListNode head) {
            ListNode pre = null;
            ListNode next = null;
            while (head != null) {
                next = head.next;
                head.next = pre;
                pre = head;
                head = next;
            }
            return pre;
        }
    }

    // 递归
    class Solution2 {
        public ListNode trainningPlan(ListNode head) {
            if (head == null) {
                return null;
            }
            return recur(null, head);
        }
        private ListNode recur(ListNode pre, ListNode node) {
            if (node.next == null) {
                node.next = pre;
                return node;
            }
            else {
                ListNode head = recur(node, node.next);
                node.next = pre;
                return head;
            }
        }
    }

    @Test
    public void test() {
        new Solution().trainningPlan(new ListNode(1, new ListNode(2, new ListNode(3)))).print();
    }
    @Test
    public void test2() {
        new Solution2().trainningPlan(new ListNode(1, new ListNode(2, new ListNode(3)))).print();
    }
}
