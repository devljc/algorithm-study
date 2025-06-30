package queue;

import java.util.Arrays;

/**
 * 시간 복잡도 O(N)
 * 공간 복잡도 O(N)
 */
public class Programmers42586 {

    static int[] solution(int[] progresses, int[] speeds) {
        int[] result = new int[progresses.length];
        int maxExtra = -1;
        int currentIdx = -1;

        for (int i = 0; i < progresses.length; i++) {

            int extra = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]);
            if (maxExtra < extra) {
                maxExtra = extra;
                currentIdx++;
            }
            result[currentIdx]++;
        }

        return Arrays.copyOfRange(result, 0, currentIdx + 1);
    }

    public static void main(String[] args) {
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};
        int[] solution = solution(progresses, speeds);
        System.out.println(Arrays.toString(solution));
    }
}
