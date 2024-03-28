package com.aorez.leetcode.LCR;

import org.junit.Test;

/*
https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/description/?envType=study-plan-v2&envId=coding-interviews
https://leetcode-cn.com/problems/reverse-words-in-a-string/
简单
你在与一位习惯从右往左阅读的朋友发消息，他发出的文字顺序都与正常相反但单词内容正确
为了和他顺利交流你决定写一个转换程序，把他所发的消息 message 转换为正常语序。

注意：输入字符串 message 中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。

示例 1：
输入: message = "the sky is blue"
输出: "blue is sky the"

示例 2：
输入: message = "  hello world!  "
输出: "world! hello"
解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。

示例 3：
输入: message = "a good   example"
输出: "example good a"
解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。

提示：
1 <= message.length <= 10^4
message 中包含英文大小写字母、空格和数字
message 中至少有一个单词
 */
public class LCR181字符串中的单词反转 {
    // 倒序遍历字符串
    class Solution {
        public String reverseMessage(String message) {
            if (message.isEmpty()) {
                return message;
            }

            // 初始化16characters的容量
            StringBuilder result = new StringBuilder();
            int r = message.length() - 1, l = r;
            while (l >= 0 && r >= 0) {
                while (r >= 0 && message.charAt(r) == ' ') r--;
                l = r;
                while (l >= 0 && message.charAt(l) != ' ') l--;
                if (r >= 0) {
                    result.append(message.substring(l + 1, r + 1));
                    result.append(" ");
                    r = l;
                }
            }

            // 可能l<0但是r>=0
            // 所以要加最后一个单词
            if (r >= 0) result.append(message.substring(0, r + 1));
            return String.valueOf(result).trim();
        }
    }

    @Test
    public void test() {
        String s = new Solution().reverseMessage("the sky is blue");
        System.out.println("[" + s + "]");
        s = new Solution().reverseMessage("  hello world!  ");
        System.out.println("[" + s + "]");
        s = new Solution().reverseMessage("a good   example");
        System.out.println("[" + s + "]");
    }

    // 利用split
    class Solution2 {
        public String reverseMessage(String message) {
            StringBuilder result = new StringBuilder();
            String[] msg = message.split(" ");
            for (int i = msg.length - 1; i >= 0; i--) {
                if (!msg[i].isEmpty()) {
                    result.append(msg[i]);
                    result.append(" ");
                }
            }
            return String.valueOf(result).trim();
        }
    }

    @Test
    public void test2() {
        String s = new Solution2().reverseMessage("the sky is blue");
        System.out.println("[" + s + "]");
        s = new Solution2().reverseMessage("  hello world!  ");
        System.out.println("[" + s + "]");
        s = new Solution2().reverseMessage("a good   example");
        System.out.println("[" + s + "]");
    }
}
