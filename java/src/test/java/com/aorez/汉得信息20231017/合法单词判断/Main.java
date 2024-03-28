package com.aorez.汉得信息20231017.合法单词判断;

//合法单词判断
//有2个辅音字母相邻的单词就不合法

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int yuan = 0;
        int fu = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                yuan++;
            }
            else {
                fu++;
            }
        }
        if (fu == 0) {
            System.out.println(yuan);
        }

        int ans = 1;
        fu--;
        //ababab
        //ababab  bb
        //abababaa
        if (yuan == fu) {
            ans += fu + yuan;
        }
        else if (yuan > fu) {
            ans += fu + yuan;
        }
        else {
            ans += yuan + yuan;
        }

        System.out.println(ans);
    }
}
