package com.aorez.leetcode.剑指Offer;

import java.util.HashSet;
import java.util.Set;

public class 剑指Offer52两个链表的第一个公共节点 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    //HashSet
    class Solution {
        ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            Set<ListNode> nodeA = new HashSet<>();
            ListNode node = headA;
            while (node != null) {
                nodeA.add(node);
                node = node.next;
            }

            node = headB;
            while (node != null) {
                if (nodeA.contains(node)) {
                    return node;
                }
                node = node.next;
            }

            return null;
        }
    }

    //Double pointer
    //一
    //Intersect
    //A: m个
    //B: n个
    //A: a个
    //        c个
    //B: b个
        //1
        //m == n
        //双指针同时移动，最终在相交节点相遇
        //2
        //m != n
        //假设a<b，指针A先到达链表A末尾空指针，继续遍历链表B
        //指针B稍后会到达链表B末尾空指针，继续遍历链表A
        //接着两个指针会在相交节点相遇，因为两个指针都移动了a+c+b（或b+c+a）个节点
    //二
    //Non-intersect
    //1
    //m == n
    //2
    //m != n
    //两个指针最终都到达空指针，因为都移动了m+n（或n+m）个节点
    class Solution2 {
        ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }

            ListNode nodeA = headA, nodeB = headB;
            while (true) {
                if (nodeA == nodeB) {
                    return nodeA;
                }
                if (nodeA == null && nodeB == null) {
                    return null;
                }
                if (nodeA == null) {
                    nodeA = headB;
                }
                else {
                    nodeA = nodeA.next;
                }
                if (nodeB == null) {
                    nodeB = headA;
                }
                else {
                    nodeB = nodeB.next;
                }

                //If get next node in advance, may miss the correct answer
                //Linked list A, a node, [3]
                //Linked list B, a node, [3]
                //Will run forward to both null in advance, miss the correct answer (both node "3")
//                nodeA = nodeA.next;
//                nodeB = nodeB.next;
            }
        }
    }

    class Solution3 {
        ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }

            ListNode nodeA = headA, nodeB = headB;
            while (nodeA != nodeB) {
                nodeA = nodeA == null? headB: nodeA.next;
                nodeB = nodeB == null? headA: nodeB.next;
            }

            return nodeA;
        }
    }
}
