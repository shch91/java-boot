package shch91.app.algorithm;


import com.google.common.collect.Lists;

import java.util.*;

public class Graph {

    /**
     * BFS 遍历求最短路径
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)){
            return 0;
        }
        //首单层次
        int res = 1;
        LinkedList<String> q = new LinkedList<String>();
        //访问标记
        HashMap<String, Integer> visit = new HashMap<>();
        for (String it : wordList) {
            visit.put(it, 1);
        }

        //首队列,与分层标记
        q.offer(beginWord);
        q.offer("-");
        //bfs
        while (!q.isEmpty()) {
            StringBuilder cur = new StringBuilder(q.poll());
            //处于当点层
            if (!"-".equals(cur.toString())) {
                //遍历修改当个字符
                for (int i = 0; i < cur.length(); i++) {
                    char tmp = cur.charAt(i);
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (tmp != c) {
                            cur.setCharAt(i, c);
                            if (visit.getOrDefault(cur.toString(), 0) != 0) {
                                //补充当前层
                                q.offer(cur.toString());
                                visit.put(cur.toString(), 0);
                                if (endWord.equals(cur.toString())) {
                                    return res + 1;
                                }
                            }
                        }
                    }
                    //重新置回
                    cur.setCharAt(i, tmp);
                }
            } else if(!q.isEmpty()){ //
                res++;
                //补充协议层的分隔符
                q.offer("-");
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Graph g = new Graph();

        List<String> wordList = Lists.newArrayList("hot", "dog");
        int ret = g.ladderLength("hot", "dog", wordList);
        System.out.println(ret);
    }
}
