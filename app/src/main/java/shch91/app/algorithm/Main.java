package shch91.app.algorithm;

/**
 * @author shch
 */
public class Main {
    public static void main(String[] args) {
        //getAns(10000);
        //int []arr=new int[]{1,2,3};
        //perm(arr,0,3);
        //System.out.println(string2int("1"));
    }

    /**
     * 连续的数和等于ans
     *
     * @param ans
     */
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

    public static void swap(int[] arr, int i, int k) {
        int temp = arr[i];
        arr[i] = arr[k];
        arr[k] = temp;
    }

    public static void perm(int[] arr, int k, int len) {
        if (k == len) {
            for(int i=0;i<len;i++) {
                System.out.print(arr[i]);
            }
            System.out.println();
        }
        for (int i = k; i < len; i++) {
            swap(arr, i, k);
            perm(arr, k + 1, len);
            swap(arr, i, k);

        }
    }


    public static  int  string2int(String str){
        if(str==null ||str.length()==0){
            return 0;
        }
        int ret=0;
        int pos=1;
        for(int k=str.length()-1;k>=0;k--){
            int c=str.charAt(k)-'0';
            ret=ret+pos*c;
            pos*=10;
        }
        return ret;
    }
}
