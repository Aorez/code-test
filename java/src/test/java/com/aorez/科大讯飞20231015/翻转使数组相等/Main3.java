package com.aorez.科大讯飞20231015.翻转使数组相等;

import java.util.Scanner;

//翻转第二个数组中某个区间的几个数字，判断翻转后2个数组是否相等
//打印有多少种相等的解法
//4
//1 2 3 1
//1 3 2 1
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int[] nums1 = new int[k];
        int[] nums2 = new int[k];
        for (int i = 0; i < k; i++) {
            nums1[i] = scanner.nextInt();
        }
        for (int i = 0; i < k; i++) {
            nums2[i] = scanner.nextInt();
        }
        boolean[] eq = new boolean[k];
        for (int i = 0; i < k; i++) {
            if (nums1[i] == nums2[i]) {
                eq[i] = true;
            }
        }

        int ans = 0;
        //0 1 2 3 4 5
        for (int len = 1; len <= k; len++) {
            for (int left = 0; left <= k - len; left++) {
                int right = left + len - 1;
                if (f(k, nums1, nums2, eq, left, right)) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }

    public static boolean f(int k, int[] nums1, int[] nums2, boolean[] eq, int left, int right) {
        for (int i = 0; i < left; i++) {
            if (!eq[i]) {
                return false;
            }
        }
        for (int j = right + 1; j < k; j++) {
            if (!eq[j]) {
                return false;
            }
        }

        while (left <= right) {
            if (nums1[left] != nums2[right]) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
