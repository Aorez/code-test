package com.aorez.leetcode.剑指Offer;

import org.junit.Test;

public class 剑指Offer12矩阵中的路径 {
    //回溯
    //
    //check函数
    //从i,j出发，判断word[k:]是否匹配
    //首先判断i,j位置是否与word[k]等同
    //然后判断是否到达word最后一位，如果是，则是匹配的
    //然后从上下左右4个方向继续判断word[k+1]
    //这就是一个递归调用，或者说回溯
    //由于一个位置只能匹配一次，需要visited[]保存访问记录
    //同时，回溯之前visited[i][j]=true，回溯的时候visited[i][j]=false
    //
    //一开始遍历每个位置，对每个位置调用check函数
    //
    //时间MN*3的L次方，MN是长和宽，L是word的长度
    //每次调用check函数，除了第一次是4个方向都要检查，其余时间是3个方向，因为每个位置只能走一次
    //判断过word当前位置，就要去判断下一位置，所以L个字母判断到最后是3的L次方
    //而一开始遍历每个位置执行check，所以MN*3的L次方
    //因为有很多剪枝，比如每个位置只能遍历一次，不匹配的剪枝，所以最后的时间小于这个值
    //
    //空间MN
    //额外数组空间visited[][]是MN
    //栈的最大深度是L或者MN，意思是在check的时候，一直匹配直到第L个字母，或者一直匹配，MN个都匹配完了
    class Solution {
        private int[][] direction = {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };
        public boolean exist(char[][] board, String word) {
            int r = board.length, c = board[0].length;
            boolean[][] visited = new boolean[r][c];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (check(board, word, 0, i, j, visited)) {
                        return true;
                    }
                }
            }
            return false;
        }
        //第k个字母，i,j位置
        public boolean check(char[][] board, String word, int k, int i, int j, boolean[][] visited) {
            if (board[i][j] != word.charAt(k)) {
                return false;
            }
            if (k == word.length() - 1) {
                return true;
            }
            visited[i][j] = true;
            for (int[] d : direction) {
                int r = i + d[0], c = j + d[1];
                if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && !visited[r][c]) {
                    if (check(board, word, k + 1, r, c, visited)) {
                        visited[i][j] = false;
                        return true;
                    }
                }
            }
            visited[i][j] = false;
            return false;
        }
    }
    @Test
    public void test() {
        Solution solution = new Solution();
        char[][] board = {
                {'a', 'b'},
                {'c', 'd'}
        };
        String word = "abcd";
        System.out.println(solution.exist(board, word));//false

        char[][] board2 = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String word2 = "ABCB";
        //忘记判断visited
        System.out.println(solution.exist(board2, word2));//false

        char[][] board3 = {
                {'C','A','A'},
                {'A','A','A'},
                {'B','C','D'}
        };
        String word3 = "AAB";
        //返回true之前需要判断visited
        //返回false之前也要
        System.out.println(solution.exist(board3, word3));//true
    }
}
