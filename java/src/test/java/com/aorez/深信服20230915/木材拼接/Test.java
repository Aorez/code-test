package com.aorez.深信服20230915.木材拼接;

//给定一个数组表示一组木材的长度，给定一个目标木材长度，求最少需要多少木材才能拼接成目标木材
//动态规划
//dp[i] = min(dp[i], dp[i - j] + 1) for j in woods

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] woods = new int[]{1, 2, 3, 4, 5};
        int target = 6;
//        System.out.println(f(woods, target));
//        System.out.println(f2(woods, target));

        int[] woods2 = new int[]{1, 2, 3, 4, 5};
        int target2 = 10;
//        System.out.println(f(woods2, target2));
//        System.out.println(f2(woods2, target2));

        int[] woods3 = new int[]{1, 2, 3, 4, 5};
        int target3 = 1;
//        System.out.println(f(woods3, target3));
//        System.out.println(f2(woods3, target3));

        int[] woods4 = new int[]{2};
        int target4 = 3;
//        System.out.println(f(woods4, target4));
//        System.out.println(f2(woods4, target4));
    }

    public static int f(int[] woods, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 0;
        for (int i = 1; i <= target; i++) {
            dp[i] = -1;
        }

        for (int i = 1; i <= target; i++) {
            for (int j : woods) {
                if (i - j < 0) {
                    continue;
                }

                if (dp[i - j] == -1) {
                    continue;
                }

                if (dp[i] == -1) {
                    dp[i] = dp[i - j] + 1;
                }

                if (dp[i - j] + 1 < dp[i]) {
                    dp[i] = dp[i - j] + 1;
                }
            }
        }

        return dp[target];
    }

    public static int f2(int[] woods, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 0;
        for (int i = 1; i <= target; i++) {
            dp[i] = Integer.MAX_VALUE - 1;
        }

        for (int i = 1; i <= target; i++) {
            for (int j : woods) {
                if (i - j < 0) {
                    continue;
                }

                dp[i] = Math.min(dp[i], dp[i - j] + 1);
            }
        }

        return dp[target] < Integer.MAX_VALUE - 1 ? dp[target] : -1;
    }
}
