package shch91.app.algorithm;

import java.util.HashSet;

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
        int i = left, j = right;
        while (i <= right && map[s.charAt(i) - 'a'] < k) {
            i++;
        }
        while (j >= left && map[s.charAt(j) - 'a'] < k) {
            j--;
        }
        //全部小于k
        if (j - i + 1 < k) {
            return 0;
        }
        for (int t = i; t <= j; t++) {
            if (map[s.charAt(t) - 'a'] < k) {
                return Math.max(getMax(s, i, t - 1, k), getMax(s, t + 1, j, k));
            }
        }
        return j - i + 1;
    }

    /**
     * 数组中的最长连续序列
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        HashSet<Integer> set = new HashSet<Integer>();
        int longset = 0;
        for (int t : nums) {
            set.add(t);
        }
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int cur = num, len = 1;
                while (set.contains(cur + 1)) {
                    len++;
                    cur++;
                }
                longset = Math.max(longset, len);
            }
        }
        return longset;
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] res = new int[len + 1];
        res[0] = 0;
        res[1] = nums[0];
        for (int i = 2; i <= len; i++) {
            res[i] = Math.max(res[i - 1], res[i - 2] + nums[i - 1]);
        }
        return res[len];
    }

    public int numSquares(int n) {
        while (n % 4 == 0) {
            n /= 4;
        }
        if (n % 8 == 7) {
            return 4;
        }
        for (int i = 1; i * i <= n; i++) {
            if (i * i == n) {
                return 1;
            }
        }
        for (int i = 1; i * i <= n; i++) {
            int b = (int) Math.sqrt(n - i * i);
            if (b * b + i * i == n) {
                return 2;
            }
        }
        return 3;
    }

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 0;
        int dp[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }


    public int coinChange(int[] coins, int amount) {
        int dp[] = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                //剩余
                int remain = i - coin;
                if (remain >= 0 && dp[remain] != -1) {
                    dp[i] = Math.min(dp[remain] + 1, dp[i]);
                }
            }
            if (dp[i] == Integer.MAX_VALUE) {
                dp[i] = -1;
            }
        }
        return dp[amount];
    }

    private int[] row = new int[]{-1, 1, 0, 0};
    private int[] col = new int[]{0, 0, -1, 1};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int[][] len = new int[matrix.length][matrix[0].length];
        boolean[][] visit = new boolean[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, find(matrix, len, visit, i, j));
            }
        }
        return max;
    }

    /**
     * 当前节点为末端节点的最大长度
     *
     * @param matrix
     * @param len
     * @param visit
     * @param x
     * @param y
     * @return
     */
    private int find(int[][] matrix, int[][] len, boolean[][] visit, int x, int y) {
        if (visit[x][y]) {
            return len[x][y];
        }
        len[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int rowX = x + row[i];
            int colY = y + col[i];
            if (rowX >= 0 && rowX < matrix.length &&
                    colY >= 0 && colY < matrix[0].length &&
                    matrix[rowX][colY] < matrix[x][y]) {
                len[x][y] = Math.max(len[x][y], find(matrix, len, visit, rowX, colY) + 1);
            }
        }
        visit[x][y] = true;
        return len[x][y];
    }

    public int climbStairs(int n) {

        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int k = 3; k <= n; k++) {
            dp[k] = dp[k - 1] + dp[k - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        DP dp = new DP();
        int[] coins = new int[]{2};

        //System.out.println(dp.rob(arr));
        System.out.println(dp.climbStairs(1));
    }
}
