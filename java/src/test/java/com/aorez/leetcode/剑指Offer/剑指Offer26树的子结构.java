package com.aorez.leetcode.剑指Offer;

/*
输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
B是A的子结构， 即 A中有出现和B相同的结构和节点值。
例如:
给定的树 A:
     3
    / \
   4   5
  / \
 1   2
给定的树 B：
   4
  /
 1
返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
示例 1：
输入：A = [1,2,3], B = [3,1]
输出：false
示例 2：
输入：A = [3,4,5,1,2], B = [4,1]
输出：true
限制：
0 <= 节点个数 <= 10000
 */
public class 剑指Offer26树的子结构 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class Solution {
        //先序遍历
        //递归
        //如果当前节点的值相等，则继续判断左孩子和右孩子的值
        //判断B是A的子树，则A.root.left、A.root.right都要和B.root进行上述判断
        //时间MN
        //A的M个节点，以每个节点作为子树的根节点，都要与B树判断是否是同一棵树，而B有N个节点
        //空间O(M)
        //当M<=N时，match最多只能走到M深度
        //当M>N时，最坏A的每个节点都要进行判断，递归深度M
        public boolean isSubStructure(TreeNode A, TreeNode B) {
            if (B == null || A == null) {
                return false;
            }

            if (match(A, B)) {
                return true;
            }

            if (isSubStructure(A.left, B) || isSubStructure(A.right, B)) {
                return true;
            }

            return false;
        }

        //对节点进行匹配
        public boolean match(TreeNode A, TreeNode B) {
            if (B == null) {
                return true;
            }

            if (A == null) {
                return false;
            }

            if (A.val != B.val) {
                return false;
            }

            if (match(A.left, B.left) && match(A.right, B.right)) {
                return true;
            }

            return false;
        }
    }
}
