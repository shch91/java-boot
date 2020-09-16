package shch91.app.algorithm;

import java.util.HashMap;
import java.util.Map;


public class Bit {

    public int maxPoints(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        //点数量
        int len = points.length, max = 1;

        int[] p1, p2;
        for (int i = 0; i < len; i++) {
            p1 = points[i];
            int duplicate = 1, localMax = 0;
            Map<String, Integer> map = new HashMap<String, Integer>();
            for (int j = i + 1; j < len; j++) {
                p2 = points[j];
                if (p2[0] == p1[0] && p2[1] == p1[1]) {
                    duplicate++;
                } else {
                    String t = slope(p2, p1);
                    map.put(t, map.containsKey(t) ? map.get(t) + 1 : 1);
                    localMax = Math.max(map.get(t), localMax);
                }
            }
            localMax += duplicate;
            max = Math.max(max, localMax);
        }
        return max;
    }

    /**
     * 直线斜率
     *
     * @param p2
     * @param p1
     * @return
     */
    private String slope(int[] p2, int[] p1) {
        String sign = sign(p2[0] - p1[0], p2[1] - p1[1]);
        int g = gcd(Math.abs(p2[0] - p1[0]), Math.abs(p2[1] - p1[1]));
        return sign + Math.abs(p2[1] - p1[1]) / g + "/" + Math.abs(p2[0] - p1[0]) / g;
    }

    /**
     * 最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private String sign(int a, int b) {
        if (a * b >= 0) {
            return "+";
        } else {
            return "-";
        }
    }

    /**
     * 分数转小数
     *
     * @param numerator   分子
     * @param denominator 分母
     * @return
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        //结果符号
        if (numerator < 0 ^ denominator < 0) {
            sb.append("-");
        }
        long chuShu = Math.abs(Long.valueOf(denominator));
        long beiChuShu = Math.abs(Long.valueOf(numerator));
        long shang = beiChuShu / chuShu, yuShu = beiChuShu % chuShu;
        sb.append(shang);
        if (yuShu == 0) {
            return sb.toString();
        }
        sb.append(".");
        //小数部分
        while (yuShu > 0) {
            //已经出现过改余数
            if (map.containsKey(yuShu)) {
                sb.insert(map.get(yuShu), "(");
                sb.append(")");
                return sb.toString();
            }
            map.put(yuShu, sb.length());
            beiChuShu = yuShu * 10;
            sb.append(beiChuShu / chuShu);
            yuShu = beiChuShu % chuShu;
        }
        return sb.toString();
    }



    public static void main(String[] args) {
        int[][] p = new int[][]{
                {1, 1},
                {2, 2},
                {3, 3}
        };
        Bit b = new Bit();
        //int t = b.maxPoints(p);
     //   System.out.println(Integer.MAX_VALUE);
        System.out.println(1.0/998);
        System.out.println(b.fractionToDecimal(1, 998));
    }
}
