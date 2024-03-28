package com.aorez.leetcode.剑指Offer;

import org.junit.Test;

import java.util.Arrays;

/*
在一个 n * m 的二维数组中，每一行都按照从左到右 非递减 的顺序排序，
每一列都按照从上到下 非递减 的顺序排序。请完成一个高效的函数，
输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
示例:
现有矩阵 matrix 如下：
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。
给定 target = 20，返回 false。
限制：
0 <= n <= 1000
0 <= m <= 1000
 */
public class 剑指Offer04二维数组中的查找 {
    @Test
    public void test() {
    }

    class Solution {

        public boolean binarySearch(int[][] matrix, int target, int col) {
            int right = matrix.length - 1;
            int left = 0;
            while (left <= right) {
                int mid = (left + right) >>> 1;
                int midVal = matrix[mid][col];
                if (target < midVal) {
                    right = mid - 1;
                }
                else if (target > midVal) {
                    left = mid + 1;
                }
                else return true;
            }
            return false;
        }

        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            if (matrix.length == 0) {
                return false;
            }

            if (matrix.length == 1) {
                return Arrays.binarySearch(matrix[0], target) >= 0;
            }

            if (matrix[0].length == 1) {
                return binarySearch(matrix, target, 0);
            }

            int row = 0;
            int col = matrix[0].length - 1;
            while (row < matrix.length && col >= 0) {
                int val = matrix[row][col];
                if (target < val) {
                    col--;
                }
                else if (target > val) {
                    row++;
                }
                else return true;
            }
            return false;
        }
    }
}