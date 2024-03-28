package com.aorez.leetcode.剑指Offer;

import java.util.LinkedList;
import java.util.Queue;

/*
地上有一个m行n列的方格，
从坐标 [0,0] 到坐标 [m-1,n-1] 。
一个机器人从坐标 [0, 0] 的格子开始移动，
它每次可以向左、右、上、下移动一格（不能移动到方格外），
也不能进入行坐标和列坐标的数位之和大于k的格子。
例如，当k为18时，机器人能够进入方格 [35, 37] ，
因为3+5+3+7=18。但它不能进入方格 [35, 38]，
因为3+5+3+8=19。请问该机器人能够到达多少个格子？
示例 1：
输入：m = 2, n = 3, k = 1
输出：3
示例 2：
输入：m = 3, n = 1, k = 0
输出：1
提示：
1 <= n,m <= 100
0 <= k <= 20
*/
public class 剑指Offer13机器人的运动范围 {
    class Solution {
        //BFS
        //时间mn
        //如果continue前没有记录访问操作，则一个点可能被访问两次，因此是2mn
        //空间mn
        //访问记录的二维数组
        public int movingCount(int m, int n, int k) {
            //只有[0,0]的情况
            if (k == 0) {
                return 1;
            }

            //机器人向右和向下
            int[] r = {0, 1};
            int[] c = {1, 0};

            boolean[][] visited = new boolean[m][n];
            Queue<int[]> queue = new LinkedList<>();

            //从[0,0]开始BFS
            queue.offer(new int[]{0, 0});
            visited[0][0] = true;
            int result = 1;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                for (int i = 0; i < 2; i++) {
                    int row = current[0] + r[i];
                    int col = current[1] + c[i];

                    //continue前可以没有记录访问操作
                    //因为visited是为了BFS的时候不重复遍历
                    //而sum值大于k的点重复遍历也没事，每次都会continue
                    //但是如果没有记录访问操作，那么就会重新判断sum是否大于k
                    //相比之下，continue前也记录访问操作比较好
//                    if (row >= m || col >= n || visited[row][col] || sum(row) + sum(col) > k) {
//                        continue;
//                    }

                    if (row >= m || col >= n || visited[row][col]) {
                        continue;
                    }
                    visited[row][col] = true;
                    if (sum(row) + sum(col) > k) {
                        continue;
                    }
                    queue.offer(new int[]{row, col});
                    result++;
                }
            }

            return result;
        }

        //求某个数字的数位之和
        public int sum(int n) {
            int result = 0;
            while (n != 0) {
                result += n % 10;
                n /= 10;
            }
            return result;
        }
    }

    class Solution2 {
        //递推，动态规划
        //在BFS的时候，可以发现只需要从[0,0]开始，向右或向下
        //因此某个点机器人是否可达
        //visited[i][j] = visited[i-1][j] or visited[i][j-1]
        //时间mn
        //空间mn
        public int movingCount(int m, int n, int k) {
            if (k == 0) {
                return 1;
            }

            boolean[][] visited = new boolean[m][n];
            visited[0][0] = true;
            int result = 1;

            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (r == 0 && c == 0 || sum(r) + sum(c) > k) {
                        continue;
                    }

                    if (r - 1 >= 0) {
                        visited[r][c] |= visited[r - 1][c];
                    }
                    if (c - 1 >= 0) {
                        visited[r][c] |= visited[r][c - 1];
                    }

                    if (visited[r][c]) {
                        result++;
                    }
                }
            }

            return result;
        }

        public int sum(int n) {
            int result = 0;
            while (n != 0) {
                result += n % 10;
                n /= 10;
            }
            return result;
        }
    }
}
