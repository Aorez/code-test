package com.aorez.leetcode.剑指Offer;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//可以用map存，也可以用数组
//一般的解法是遍历两次字符串，第一次遍历时记录次数，第二次遍历时判断字母是否出现一次，例如solution2
//用map可以记录下标，但是用数组记录下标不太方便，因为数组默认值是0，和下标0冲突了，如果要用数组，则最好将初值赋为string length
//数组可以用来记录状态，未出现、出现一次、重复出现，例如solution2
//solution1用map记录下标，和solution2不同，1只需要遍历map，找到最小的下标
//solution3中queue front记录的是只出现一次的字母，因为在遍历字符串的时候，查询该字母在数组中的状态
//if repeated dequeue，因为这个字母有可能在queue front，要保证queue front的字母是只出现一次的，或者queue empty
//但是性能上提交后solution2最好
public class 剑指Offer50第一个只出现一次的字符 {

    @Test
    public void test() {
        String s = "aabbcddefgg";
        String ss = "aabb";

        Solution1 solution1 = new Solution1();
//        System.out.println(solution1.firstUniqChar(ss));
//        System.out.println(solution1.firstUniqChar(s));

        Solution3 solution3 = new Solution3();
        System.out.println(solution3.firstUniqChar(ss));
        System.out.println(solution3.firstUniqChar(s));
    }

    class Solution1 {
        //空间26个字母
        //时间n+26
        public char firstUniqChar(String s) {
            Map<Character, Integer> pos = new HashMap<Character, Integer>();
            //重复出现的位置记录为-1
            //出现一次的位置记录为下标
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (pos.containsKey(c)) {
                    //用这个数字表示重复出现，则后面判断下标最小只需比min小即可
                    pos.put(c, s.length());
                }
                else {
                    pos.put(c, i);
                }
            }

            //找到下标最小的
            int min = s.length();
            for (Map.Entry<Character, Integer> entry: pos.entrySet()) {
                int index = entry.getValue();
                if (index < min) {
                    min = index;
                }
            }

            return min == s.length()? ' ': s.charAt(min);
        }
    }

    class Solution2 {
        //空间26
        //时间n+26
        public char firstUniqChar(String s) {
            char[] chars = s.toCharArray();
            int[] count = new int[26];
            for (char c: chars) {
                count[c - 'a']++;
            }
            for (char c: chars) {
                if (count[c - 'a'] == 1) {
                    return c;
                }
            }
            return ' ';
        }
    }

    class Solution3 {
        //最坏情况
        //aabbccdd
        //入队一次后出队一次
        //时间2n
        public char firstUniqChar(String s) {
            int[] count = new int[26];
            Queue<Character> queue = new LinkedList<Character>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int index = c - 'a';
                if (count[index] == 0) {
                    queue.add(c);
                    count[index] = 1;
                }
                else {
                    count[index] = -1;
                    while (!queue.isEmpty()) {
                        if (count[queue.peek() - 'a'] == -1) {
                            queue.poll();
                        }
                        else break;
                    }
                }
            }
            return queue.isEmpty()? ' ': queue.peek();
        }
    }
}
