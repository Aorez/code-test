package com.aorez.科大讯飞20231015.成绩合格判断;

import java.util.Arrays;
import java.util.Scanner;

//满足3个条件之一则成绩合格
//3
//3
//66 66 66
//99 70 60
//4
//99 10 60 25
//43 49 43
//6
//46 64 0 100 60 88
//62 88 77
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        for (int i = 0; i < k; i++) {
            int course = scanner.nextInt();
            int[] score = new int[course];
            for (int j = 0; j < course; j++) {
                score[j] = scanner.nextInt();
            }
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            if (f(score, a, b, c)) {
                System.out.println("Yes");
            }
            else {
                System.out.println("No");
            }
        }
    }

    public static boolean f(int[] score, int a, int b, int c) {
        Arrays.sort(score);
        if (score.length % 2 == 0) {
            int midIndex = score.length / 2;
            int mid = (score[midIndex] + score[midIndex - 1]) / 2;
            if (mid >= a) {
                return true;
            }
        }
        else {
            int midIndex = score.length / 2;
            int mid = score[midIndex];
            if (mid >= a) {
                return true;
            }
        }

        int sum = Arrays.stream(score).sum();
        if (sum / score.length >= b) {
            return true;
        }

        if ((sum - score[0] - score[score.length - 1]) / (score.length - 2) >= c) {
            return true;
        }

        return false;
    }
}
