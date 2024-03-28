package com.aorez.leetcode.剑指Offer;

import org.junit.Test;

public class 剑指Offer58II左旋转字符串 {
    class Solution {
        public String reverseLeftWords(String s, int n) {
            return s.substring(n) + s.substring(0, n);
        }
    }

    @Test
    public void test() {
        Solution solution = new Solution();
        System.out.println(solution.reverseLeftWords("abcdefg", 2));
    }
}
