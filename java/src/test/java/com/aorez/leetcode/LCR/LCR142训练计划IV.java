package com.aorez.leetcode.LCR;

import org.junit.Test;

/*
https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/description/?envType=study-plan-v2&envId=coding-interviews
https://leetcode-cn.com/problems/merge-two-sorted-lists/
简单
给定两个以 有序链表 形式记录的训练计划 l1、l2，分别记录了两套核心肌群训练项目编号，请合并这两个训练计划，按训练项目编号 升序 记录于链表并返回。
注意：新链表是通过拼接给定的两个链表的所有节点组成的。

示例 1：
输入：l1 = [1,2,4], l2 = [1,3,4]
输出：[1,1,2,3,4,4]

示例 2：
输入：l1 = [], l2 = []
输出：[]

示例 3：
输入：l1 = [], l2 = [0]
输出：[0]

提示：
0 <= 链表长度 <= 1000
 */
public class LCR142训练计划IV {
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
        public ListNode trainningPlan(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            else if (l2 == null) return l1;

            ListNode h = null;
            if (l1.val < l2.val) {
                h = l1;
                l1 = l1.next;
            }
            else {
                h = l2;
                l2 = l2.next;
            }
            ListNode cur = h;

            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    cur.next = l1;
                    l1 = l1.next;
                }
                else {
                    cur.next = l2;
                    l2 = l2.next;
                }
                cur = cur.next;
            }

            // 不可能两个都等于null
            if (l1 != null) {
                cur.next = l1;
            }
            else if (l2 != null) {
                cur.next = l2;
            }

            return h;
        }
    }

    @Test
    public void test() {
        new Solution().trainningPlan(new ListNode(1, new ListNode(3, new ListNode(5))),
                new ListNode(2, new ListNode(4, new ListNode(6)))).print();
    }
}
