package heap;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * [문제 설명]
 * 매운 것을 좋아하는 Leo는 모든 음식의 스코빌 지수를 K 이상으로 만들고 싶습니다.
 *
 * 섞는 규칙:
 *   섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식 + (두 번째로 맵지 않은 음식 × 2)
 *
 * 목표:
 *   모든 음식의 스코빌 지수를 K 이상으로 만들기 위한 최소 횟수를 구하시오.
 *   만들 수 없으면 -1을 반환합니다.
 *
 * [제한 사항]
 * - scoville.length (N): 2 ≤ N ≤ 1,000,000
 * - K: 0 ≤ K ≤ 1,000,000,000
 * - scoville[i]: 0 ≤ scoville[i] ≤ 1,000,000
 *
 * [풀이 순서]
 * 1. 모든 스코빌 지수를 우선순위 큐(PriorityQueue)에 삽입 (최소 힙 구성)
 * 2. 큐의 가장 작은 값이 K 이상일 때까지 반복:
 *    - 가장 맵지 않은 음식 2개를 꺼내어 섞기
 *    - 새 음식을 큐에 삽입
 *    - 섞은 횟수 증가
 * 3. 조건을 만족하면 횟수 반환, 만족 못하면 -1 반환
 *
 * [시간 복잡도]
 * - 초기 삽입: O(N log N)
 * - 섞는 과정: 최대 N-1번 × O(log N)
 * → 총 O(N log N)
 *
 * [공간 복잡도]
 * - 우선순위 큐에 최대 N개 → O(N)
 * - 기타 변수: O(1)
 * → 총 O(N)
 */
public class Programmers42626 {

    /**
     * 모든 음식의 스코빌 지수를 K 이상으로 만들기 위한 최소 횟수를 반환합니다.
     *
     * @param scoville 스코빌 지수 배열
     * @param K 목표 스코빌 지수
     * @return 최소 횟수 또는 -1
     */
    public static int solution(int[] scoville, int K) {
        Queue<Integer> queue = new PriorityQueue<>();

        // 모든 음식 삽입 (최소 힙)
        for (int s : scoville) {
            queue.offer(s);
        }

        int operationCount = 0;

        // 가장 작은 음식이 K 이상이 될 때까지 반복
        while (queue.size() > 1 && queue.peek() < K) {
            int first = queue.poll();    // 가장 맵지 않은 음식
            int second = queue.poll();   // 두 번째로 맵지 않은 음식
            int mixed = first + second * 2;

            queue.offer(mixed);          // 새 음식 삽입
            operationCount++;            // 연산 횟수 증가
        }

        // 최종 상태 확인
        return queue.peek() != null && queue.peek() >= K ? operationCount : -1;
    }

    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int k = 7;

        int result = solution(scoville, k);
        System.out.println(result); // Expected: 2
    }
}