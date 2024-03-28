package com.aorez.leetcode.LCR;

import org.junit.Test;

import java.util.Arrays;

/*
https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/description/?envType=study-plan-v2&envId=coding-interviews
简单
教练使用整数数组 actions 记录一系列核心肌群训练项目编号。
为增强训练趣味性，需要将所有奇数编号训练项目调整至偶数编号训练项目之前。
请将调整后的训练项目编号以 数组 形式返回。

示例 1：
输入：actions = [1,2,3,4,5]
输出：[1,3,5,2,4]
解释：为正确答案之一

提示：
0 <= actions.length <= 50000
0 <= actions[i] <= 10000
 */
public class LCR139训练计划I {
    // 首尾双指针
    class Solution {
        public int[] trainingPlan(int[] actions) {
            if (actions.length == 0) return actions;

            int l = 0, r = actions.length - 1;
            while (l < r) {
                // 左边遇到奇数则跳过
                while (l < r && (actions[l] & 1) == 1) l++;
                // 右边遇到偶数则跳过
                while (l < r && (actions[r] & 1) == 0) r--;
                // 交换左边的偶数和右边的奇数
                // 可能l==r
                int t = actions[l];
                actions[l] = actions[r];
                actions[r] = t;
                l++;
                r--;
            }

            return actions;
        }
    }

/*
输入：actions = [1,2,3,4,5]
输出：[1,3,5,2,4]
解释：为正确答案之一
*/
    @Test
    public void test() {
        int[] a = new Solution().trainingPlan(new int[]{1,2,3,4,5});
        System.out.println(Arrays.toString(a));
    }
}
