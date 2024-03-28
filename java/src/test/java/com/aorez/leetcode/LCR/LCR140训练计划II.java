package com.aorez.leetcode.LCR;

import org.junit.Test;

/*
https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/description/?envType=study-plan-v2&envId=coding-interviews
简单
给定一个头节点为 head 的链表用于记录一系列核心肌群训练项目编号，请查找并返回倒数第 cnt 个训练项目编号。

示例 1：
输入：head = [2,4,7,8], cnt = 1
输出：8

提示：
1 <= head.length <= 100
0 <= head[i] <= 100
1 <= cnt <= head.length
 */
public class LCR140训练计划II {
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
            ListNode n = this;
            while (n != null) {
                System.out.print(n.val + " ");
                n = n.next;
            }
        }
    }

    // 双指针
    class Solution {
        public ListNode trainingPlan(ListNode head, int cnt) {
            ListNode right = head;
            for (int i = 0; i < cnt; i++) {
                right = right.next;
            }

            while (right != null) {
                head = head.next;
                right = right.next;
            }

            return head;
        }
    }

    @Test
    public void test() {
        new Solution().trainingPlan(new ListNode(1, new ListNode(2, new ListNode(3))), 1).print();
        System.out.println();
        new Solution().trainingPlan(new ListNode(1, new ListNode(2, new ListNode(3))), 2).print();
    }
}
