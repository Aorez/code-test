package com.aorez.科大讯飞20231015.禁着点;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

//给定一个long数字s和一个禁着点数组，判断有多少组x+y=s
//3
//1 2 3
//10
//答案
//4,6
//5,5
//6,4
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        Set<Long> nums = new HashSet<>();
        for (int i = 0; i < k; i++) {
            nums.add(scanner.nextLong());
        }
        long s = scanner.nextLong();
        long ans = s - 1;
        long mid = s / 2;
        for (long n : nums) {
            if (n >= s) {
                continue;
            }
            if (n <= mid) {
                ans -= 2;
            }
            else {
                long n2 = s - n;
                if (!nums.contains(n2)) {
                    ans -= 2;
                }
            }
        }
        if (s % 2 == 0) {
            if (nums.contains(mid)) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}
