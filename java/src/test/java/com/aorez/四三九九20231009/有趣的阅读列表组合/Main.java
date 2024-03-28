package com.aorez.四三九九20231009.有趣的阅读列表组合;

import java.util.Scanner;

//在一个文学沙龙中，你作为一名热爱阅读的书迷，来到了一本书店。书店摆放着一排书架，每个位置摆放着不同的书籍类型，用大写字母表示。你有一个特殊的任务：从这些书中选择一段连续的区间，组成一个有趣的阅读列表。
//阅读列表的"有趣"标准是：所选区间内的每种书籍类型的数量都不超过限定的值。
//例如：[A,A,A,B,B] 中类型为 A 的书籍有3本，类型为 B 的书籍有2本，每一种类型的书籍数量均不超过3本
//给定一个数组 books，表示书架上的书籍类型排布，请返回在这个书架上，共有多少种有趣的阅读列表组合
//示例：
//输入：books = [A,B,C,B], cnt = 1
//输出：8
//解释：相同类型的数据不超过 1 本，共有 8 种有趣的阅读列表组合
//长度为 1 的区间 [A]、[B]、[C]、[B] 均满足条件，共 4 种可选择区间
//长度为 2 的区间 [A,B]、[B,C]、[C,B] 均满足条件，共 3 种可选择区间
//长度为 3 的区间 [A,B,C] 满足条件，共 1 种可选择区间。
//区间 [B,C,B],[A,B,C,B] 都包含了 2 本同类型书籍 B ，不满足条件。
//返回总数 4+3+1 = 8
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String a = s.substring(9, s.indexOf("]"));
        String cnt = s.substring(s.indexOf("cnt") + 6);
        String[] split = a.split(",");
        char[] books = new char[split.length];
        for (int i = 0; i < books.length; i++) {
            books[i] = split[i].charAt(0);
        }
        f(books, Integer.parseInt(cnt));
    }

    public static void f(char[] books, int cnt) {
        int ans = 0;
        for (int i = 1; i <= books.length; i++) {
            int left = 0;
            int right = left + i - 1;
            while (right < books.length) {
                if (f2(books, left, right, cnt)) {
                    ans++;
                }
                left++;
                right++;
            }
        }
        System.out.println(ans);
    }

    public static boolean f2(char[] books, int left, int right, int cnt) {
        int[] map = new int[26];
        for (int i = left; i <= right; i++) {
            char c = books[i];
            map[c - 'A']++;
        }
        for (int m : map) {
            if (m > cnt) {
                return false;
            }
        }
        return true;
    }
}

