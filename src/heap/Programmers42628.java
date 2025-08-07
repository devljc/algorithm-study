package heap;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * [프로그래머스 42628 - 이중 우선순위 큐]
 *
 * - operations 배열은 "명령어 값" 형태의 문자열로 구성됨
 * - 명령어:
 *   - "I n" : n을 큐에 삽입
 *   - "D 1" : 큐에서 최댓값 삭제
 *   - "D -1": 큐에서 최솟값 삭제
 *
 * [문제 조건]
 * - 같은 숫자가 여러 번 삽입되어도 삭제는 1개만 수행됨 → 중복 관리 필요 없음
 * - 최종적으로 큐가 비어 있다면 [0, 0] 반환
 *
 * [자료구조 선택 이유]
 * - TreeSet은 정렬된 집합(Set) 구조로 중복을 허용하지 않음
 * - 최소값: first(), 최대값: last()
 * - 삭제: pollFirst(), pollLast() 사용 가능
 */
public class Programmers42628 {

    public static int[] solution(String[] operations) {
        TreeSet<Integer> queue = new TreeSet<>();

        for (String operation : operations) {
            char command = operation.charAt(0); // 'I' 또는 'D'
            int value = Integer.parseInt(operation.substring(2)); // 정수 값 추출

            if (command == 'I') {
                queue.add(value); // 삽입
            } else if (!queue.isEmpty()) {
                if (value == 1) {
                    queue.pollLast(); // 최대값 삭제
                } else {
                    queue.pollFirst(); // 최소값 삭제
                }
            }
        }

        // 큐가 비어있으면 [0, 0], 아니면 [최댓값, 최솟값] 반환
        return queue.isEmpty()
                ? new int[]{0, 0}
                : new int[]{queue.last(), queue.first()};
    }

    public static void main(String[] args) {
        String[] operations = {
                "I -45",
                "I 653",
                "D 1",
                "I -642",
                "I 45",
                "I 97",
                "D 1",
                "D -1",
                "I 333"
        };
        int[] solution = solution(operations);
        System.out.println(Arrays.toString(solution)); // 예상 출력: [333, 333]
    }
}