package com.aorez.leetcode.LCR;

import java.util.HashMap;
import java.util.Map;

/*
请实现 copyRandomList 函数，复制一个复杂链表。
在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。

示例 1：
输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]

示例 2：
输入：head = [[1,1],[2,1]]
输出：[[1,1],[2,1]]

示例 3：
输入：head = [[3,null],[3,0],[3,null]]
输出：[[3,null],[3,0],[3,null]]

示例 4：
输入：head = []
输出：[]
解释：给定的链表为空（空指针），因此返回 null。

提示：
-10000 <= Node.val <= 10000
Node.random 为空（null）或指向链表中的节点。
节点数目不超过 1000 。
 */
public class LCR154复杂链表的复制 {
    class Node {
        int val;
        Node next;
        Node random;
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // 繁琐的HashMap
    class Solution {
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }

            Node h = head;
            Map<Node, Node> nodeMap = new HashMap<>();
            while (head != null) {
                if (!nodeMap.containsKey(head)) {
                    nodeMap.put(head, new Node(head.val));
                }
                if (head.next != null && !nodeMap.containsKey(head.next)) {
                    nodeMap.put(head.next, new Node(head.next.val));
                }
                if (head.random != null && !nodeMap.containsKey(head.random)) {
                    nodeMap.put(head.random, new Node(head.random.val));
                }
                if (head.next != null) {
                    nodeMap.get(head).next = nodeMap.get(head.next);
                }
                if (head.random != null) {
                    nodeMap.get(head).random = nodeMap.get(head.random);
                }
                head = head.next;
            }

            return nodeMap.get(h);
        }
    }

    // 简洁的HashMap
    class Solution2 {
        public Node copyRandomList(Node head) {
            if (head == null) return null;

            Map<Node, Node> nodeMap = new HashMap<>();
            Node node = head;
            while (node != null) {
                nodeMap.put(node, new Node(node.val));
                node = node.next;
            }

            node = head;
            Node cur = null;
            while (node != null) {
                cur = nodeMap.get(node);
                cur.next = nodeMap.get(node.next);
                cur.random = nodeMap.get(node.random);
                node = node.next;
            }

            return nodeMap.get(head);
        }
    }

    // 拼接再拆分
    class Solution3 {
        public Node copyRandomList(Node head) {
            if (head == null) return null;

            // 插入新节点到旧节点之后
            // 1-2-3
            // 1-1-2-2-3-3
            Node node = head;
            Node next = null;
            while (node != null) {
                next = node.next;
                node.next = new Node(node.val);
                node.next.next = next;
                node = next;
            }

            // 处理新节点的random
            node = head;
            while (node != null) {
                if (node.random != null) {
                    node.next.random = node.random.next;
                }
                node = node.next.next;
            }

            // 拆分链表，剔除旧节点
            node = head;
            next = head.next;
            Node result = head.next;
            while (next.next != null) {
                node.next = next.next;
                next.next = next.next.next;
                node = node.next;
                next = next.next;
            }

            // 因为next.next为空的时候退出循环了
            // 所以要再处理node.next
            // 否则会修改到原数组，答案不通过
            node.next = null;
            return result;
        }
    }
}
