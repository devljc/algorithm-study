package array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * K번째 수 문제 풀이
 *
 * 1. commands[i] = [start, end, k]일 때
 *    - array의 (start-1) ~ (end-1)까지 슬라이싱
 *    - 정렬 후 k번째 원소를 선택 (k는 1-based)
 *
 * 제한 사항:
 * - 1 <= array.length <= 100
 * - 1 <= commands.length <= 50
 * - 각 원소 값은 1~100 사이 정수
 *
 * 시간 복잡도: O(M × K log K)
 *   - M: 명령 수 (commands.length)
 *   - K: 슬라이스 길이 (end - start + 1)
 *   - 최악의 경우 K ≈ N (array 전체 슬라이싱) → O(M × N log N)
 *
 * 공간 복잡도: O(K)
 *   - 명령당 슬라이스 배열을 별도 생성 (정렬은 in-place)
 *   - 결과 배열 answer는 O(M)
 *
 * 참고: Arrays.copyOfRange(start, end)는 [start, end-1] 구간 복사
 *      - Arrays.sort()는 in-place(원본 메모리 공간 그대로 사용) 정렬, O(N log N), 내부적으로 Dual-Pivot QuickSort 사용
 *      - Arrays.sort()는 안정 정렬이 아님
 *      - 객체 정렬 시에는 내부에 TimSort 사용
 */
public class Programmers42748 {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            int startIdx = commands[i][0];
            int endIdx = commands[i][1];
            int[] rangeSortArray = getRangeSortArray(array, startIdx, endIdx);
            int targetIdx = commands[i][2] - 1;
            answer[i] = rangeSortArray[targetIdx];
        }
        return answer;
    }

    private int[] getRangeSortArray(int[] array, int i, int j) {
        int[] slice = Arrays.copyOfRange(array, i - 1, j);
        Arrays.sort(slice);
        return slice;
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        int[] result = new Programmers42748().solution(array, commands);
        System.out.println(Arrays.toString(result));
    }

    public static class Reader {
        private final BufferedReader br;
        private StringTokenizer st;

        public Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
