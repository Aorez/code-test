package com.aorez.leetcode.剑指Offer;

import org.junit.Test;

import java.util.Arrays;

public class 剑指Offer67把字符串转换成整数 {
    //Traverse
    class Solution {
        public int strToInt(String str) {
            boolean positive = true;
            int start = -1;

            //Find '-' or '+' or digit
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == ' ') {
                    continue;
                }
                else if (c == '-') {
                    positive = false;
                    start = i + 1;
                    break;
                }
                else if (c == '+') {
                    start = i + 1;
                    break;
                }
                else if (c >= '0' && c <= '9') {
                    start = i;
                    break;
                }
                else {
                    return 0;
                }
            }

            //Cases "string is null" and "string is made of spaces"
            if (start == -1) {
                return 0;
            }

            int end = str.length() - 1;
            //Find the final digit
            for (int i = start; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c >= '0' && c <= '9') {
                    continue;
                }
                else {
                    end = i - 1;
                    break;
                }
            }

            String numStr;
            //More than 10 digits in the number
            if (end - start + 1 > 10) {
                //Get "1234567890"
                numStr = str.substring(end - 9, end + 1);

                //aaaa10001234567890aaaa
                //len=22
                //start=4,end=17
                //bigNum=[4,8)
                //Get "1000"
                String bigNum = str.substring(start, end - 9);

                //Define "0000"
                char[] zeroNum = new char[end - 9 - start];
                Arrays.fill(zeroNum, '0');
                String zeroNumber = new String(zeroNum);

                //Not equal indicates too big or too small
                if (!bigNum.equals(zeroNumber)) {
                    return positive? Integer.MAX_VALUE: Integer.MIN_VALUE;
                }
            }
            else {
                numStr = str.substring(start, end + 1);
            }

            long result = 0L;
            long multiple = 1;
            for (int i = numStr.length() - 1; i >= 0; i--) {
                result += multiple * charToInt(numStr.charAt(i));
                multiple *= 10;
            }

            if (positive) {
                return result > Integer.MAX_VALUE? Integer.MAX_VALUE: (int) result;
            }
            else {
                return -result < Integer.MIN_VALUE? Integer.MIN_VALUE: (int) -result;
            }
        }

        public int charToInt(char c) {
            switch (c) {
                case '0': return 0;
                case '1': return 1;
                case '2': return 2;
                case '3': return 3;
                case '4': return 4;
                case '5': return 5;
                case '6': return 6;
                case '7': return 7;
                case '8': return 8;
                case '9': return 9;
            }
            return -1;
        }
    }

    @Test
    public void test() {
        Solution solution = new Solution();
        String str1 = "42";
        System.out.println(solution.strToInt(str1));//42
        String str2 = "   -42";
        System.out.println(solution.strToInt(str2));//-42
        String str3 = "4193 with words";
        System.out.println(solution.strToInt(str3));//4193
        String str4 = "words and 987";
        System.out.println(solution.strToInt(str4));//0
        String str5 = "-91283472332";
        System.out.println(solution.strToInt(str5));//-2147483648

        System.out.println();
        String str6 = "0000000000000001234";
        System.out.println(solution.strToInt(str6));//1234
        String str7 = "9223372036854775808";
        System.out.println(solution.strToInt(str7));//2147483647

        System.out.println();
        String str8 = "10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000522545459";
        System.out.println(solution.strToInt(str8));//2147483647

        System.out.println();
        String str9 = "3.14159";
        System.out.println(solution.strToInt(str9));
    }
}