package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 	•	진출 시점을 기준으로 정렬
 * 	•	기존 카메라로 커버되지 않는 차량이 나타날 때만 새 카메라 설치
 * 	•	카메라는 진출 지점(exit)에 설치 (최대한 많은 차량 커버 목적)
 * 	O(n log n) , 약 130,000
 */
public class Programmers42884 {

    public static int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(o -> o[1]));
        int camera = 0;
        int cameraPosition = Integer.MIN_VALUE;

        for (int[] route : routes) {
            int entry = route[0];
            int exit = route[1];

            if (cameraPosition < entry) {
                cameraPosition = exit;
                camera++;
            }
        }

        return camera;
    }

    public static void main(String[] args) {
        int[][] intervals = {
                {-20, -15},
                {-14, -5},
                {-18, -13},
                {-5, -3}
        };
        int solution = solution(intervals);
        System.out.println(solution);
    }

}
