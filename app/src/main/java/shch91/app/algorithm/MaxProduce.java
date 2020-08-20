package shch91.app.algorithm;

public class MaxProduce {

    public static void main(String[] args) {
        MaxProduce p = new MaxProduce();
        int[] nums = new int[]{1,2,3,4};
       p.productExceptSelf(nums);

        System.out.println(66.0/10000);
    }

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] sup = new int[nums.length];
        int[] inf = new int[nums.length];
        int max = sup[0] = inf[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sup[i] = Math.max(Math.max(nums[i], sup[i - 1] * nums[i]), inf[i - 1] * nums[i]);
            inf[i] = Math.min(Math.min(nums[i], sup[i - 1] * nums[i]), inf[i - 1] * nums[i]);
            max = Math.max(sup[i], max);
        }
        return max;
    }

    public void rotate(int[] nums, int k) {
        if(k%nums.length==0){
           return;
        }
        int offset=k%nums.length;
        while(offset>0){
            int tmp=nums[nums.length-1];
            for(int index=nums.length-2;index>=0;index--){
                nums[index+1]=nums[index];
            }
            nums[0]=tmp;
            offset--;
        }
    }

    public int[] productExceptSelf(int[] nums) {
         int [] front=new int[nums.length];
         int [] back=new int[nums.length];
         front[0]=back[nums.length-1]=1;
        for(int i=0;i<nums.length;i++){
            if(i>=1) {
                front[i] =nums[i - 1]*front[i-1];
            }
        }
        for(int j=nums.length-1;j>=0;j--){
            if(j<nums.length-1) {
                back[j] =nums[j+ 1]*back[j+1];
            }
        }
        int []ret=new int[nums.length];
        for(int k=0;k<nums.length;k++){
            ret[k]=back[k]*front[k];
        }
        return ret;
    }
}
