package com.aorez.leetcode.剑指Offer;

import java.util.*;

//奇数层从左到右输出
//偶数层从右到左输出
//一般的想法是在遍历树的时候交替顺序进行遍历
//但是solution中是暂存交替输出后输出的内容，最后放回结果集中
public class 剑指Offer32III从上到下打印二叉树III {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> rtn = new ArrayList<>();
            if (root == null) {
                return rtn;
            }

            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            boolean offerLast = true;
            while (!queue.isEmpty()) {
                int length = queue.size();
                Deque<Integer> deque = new LinkedList<>();
                for (int i = 0; i < length; i++) {
                    TreeNode node = queue.poll();
                    if (offerLast) {
                        deque.offerLast(node.val);
                    }
                    else {
                        deque.offerFirst(node.val);
                    }

                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
                offerLast = !offerLast;
                rtn.add((List<Integer>) deque);
            }

            return rtn;
        }
    }
}
