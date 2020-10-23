package shch91.app.algorithm;

public class C_M_N {
    static int M = 5;
    static int N= 3;
    static int[] a= new int[]{1,2,3,4,5};
    static int[] b = new int[N];
 
    public static void main(String[] args){
        C(M,N);
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
                for(int j=0;j<=N-1;j++) {
                    System.out.print(a[b[j]] + "  ");
                }
                System.out.println();
            }
        }
    }
}