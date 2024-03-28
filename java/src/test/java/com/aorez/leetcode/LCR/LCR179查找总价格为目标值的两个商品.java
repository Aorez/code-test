package com.aorez.leetcode.LCR;

import org.junit.Test;

import java.util.Arrays;

/*
https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/description/?envType=study-plan-v2&envId=coding-interviews
简单
购物车内的商品价格按照升序记录于数组 price。请在购物车中找到两个商品的价格总和刚好是 target。
若存在多种情况，返回任一结果即可。

示例 1：
输入：price = [3, 9, 12, 15], target = 18
输出：[3,15] 或者 [15,3]

示例 2：
输入：price = [8, 21, 27, 34, 52, 66], target = 61
输出：[27,34] 或者 [34,27]

提示：
1 <= price.length <= 10^5
1 <= price[i] <= 10^6
1 <= target <= 2*10^6
 */
public class LCR179查找总价格为目标值的两个商品 {
    // 首尾双指针
    class Solution {
        public int[] twoSum(int[] price, int target) {
            int l = 0, r = price.length - 1;
            while (l < r) {
                int sum = price[l] + price[r];
                if (sum == target) return new int[]{price[l], price[r]};
                else if (sum < target) l++;
                else r--;
            }
            return new int[]{price[l], price[r]};
        }
    }

    @Test
    public void test() {
        int[] a = new Solution().twoSum(new int[]{3, 9, 12, 15}, 18);
        System.out.println(Arrays.toString(a));
    }
}
