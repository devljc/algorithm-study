package bruteForce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 시간 복잡도 O(N)
public class Programmers42840 {

    private static final int STUDENTS = 3;
    private static final int[][] patterns = new int[][]{
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5},
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
    };

    public static int[] solution(int[] answers) {
        int[] score = new int[STUDENTS];
        int max = 0;
        for (int i = 0; i < answers.length; i++) {
            for (int k = 0; k < STUDENTS; k++) {
                int length = patterns[k].length;
                boolean isCorrect = patterns[k][i % length] == answers[i];

                if (isCorrect) score[k]++;
                if (score[k] > max) max = score[k];
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < score.length; i++) {
            if (max == score[i]) list.add(i);
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] answer = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(solution(answer)));
    }
}
