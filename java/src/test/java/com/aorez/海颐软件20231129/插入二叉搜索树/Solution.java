package com.aorez.海颐软件20231129.插入二叉搜索树;

//插入二叉搜索树
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param root TreeNode类
     * @param val  int整型
     * @return TreeNode类
     */
    public TreeNode insertToBST(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
        }

        if (root.val > val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
            }
            else {
                insertToBST(root.left, val);
            }
        }

        if (root.val < val) {
            if (root.right == null) {
                root.right = new TreeNode(val);
            }
            else {
                insertToBST(root.right, val);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        Solution solution = new Solution();
        solution.insertToBST(root, 2);
        System.out.println(root.right.val);
    }
}
