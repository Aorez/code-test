package com.aorez.nowcoder.DP21正则表达式匹配;
/*
描述
请实现一个函数用来匹配包括'.'和'*'的正则表达式。
模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
在本题中，匹配是指字符串的所有字符匹配整个模式。
例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
数据范围:
1.str 只包含从 a-z 的小写字母。
2.pattern 只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
3.1<=str.length<=1000
4.1≤pattern.length≤1000
输入描述：
第一行输入一个字符串 str。
第二行输入一个字符串 pattern。
输出描述：
输出两个字符串的匹配结果，如果匹配则输出 true ，否则输出 false
示例1
输入：
aaa
a*a
复制
输出：
true
复制
示例2
输入：
aab
c*a*b
复制
输出：
true
复制
示例3
输入：
a
.*
复制
输出：
true
复制
示例4
输入：
aaab
a*a*a*c
复制
输出：
false
 */

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
//            Solution.main();
//        Solution2.main();
        Solution3.main();
    }

    public static class Solution {
        //24/35用例通过
        //a
        //a*
        //预期true
        //实际false
        public static void main() {
            Scanner in = new Scanner(System.in);
            String s = in.next();
            String p = in.next();
            int pi = 0;
            int si = 0;
            while (true) {
                if (si == s.length() && pi == p.length()) {
                    System.out.println(true);
                    return;
                }
                if (si == s.length() || pi == p.length()) {
                    System.out.println(false);
                    return;
                }
                if (p.charAt(pi) == s.charAt(si)) {
                    pi++;
                    si++;
                    continue;
                }
                if (p.charAt(pi) == '*') {
                    while (true) {
                        if (s.charAt(si) == p.charAt(pi - 1)) {
                            si++;
                            if (si == s.length()) {
                                System.out.println(true);
                                return;
                            }
                            continue;
                        } else {
                            pi++;
                            break;
                        }
                    }
                }
                if (p.charAt(pi) == '.') {
                    System.out.println(true);
                    return;
                }
                if (p.charAt(pi) != s.charAt(si)) {
                    System.out.println(false);
                    return;
                }
            }
        }
    }

    public static class Solution2 {
        //34/35用例通过
        //a
        //ab*
        //因为把si==str.length&&pi<pattern.length判定为false
        public static void main() {
            Scanner in = new Scanner(System.in);
            String str = in.next();
            String pattern = in.next();
            int si = 0;
            int pi = 0;
            System.out.println(match(str.toCharArray(), si, pattern.toCharArray(), pi));
        }

        //si字符串当前匹配的下标
        //pi模式串当前匹配的下标
        //分为以下几种情况
        //如果si和pi都到达了末尾，则正确匹配
        //如果si或pi中只有一个到达了末尾，则匹配失败
        //如果模式串有下一个符号，并且是'*'，*可以取0、1、或>=2
        //--如果si和pi匹配，'.'和任意字符匹配
        //----*表示0，继续匹配si和pi+2
        //----*表示1，继续匹配si+1和pi+2
        //----*表示>=2，继续匹配si+1和pi
        //--如果si和pi不匹配
        //----暗示*表示0，继续匹配si和pi+2
        //如果模式串没有下一个符号，或者不是下一个不是'*'
        //--如果si和pi匹配，则继续匹配si+1和pi+1
        //--否则匹配失败
        public static boolean match(char[] str, int si, char[] pattern, int pi) {
            if (si == str.length && pi == pattern.length) {
                return true;
            }

            if (si == str.length || pi == pattern.length) {
                return false;
            }

            //下一个符号是*
            if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
                //如果si和pi匹配，考虑'.'
                if (str[si] == pattern[pi] || pattern[pi] == '.') {
                    //*表示0
                    return match(str, si, pattern, pi + 2) ||
                            //*表示1
                            match(str, si + 1, pattern, pi + 2) ||
                            //*表示>=2
                            match(str, si + 1, pattern, pi);
                } else {
                    //不匹配，*表示0
                    return match(str, si, pattern, pi + 2);
                }
            }

            //下一个符号不是*
            if (str[si] == pattern[pi] || pattern[pi] == '.') {
                return match(str, si + 1, pattern, pi + 1);
            }
            return false;
        }
    }

    public static class Solution3 {
        public static void main() {
            Scanner in = new Scanner(System.in);
            String str = in.next();
            String pattern = in.next();
            System.out.println(match(str.toCharArray(), 0, pattern.toCharArray(), 0));
        }

        public static boolean match(char[] str, int si, char[] pattern, int pi) {
            if (si == str.length && pi == pattern.length) {
                return true;
            }

            //修改这个条件，pi到达末尾才匹配失败
            if (pi == pattern.length) {
                return false;
            }

            if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
                //si到达末尾
                //a
                //ab*
                //ab*c
                if (si == str.length) {
                    return match(str, si, pattern, pi + 2);
                }

                if (str[si] == pattern[pi] || pattern[pi] == '.') {
                    return match(str, si, pattern, pi + 2) ||
                            match(str, si + 1, pattern, pi + 2) ||
                            match(str, si + 1, pattern, pi);
                }

                return match(str, si, pattern, pi + 2);
            }

            if (si == str.length) {
                return false;
            }

            if (str[si] == pattern[pi] || pattern[pi] == '.') {
                return match(str, si + 1, pattern, pi + 1);
            }

            return false;
        }
    }
}
