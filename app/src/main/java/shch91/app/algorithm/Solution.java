package shch91.app.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {

    private static  LinkedList<String> temp = new LinkedList<>();

    private static List<List<String>> result = new ArrayList<List<String>>();

    public static void main(String[] args) {
        partition("a");
        for (List<String> list : result) {
            for (String it : list) {
                System.out.print(it + "    ");
            }
            System.out.println();
        }
    }

    public static List<List<String>> partition(String s) {
        if (s == "") {
            return result;
        }
        //开始搜索
        traceBack(s, 0);
        return result;
    }

    private static void traceBack(String str, int beg) {
        if (beg == str.length()) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int end = beg; end < str.length(); end++) {
            if (isPalindrome(str, beg, end)) {
                String tempStr = str.substring(beg, end + 1);
                temp.addLast(tempStr);
                traceBack(str, end+1);
                temp.removeLast();
            }
        }
    }

    public static boolean isPalindrome(String str, int s, int e) {
        while (s < e) {
            if (str.charAt(s++) != str.charAt(e--)) {
                return false;
            }
        }
        return true;
    }


}