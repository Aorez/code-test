package com.aorez.leetcode.LCR;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/*
https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/description/?envType=study-plan-v2&envId=coding-interviews
简单
书店店员有一张链表形式的书单，每个节点代表一本书，节点中的值表示书的编号。
为更方便整理书架，店员需要将书单倒过来排列，就可以从最后一本书开始整理，逐一将书放回到书架上。
请倒序返回这个书单链表。

示例 1：
输入：head = [3,6,4,1]
输出：[1,4,6,3]

提示：
0 <= 链表长度 <= 10000
 */
public class LCR123图书整理I {
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
    }

    // 递归
    class Solution {
        public int[] reverseBookList(ListNode head) {
            if (head == null) {
                return new int[]{};
            }
            return recursion(head, 1);
        }
        private int[] recursion(ListNode node, int length) {
            int[] result = null;
            if (node.next == null) {
                result = new int[length];
                result[0] = node.val;
            }
            else {
                result = recursion(node.next, length + 1);
                result[result.length - length] = node.val;
            }
            return result;
        }
    }

    // 栈
    class Solution2 {
        public int[] reverseBookList(ListNode head) {
            LinkedList<Integer> stack = new LinkedList<>();
            while (head != null) {
                stack.addLast(head.val);
                head = head.next;
            }
            int[] result = new int[stack.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = stack.removeLast();
            }
            return result;
        }
    }

    @Test
    public void test() {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.reverseBookList(head)));
    }
    @Test
    public void test2() {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
        Solution2 solution = new Solution2();
        System.out.println(Arrays.toString(solution.reverseBookList(head)));
    }

}
