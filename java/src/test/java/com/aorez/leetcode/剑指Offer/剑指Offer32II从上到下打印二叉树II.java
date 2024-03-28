package com.aorez.leetcode.剑指Offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//普通的广度优先搜索每次从队列里取出一个元素
//这里的广度优先搜索每次从队列里取出二叉树一层的元素
public class 剑指Offer32II从上到下打印二叉树II {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> rtn = new ArrayList<>();
            if (root == null) {
                return rtn;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                List<Integer> rtn2 = new ArrayList<>();
                int length = queue.size();
                for (int i = 0; i < length; i++) {
                    TreeNode node = queue.poll();
                    rtn2.add(node.val);
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
                rtn.add(rtn2);
            }

            return rtn;
        }
    }
}