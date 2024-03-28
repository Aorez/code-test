package com.aorez.leetcode.剑指Offer;

import java.util.HashMap;
import java.util.Map;

public class 剑指Offer35复杂链表的复制 {
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

    class Solution {
        Map<Node, Node> nodeCache = new HashMap<>();

        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }

            if (nodeCache.containsKey(head)) {
                return nodeCache.get(head);
            }

            Node newNode = new Node(head.val);
            nodeCache.put(head, newNode);
            newNode.next = copyRandomList(head.next);
            newNode.random = copyRandomList(head.random);
            return newNode;
        }
    }
}
