package shch91.app.algorithm;

import java.util.*;

public class TopKFreq {
    public static void main(String[] args) {
        TopKFreq t = new TopKFreq();
        int[] nums = new int[]{1, 1, 1, 2, 2, 3, 444, 444, 444};
        int[] ret = t.topKFrequent(nums, 2);
        for (int it : ret) {
            System.out.print(it + "\t");
        }

    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ret = new int[k];
        int index=0;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        List<Map.Entry<Integer,Integer>> list=new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });
        for(Map.Entry<Integer,Integer>it:list){
            if(index<k){
                ret[index++]=it.getKey();
            }
        }
        return ret;
    }
}
