package com.aorez.深信服20230915.域名判断;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        // while (in.hasNextInt()) { // 注意 while 处理多个 case
        //     int a = in.nextInt();
        //     int b = in.nextInt();
        //     System.out.println(a + b);
        // }
        String com = in.next();
        if (com.length() > 255) {
            System.out.println(false);
            return;
        }
        String[] split = com.split("\\.");
        if (split.length < 2) {
            System.out.println(false);
            return;
        }
        for (String s : split) {
            if (!Main.check(s)) {
                System.out.println(false);
                return;
            }
        }
        System.out.println(true);
    }

    public static boolean check(String com) {
        if (com.length() == 0 || com.length() > 63) {
            return false;
        }
        if (com.charAt(0) == '-' || com.charAt(com.length() - 1) == '-') {
            return false;
        }
        Pattern pattern = Pattern.compile("[a-zA-Z0-9-]+");
        Matcher matcher = pattern.matcher(com);
        return matcher.matches();
    }
}
