package com.aorez.leetcode.剑指Offer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
    1
   / \
  2   2
 / \ / \
3  4 4  3
但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
    1
   / \
  2   2
   \   \
   3    3
示例 1：
输入：root = [1,2,2,3,4,4,3]
输出：true
示例 2：
输入：root = [1,2,2,null,3,null,3]
输出：false
限制：
0 <= 节点个数 <= 1000
 */
public class 剑指Offer28对称的二叉树 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        //层次遍历
        //判断每一层是否镜像
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            List<TreeNode> nodes = new ArrayList<>();
            nodes.add(root.left);
            nodes.add(root.right);
            while (!nodes.isEmpty()) {
                int half = nodes.size() / 2;
                for (int i = 0; i < half; i++) {
                    if (!eq(nodes.get(i), nodes.get(nodes.size() - 1 - i))) {
                        return false;
                    }
                }

                List<TreeNode> temp = new ArrayList<>();
                for (TreeNode node : nodes) {
                    if (node != null) {
                        temp.add(node.left);
                        temp.add(node.right);
                    }
                }
                nodes = temp;
            }
            return true;
        }

        public boolean eq(TreeNode node1, TreeNode node2) {
            if (node1 == null) {
                return node2 == null;
            }
            if (node2 == null) {
                return false;
            }
            return node1.val == node2.val;
        }
    }

    @Test
    public void test() {
        TreeNode third1 = new TreeNode(3);
        TreeNode third2 = new TreeNode(3);
        TreeNode third3 = new TreeNode(3);
        TreeNode third4 = new TreeNode(3);

        TreeNode second1 = new TreeNode(2);
        TreeNode second2 = new TreeNode(2);
        second1.left = third1;
        second1.right = third2;
        second2.left = third3;
        second2.right = third4;

        TreeNode root = new TreeNode(1);
        root.left = second1;
        root.right = second2;

        Solution solution = new Solution();
        System.out.println(solution.isSymmetric(root));
    }

    class Solution2 {
        //递归
        //从根节点的左右孩子开始判断
        //递归方法两个树节点参数在树中处于镜像的位置
        //递归调用的时候判断参数1.left和参数2.right是否镜像
        //参数1.right和参数2.left是否镜像
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            return check(root.left, root.right);
        }

        public boolean check(TreeNode node1, TreeNode node2) {
            if (node1 == null && node2 == null) {
                return true;
            }
            if (node1 == null || node2 == null) {
                return false;
            }
            return node1.val == node2.val && check(node1.left, node2.right) && check(node1.right, node2.left);
        }
    }

    @Test
    public void test2() {
        TreeNode third1 = new TreeNode(3);
        TreeNode third2 = new TreeNode(3);
        TreeNode third3 = new TreeNode(3);
        TreeNode third4 = new TreeNode(3);

        TreeNode second1 = new TreeNode(2);
        TreeNode second2 = new TreeNode(2);
        second1.left = third1;
        second1.right = third2;
        second2.left = third3;
        second2.right = third4;

        TreeNode root = new TreeNode(1);
        root.left = second1;
        root.right = second2;

        Solution2 solution = new Solution2();
        System.out.println(solution.isSymmetric(root));
    }

    class Solution3 {
        //递归的迭代写法
        //每次都入队一对树节点，这对树节点在树中处于镜像的位置
        //出队的时候对一对树节点判断是否镜像即可
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root.left);
            queue.offer(root.right);
            TreeNode left, right;
            while (!queue.isEmpty()) {
                left = queue.poll();
                right = queue.poll();
                if (left == null && right == null) {
                    continue;
                }
                if (left == null || right == null || left.val != right.val) {
                    return false;
                }

                queue.offer(left.left);
                queue.offer(right.right);
                queue.offer(left.right);
                queue.offer(right.left);
            }
            return true;
        }
    }

    @Test
    public void test3() {
        TreeNode third1 = new TreeNode(3);
        TreeNode third2 = new TreeNode(3);
        TreeNode third3 = new TreeNode(3);
        TreeNode third4 = new TreeNode(3);

        TreeNode second1 = new TreeNode(2);
        TreeNode second2 = new TreeNode(2);
        second1.left = third1;
        second1.right = third2;
        second2.left = third3;
        second2.right = third4;

        TreeNode root = new TreeNode(1);
        root.left = second1;
        root.right = second2;

        Solution3 solution = new Solution3();
        System.out.println(solution.isSymmetric(root));
    }
}
