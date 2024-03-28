package com.aorez.leetcode.剑指Offer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
请完成一个函数，输入一个二叉树，该函数输出它的镜像。
例如输入：
     4
   /   \
  2     7
 / \   / \
1   3 6   9
镜像输出：
     4
   /   \
  7     2
 / \   / \
9   6 3   1
示例 1：
输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]
限制：
0 <= 节点个数 <= 1000
 */
public class 剑指Offer27二叉树的镜像 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class Solution {
        /*
        1
        2 3
        4 5 6 7
        8 9 a b c d e f

        1
        3 2
        7 6 5 4
        f e d c b a 9 8
         */
        //递归
        public TreeNode mirrorTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode node = new TreeNode(root.val);
            node.right = mirrorTree(root.left);
            node.left = mirrorTree(root.right);
            return node;
        }
    }

    class Solution2 {
        //错误
        public TreeNode mirrorTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            //直接赋值是错误的
            root.left = mirrorTree(root.right);
            //从根节点的左右孩子看
            //经过上一步，这一步的root.left已经不是原来的root.left了
            //已经经过了交换，所以会错误
            root.right = mirrorTree(root.left);
            return root;
        }
    }

    class Solution3 {
        //递归
        public TreeNode mirrorTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode left = mirrorTree(root.left);
            TreeNode right = mirrorTree(root.right);
            root.left = right;
            root.right = left;
            return root;
        }
    }

    class Solution4 {
        //栈
        //后序遍历
        public TreeNode mirrorTree(TreeNode root) {
            if (root == null) {
                return null;
            }

            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.empty()) {
                TreeNode node = stack.pop();
                if (node.left != null) {
                    stack.push(node.left);
                }
                if (node.right != null) {
                    stack.push(node.right);
                }

                TreeNode n = node.left;
                node.left = node.right;
                node.right = n;
            }

            return root;
        }
    }

    class Solution5 {
        //队列
        //层次遍历
        public TreeNode mirrorTree(TreeNode root) {
            if (root == null) {
                return null;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                TreeNode n = node.left;
                node.left = node.right;
                node.right = n;
            }

            return root;
        }
    }
}
