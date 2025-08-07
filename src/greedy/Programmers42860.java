package greedy;

// 핵심: 문자 이동 거리, 커서 이동 거리(다음 문자 부터 A 인 횟수를 제거 해서 최소 방문 횟수를 구한다.)
public class Programmers42860 {

    public static int solution(String name) {
        int answer = 0;
        int length = name.length();

        // 1. 상하 조작 횟수 (각 문자마다), 문자 변경
        for (int i = 0; i < length; i++) {
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);
        }

        // 2. 커서이동, “A가 아닌 모든 커서 위치를 방문하기 위해 필요한 최소 이동 거리”
        int move = length - 1; // 최악의 경우: 끝까지 오른쪽 이동

        for (int i = 0; i < length; i++) {
            int next = i + 1; // 다음 문자 부터

            // 연속된 'A' 구간 스킵
            while (next < length && name.charAt(next) == 'A') {
                next++;
            }
            // ex) "ABAAAAAADFG"
            // i: 현재 위치까지 오른쪽 이동
            // length - next: 'A' 연속 이후 남은 작업량
            // 오른쪽 → ← 되돌아감 → 우측 처리
            // A(0) → B(1) → A(0) → G(10) → F(9) → D(8)
            int turnBack = i * 2 + (length - next);

            // 왼쪽 ← → 되돌아감 → 우측 처리
            //A(0) → 왼쪽으로 G(10) → F(9) → D(8) → 돌아와서 B(1)
            int reverseTurn = (length - next) * 2 + i;
            move = Math.min(move, Math.min(turnBack, reverseTurn));
        }

        return answer + move;

    }

    public static void main(String[] args) {
        System.out.println(solution("JEROEN"));
    }
}
