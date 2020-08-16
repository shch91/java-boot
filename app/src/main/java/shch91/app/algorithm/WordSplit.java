package shch91.app.algorithm;

import org.assertj.core.util.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class WordSplit {

    private static LinkedList<String> tmp = new LinkedList<String>();

    private static List<String> ret = new LinkedList<String>();

    public static void main(String[] args) {
        String s = "pineapplepenapple";
        ArrayList<String> wordDict = Lists.newArrayList("apple", "pen", "applepen", "pine", "pineapple");
        List<String> re = dfs(s, wordDict, new HashMap<String, LinkedList<String>>());
        for (String it : re) {
            System.out.println(it);
        }
    }

    /**
     * dfs
     * @param s
     * @param wordDict
     * @param map
     * @return
     */
    public  static  List<String> dfs(String s, List<String> wordDict, HashMap<String, LinkedList<String>> map) {
        LinkedList<String> res = new LinkedList<String>();
        if (s == null || s.length() == 0) {
            res.add("");
            return res;
        }
        if (map.containsKey(s)) {
            return map.get(s);
        }

        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> subList = dfs(s.substring(word.length()), wordDict, map);
                for (String subString : subList) {
                    res.add(word + (subString.isEmpty() ? "" : " ") + subString);
                }
            }
        }
        map.put(s, res);
        return res;
    }

    public static boolean dp(String s, List<String> wordDict) {

        if (s == null || s.length() == 0) {
            return true;
        }
        boolean dp[] = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
