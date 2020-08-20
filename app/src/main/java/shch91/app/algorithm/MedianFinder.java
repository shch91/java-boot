package shch91.app.algorithm;

class MedianFinder {

    public static void main(String[] args) {

        MedianFinder m = new MedianFinder();
        m.addNum(1);
        m.addNum(2);
        System.out.println(m.findMedian());
        m.addNum(3);
        System.out.println(m.findMedian());
    }

    //插入排序
    private int total;

    private int[] nums;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        total = 0;
        nums = new int[10];
    }

    public void addNum(int num) {

        if (total > (nums.length >> 1)) {
            int[] temp = new int[2 * total];
            System.arraycopy(nums, 0, temp, 0, total);
            nums = temp;
        }
        int i = total - 1;
        while (i >= 0 && nums[i] > num) {
            //后移元素
            nums[i + 1] = nums[i];
            i--;
        }
        nums[i + 1] = num;
        total++;
    }

    public double findMedian() {
        if (total % 2 == 1) {
            return nums[total>>1] * 1.0;
        } else {
            return (nums[total >> 1] + nums[(total >> 1) - 1]) / 2.0;
        }
    }
}
