package bruteForce;

public class Programmers42842 {

    public int[] solution(int brown, int yellow) {
        /*
         * 풀이
         * 1. b + y = 넓이
         * 2. w, h 결과
         * 3. 노란색 너비 == 노란색 개수 == 가로 -2 * 세로 -2, 갈색 상하 -2, 좌우 두칸 -2
         * 4. 노란색이 중앙 따라서 최소 높이는 3
         */

        int total = brown + yellow;
        for (int h = 3; h < total; h++) {
            if (total % h != 0) continue;
            int w = total / h;
            if ((h - 2) * (w - 2) == yellow) return new int[]{w, h};
        }

        return new int[0];
    }
}
