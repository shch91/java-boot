package shch91.app;

public class Main {
    public static void main(String[] args) {
        getAns(90);
    }

    public static void getAns(int ans) {
        if (ans < 3) {
            return;
        }

        int beg = 1;
        int sum = 3;
        int cur = 2;
        while (beg <= ans / 2 + 1) {
            if (sum == ans) {
                for (int k = beg; k <= cur; k++) {
                    System.out.print(k + "  ");
                }
                System.out.println();
                sum -= beg;
                beg++;
                cur++;
                sum += cur;
            } else if (sum < ans) {
                //和小于ans
                cur++;
                sum += cur;
            } else {
                //和大于ans
                sum -= beg;
                beg++;
            }
        }
    }
}
