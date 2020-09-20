package shch91.app.algorithm;

public class DP {


    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return getMax(s, 0, s.length() - 1, k);
    }

    /**
     * 获取字符串[left,right]中每个字符至少出现k次的子串最大长度
     *
     * @param s
     * @param left
     * @param right
     * @param k
     * @return
     */
    private int getMax(String s, int left, int right, int k) {

        int[] map = new int[26];
        for (int i = left; i <= right; i++) {
            map[s.charAt(i) - 'a']++;
        }
        //提出小于k次
        int i=left,j=right;
        while (i <= right && map[s.charAt(i)-'a'] < k) {
            i++;
        }
        while (j >= left && map[s.charAt(j)-'a'] < k) {
            j--;
        }
        //全部小于k
        if (j - i +1< k) {
            return 0;
        }
        for(int t=i;t<=j;t++){
            if(map[s.charAt(t)-'a']<k){
                return Math.max(getMax(s,i,t-1,k),getMax(s,t+1,j,k));
            }
        }
        return j-i+1;
    }

    public static void main(String[] args) {
        DP dp=new DP();
        System.out.println(dp.longestSubstring("aaabb",3));
    }
}
