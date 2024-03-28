package com.aorez.汉得信息20231017.按位取反后消除前导零;

//按位取反后消除前导零
//按位取反k次后消除前导零

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        String s = scanner.next();
        char c = '1';
        int start = 0;
        //9 3
        //110001001
        int count = 0;
        while (count < k && start < s.length() - 1) {
            if (s.charAt(start) == c) {
                start++;
            }
            else {
                c = c == '1' ? '0' : '1';
                count++;
            }
        }
        StringBuilder ans = new StringBuilder();
        if (k % 2 == 1) {
            for (int i = start; i < s.length(); i++) {
                if (s.charAt(i) == '1') {
                    ans.append('0');
                }
                else {
                    ans.append('1');
                }
            }
        }
        else {
            ans.append(s.substring(start));
        }

        System.out.println(ans);
    }
}
