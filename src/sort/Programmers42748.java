package sort;

import java.util.Arrays;
//O(M × N log N)

/**
 * O(M × k log k)
 * = O(50 × 100 log 100)
 * ≈ O(50 × 100 × 7)
 * ≈ 35,000 연산 이내
 */
public class Programmers42748 {

    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int i = 0; i < commands.length; i++) {

            int[] command = commands[i];
            int from = command[0] - 1, to = command[1], target = command[2] - 1;
            int[] parts = Arrays.copyOfRange(array, from, to);

            Arrays.sort(parts);
            answer[i] = parts[target];
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        int[] solution = solution(arr, commands);
        System.out.println(Arrays.toString(solution));
    }
}
