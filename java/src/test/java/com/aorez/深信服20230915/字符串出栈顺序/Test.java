package com.aorez.深信服20230915.字符串出栈顺序;

import java.util.*;

public class Test {
    //给定一个字符串入栈,入栈期间可以出栈,求所有出栈的排列
    public static void main(String[] args) {
        //卡特兰数
        //https://blog.sina.com.cn/s/blog_6917f47301010cno.html
        //https://zhuanlan.zhihu.com/p/355294682
        //计算所有出栈可能的个数
        //f(0)=1
        //1,1,2,5,14,42,132,429,1430,4862
        String str = "abc";
        //abc
        //acb
        //bac
        //bca
        //cba
        Stack<Character> string = new Stack<Character>(){{
            for (int i = str.length() - 1; i >= 0; i--) {
                push(str.charAt(i));
            }
        }};
        //字符串出栈顺序
//        stackPop(string, new Stack<>(), new ArrayList<>(), false);
//        stackPop2(string, new Stack<>(), new ArrayList<>(), false);
//        stackPop3(str.toCharArray());
        //toCharArray()是重创char数组
//        stackPop4(str.toCharArray(), str.toCharArray(), 0, str.length() - 1);
        //全排列
//        permutation(str);
//        permutations2(str.toCharArray(), 0, str.length() - 1);
    }

    //Java在递归的时候使用集合类要注意恢复集合类的状态
    //递归中修改了什么，递归后就要恢复什么
    //C++函数参数没有加&或*的时候是拷贝构造，所以不需要恢复
    public static void stackPop(Stack<Character> string, Stack<Character> stack, List<Character> result, boolean pop) {

        if (pop) {
            result.add(stack.peek());
            stack.pop();
        }
        //else是有必要的
        //执行pop之后不能马上执行push
        //pop之后可能还是pop
        //例如出栈顺序bac
        else {
            stack.push(string.peek());
            string.pop();
        }

        //string.empty并打印的这段代码不能放到最前面
        //如果放到最前面
        //那么到了string=,stack=c,result=ab的时候，
        //会进入f(string,stack,result,true)和f(string,stack,result,false)
        //导致两次abc的打印
        //就像stackPop2
        if (string.empty()) {
            List<Character> rst = new ArrayList<>();
            while (!stack.empty()) {
                rst.add(stack.peek());
                stack.pop();
            }

            for (Character c : result) {
                System.out.print(c);
            }
            for (Character c : rst) {
                System.out.print(c);
            }
            System.out.println();

            //恢复
            for (int i = rst.size() - 1; i >= 0; i--) {
                stack.push(rst.get(i));
            }
            return;
        }

        if (!stack.empty()) {
            stackPop(string, stack, result, true);
            //恢复
            stack.push(result.get(result.size() - 1));
            result.remove(result.size() - 1);
        }
        stackPop(string, stack, result, false);
        //恢复
        string.push(stack.peek());
        stack.pop();
    }

    public static void stackPop2(Stack<Character> string, Stack<Character> stack, List<Character> result, boolean pop) {
        if (string.empty()) {
            List<Character> rst = new ArrayList<>();
            while (!stack.empty()) {
                rst.add(stack.peek());
                stack.pop();
            }

            for (Character c : result) {
                System.out.print(c);
            }
            for (Character c : rst) {
                System.out.print(c);
            }
            System.out.println();

            for (int i = rst.size() - 1; i >= 0; i--) {
                stack.push(rst.get(i));
            }
            return;
        }

        if (pop) {
            result.add(stack.peek());
            stack.pop();
        }
        else {
            stack.push(string.peek());
            string.pop();
        }

        boolean restore = true;
        if (string.empty()) {
            restore = false;
        }

        if (!stack.empty()) {
            stackPop2(string, stack, result, true);
            if (restore) {
                stack.push(result.get(result.size() - 1));
                result.remove(result.size() - 1);
            }
        }

        stackPop2(string, stack, result, false);
        if (restore) {
            string.push(stack.peek());
            stack.pop();
        }
    }

    public static void permutation(String str) {
        boolean[] visited = new boolean[str.length()];
        char[] result = new char[str.length()];
        permutations2(str, 0, visited, result);
    }

    //全排列
    //往result中填入字符，填过的字符设置visited
    public static void permutations2(String str, int p, boolean[] visited, char[] result) {
        if (p == str.length()) {
            System.out.println(result);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            if (visited[i]) {
                continue;
            }
            result[p] = str.charAt(i);
            visited[i] = true;
            permutations2(str, p + 1, visited, result);
            visited[i] = false;
        }
    }

    //全排列
    //left到right范围的字符，依次把每一个字符放到left的位置，并继续递归left+1到right
    public static void permutations2(char[] a, int left, int right) {
        if (left == right) {
            System.out.println(a);
            return;
        }

        for (int i = left; i <= right; i++) {
            char temp = a[i];
            a[i] = a[left];
            a[left] = temp;
            permutations2(a, left + 1, right);
            temp = a[i];
            a[i] = a[left];
            a[left] = temp;
        }
    }

    //将全排列修改为字符串出栈
    //字符串出栈的时候
    //三个字符中，如果中间的字符比右边的小，右边的比左边的小，就是错误的
    //例如cab
    //在全排列输出的时候进行检查即可
    public static void stackPop3(char[] a) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], i);
        }
        stackPop3(map, a, 0, a.length - 1);
    }

    public static void stackPop3(Map<Character, Integer> map, char[] a, int left, int right) {
        if (left == right) {
            for (int x = 0; x < right - 1; x++) {
                for (int y = 0; y < right; y++) {
                    for (int z = 0; z <= right; z++) {
                        if (map.get(a[y]) < map.get(a[z]) && map.get(a[z]) < map.get(a[x])) {
                            return;
                        }
                    }
                }
            }
            System.out.println(a);
            return;
        }

        for (int i = left; i <= right; i++) {
            char temp = a[left];
            a[left] = a[i];
            a[i] = temp;
            stackPop3(map, a, left + 1, right);
            temp = a[left];
            a[left] = a[i];
            a[i] = temp;
        }
    }

    //修改全排列为出栈序列
    //判断全排列是否出栈序列不用3次循环嵌套
    //将原入栈字符和出栈字符进行一次出入栈模拟即可
    public static void stackPop4(char[] str, char[] a, int left, int right) {
        if (left == right) {

            //检验全排列是否出栈序列
            Stack<Character> stack = new Stack<>();
            int k = 0;
            for (char c : str) {
                stack.push(c);
                //peek刚好与出栈的字符相等，说明到了出栈的时候
                while (!stack.empty() && stack.peek() == a[k]) {
                    stack.pop();
                    k++;
                }
            }

            //空说明全部正确出栈
            if (stack.empty()) {
                System.out.println(a);
            }
            return;
        }

        for (int i = left; i <= right; i++) {
            char temp = a[left];
            a[left] = a[i];
            a[i] = temp;
            stackPop4(str, a, left + 1, right);
            temp = a[left];
            a[left] = a[i];
            a[i] = temp;
        }
    }
}
