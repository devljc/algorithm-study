package sort;

import java.util.Arrays;

public class Programmers42746 {
    public static String solution(int[] numbers) {
        String[] nums = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            nums[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(nums, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        if (nums[0].equals("0")) return "0";

        StringBuilder sb = new StringBuilder();
        for (String num : nums) {
            sb.append(num);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        solution(new int[]{3, 30, 34, 5, 9});
    }
}
