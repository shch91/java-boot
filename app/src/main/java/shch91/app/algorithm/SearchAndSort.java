package shch91.app.algorithm;

public class SearchAndSort {

    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "0";
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i == nums.length - 1) {
                return "0";
            }
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i; j++) {
                if (cmp(nums[j], nums[j + 1]) < 0) {
                    int t = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = t;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
        }
        return sb.toString();
    }

    private int cmp(int x, int y) {
        int lenx = base(x), leny = base(y);
        int xy, yx, tx = x, ty = y;
        while (leny > 0) {
            tx = tx * 10;
            leny--;
        }
        xy = tx + y;
        while (lenx > 0) {
            ty = ty * 10;
            lenx--;
        }
        yx = ty + x;
        return xy - yx;
    }


    private int base(int v) {
        int t = v, ret = 0;
        do {
            t /= 10;
            ret++;
        } while (t != 0);
        return ret;
    }

    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int i = 0, j = nums.length - 1,t;
        while (i < j) {

        }
    }

}
