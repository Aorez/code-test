package com.aorez.leetcode.LCR;

import org.junit.Test;

/*
https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/description/?envType=study-plan-v2&envId=coding-interviews
https://leetcode-cn.com/problems/string-to-integer-atoi/
中等
请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。

函数 myAtoi(string s) 的算法如下：
1. 读入字符串并丢弃无用的前导空格
2. 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
3. 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
4. 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
5. 如果整数数超过 32 位有符号整数范围 [−2^31,  2^31 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −2^31 的整数应该被固定为 −2^31 ，大于 2^31 − 1 的整数应该被固定为 2^31 − 1 。
6. 返回整数作为最终结果。

注意：
- 本题中的空白字符只包括空格字符 ' ' 。
- 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。

示例 1：
输入：s = "42"
输出：42
解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
第 1 步："42"（当前没有读入字符，因为没有前导空格）
         ^
第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
         ^
第 3 步："42"（读入 "42"）
           ^
解析得到整数 42 。
由于 "42" 在范围 [-2^31, 2^31 - 1] 内，最终结果为 42 。

示例 2：
输入：s = "   -42"
输出：-42
解释：
第 1 步："   -42"（读入前导空格，但忽视掉）
            ^
第 2 步："   -42"（读入 '-' 字符，所以结果应该是负数）
             ^
第 3 步："   -42"（读入 "42"）
               ^
解析得到整数 -42 。
由于 "-42" 在范围 [-2^31, 2^31 - 1] 内，最终结果为 -42 。

示例 3：
输入：s = "4193 with words"
输出：4193
解释：
第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
         ^
第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
         ^
第 3 步："4193 with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
             ^
解析得到整数 4193 。
由于 "4193" 在范围 [-2^31, 2^31 - 1] 内，最终结果为 4193 。

提示：
- 0 <= s.length <= 200
- s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
 */
public class LCR192把字符串转换成整数atoi {
    class Solution {
        public int myAtoi(String str) {
            // 去除前后空格
            str = str.trim();

            if (str.isEmpty()) {
                return 0;
            }

            // 提取正负号
            int sign = 1;
            if (str.charAt(0) == '-') {
                sign = -1;
                str = str.substring(1);
            }
            else if (str.charAt(0) == '+') {
                str = str.substring(1);
            }

            // 找到0000后的第一个字符
            int startIndex = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') {
                    startIndex++;
                }
                else {
                    break;
                }
            }

            // 如果0000后没有字符 | 0000后第一个字符不是数字
            if (startIndex > str.length() - 1 || str.charAt(startIndex) < '0' || str.charAt(startIndex) > '9') {
                return 0;
            }
            str = str.substring(startIndex);

            // 0000后第一个字符是数字
            // 找到连续的一串数字
            for (int i = 1; i < str.length(); i++) {
                // 如果不是数字，则截取数字串，退出循环
                if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                    str = str.substring(0, i);
                    break;
                }
                // 如果str都是数字，则循环正常结束即可
            }

            // [−2^31, 2^30]
            if (str.length() > 10) {
                return sign > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            long result = 0L;
            long mul = 1;
            for (int i = str.length() - 1; i >= 0; i--) {
                result += Character.getNumericValue(str.charAt(i)) * mul;
                mul *= 10;
            }

            if (sign > 0) {
                return (int) Math.min(result, Integer.MAX_VALUE);
            }
            else {
                return (int) Math.max(-result, Integer.MIN_VALUE);
            }
        }
    }

    @Test
    public void test() {
        Solution solution = new Solution();
        System.out.println(solution.myAtoi("4193 with words"));
    }
}
