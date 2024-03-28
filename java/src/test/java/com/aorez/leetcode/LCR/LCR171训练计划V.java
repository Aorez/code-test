package com.aorez.leetcode.LCR;

import org.junit.Test;

/*
https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/description/?envType=study-plan-v2&envId=coding-interviews
https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
简单
某教练同时带教两位学员，分别以链表 l1、l2 记录了两套核心肌群训练计划，节点值为训练项目编号。
两套计划仅有前半部分热身项目不同，后续正式训练项目相同。
请设计一个程序找出并返回第一个正式训练项目编号。
如果两个链表不存在相交节点，返回 null 。

如下面的两个链表：
   a1-a2-
          \
          |----c1-c2-c3
          /
b1-b2-b3-
在节点 c1 开始相交。
输入说明：
intersectVal - 相交的起始节点的值。如果不存在相交节点，这一值为 0
l1 - 第一个训练计划链表
l2 - 第二个训练计划链表
skip1 - 在 l1 中（从头节点开始）跳到交叉节点的节点数
skip2 - 在 l2 中（从头节点开始）跳到交叉节点的节点数

程序将根据这些输入创建链式数据结构，并将两个头节点 head1 和 head2 传递给你的程序。
如果程序能够正确返回相交节点，那么你的解决方案将被视作正确答案 。

示例 1：
  4-1-
      \
      |---8-4-5
      /
5-0-1-
输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
解释：第一个正式训练项目编号为 8 （注意，如果两个列表相交则不能为 0）。
从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。

示例 2：
0-9-1-
      \
      |---2-4
      /
    3-
输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Reference of the node with value = 2
解释：第一个正式训练项目编号为 2 （注意，如果两个列表相交则不能为 0）。
从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。
在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。

示例 3：
A: 2-6-4
B:   1-5
输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
解释：两套计划完全不同，返回 null。
从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。

注意：
如果两个链表没有交点，返回 null.
在返回结果后，两个链表仍须保持原有的结构。
可假定整个链表结构中没有循环。
程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */
public class LCR171训练计划V {
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
            System.out.println(val);
        }
    }

    // 双指针
    // a+c+b=b+c+a
    class Solution {
        ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }

            ListNode curA = headA, curB = headB;
            while (curA != curB) {
                if (curA == null) curA = headB;
                else curA = curA.next;

                if (curB == null) curB = headA;
                else curB = curB.next;
            }

            return curA;
        }
    }

/*
  4-1-
      \
      |---8-4-5
      /
5-0-1-
*/
    @Test
    public void test() {
        ListNode c = new ListNode(8, new ListNode(4, new ListNode(5)));
        ListNode a = new ListNode(4, new ListNode(1, c));
        ListNode b = new ListNode(5, new ListNode(0, new ListNode(1, c)));
        new Solution().getIntersectionNode(a, b).print();
    }

    @Test
    public void test2() {
        ListNode a = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode b = new ListNode(4, new ListNode(5, new ListNode(6)));
        new Solution().getIntersectionNode(a, b).print();
    }
}
