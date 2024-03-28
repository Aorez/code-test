package com.aorez.leetcode.剑指Offer;

import org.junit.Test;

import java.util.Arrays;

/*
输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
示例 1：
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]
示例 2：
输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]
限制：
0 <= matrix.length <= 100
0 <= matrix[i].length <= 100
 */
public class 剑指Offer29顺时针打印矩阵 {
    class Solution {
        //直接模拟
        //1 2 3
        //4 5 6
        //7 8 9
        //1  2  3  4
        //5  6  7  8
        //9  10 11 12
        //13 14 15 16
        public int[] spiralOrder(int[][] matrix) {
            if (matrix.length == 0) {
                return new int[]{};
            }
            int row = 0, col = 0;
            int direction = 4;
            int colLeft = 0, colRight = matrix[0].length - 1;
            int rowStart = 0, rowEnd = matrix.length - 1;
            int[] result = new int[matrix.length * matrix[0].length];
            int index = 0;
            do {
                result[index] = matrix[row][col];
                index++;
                switch (direction) {
                    case 1: {
                        row--;
                        if (row < rowStart) {
                            row++;
                            col++;
                            colLeft++;
                            direction = 4;
                        }
                        break;
                    }
                    case 2: {
                        row++;
                        if (row > rowEnd) {
                            row--;
                            col--;
                            colRight--;
                            direction = 3;
                        }
                        break;
                    }
                    case 3: {
                        col--;
                        if (col < colLeft) {
                            col++;
                            row--;
                            rowEnd--;
                            direction = 1;
                        }
                        break;
                    }
                    case 4: {
                        col++;
                        if (col > colRight) {
                            col--;
                            row++;
                            rowStart++;
                            direction = 2;
                        }
                        break;
                    }
                }
            }
            while (colLeft <= colRight && rowStart <= rowEnd);
            return result;
        }
    }
    @Test
    public void test() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Solution solution = new Solution();
        int[] spiralOrder = solution.spiralOrder(matrix);
        System.out.println(Arrays.toString(spiralOrder));

        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        int[] spiralOrder2 = solution.spiralOrder(matrix2);
        System.out.println(Arrays.toString(spiralOrder2));
    }
}
