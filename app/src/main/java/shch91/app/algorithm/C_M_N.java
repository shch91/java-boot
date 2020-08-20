package shch91.app.algorithm;

public class C_M_N {
    static int N = 5;
    static int M = 3;
    static int[] a= new int[]{1,2,3,4,5};
    static int[] b = new int[M];
 
    public static void main(String[] args){
        C(N,M);
    }

    /**
     * m 中选n个
     * @param m
     * @param n
     */
    static void C(int m,int n){

        for(int i=n;i<=m;i++) {
            //选择最后一个元素
            b[n-1] = i-1;
            if(n>1) {
                C(i - 1, n - 1);
            }
            else {
                for(int j=0;j<=M-1;j++) {
                    System.out.print(a[b[j]] + "  ");
                }
                System.out.println();
            }
        }
    }
}