package graph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 문제: 타겟 넘버
 * 제한:
 * - numbers 길이 최대 20, 최소 2
 * - 각 원소는 1<=N<=50, 자연수
 * - 타겟 넘버는 1<=M<=1000, 자연수
 * <p>
 * 풀이
 * 1. +, - 두가지 선택지
 * 2. 0부터 + or - 의 조합 트리
 * 3. 배열 순서 대로, 재귀를 돌면서 각 조합 계산
 * 4. 타겟 == 모든 깊이 탐색 완료 합 일 때 count ++;
 * <p>
 * 시간 복잡도 O(2^n) -> 최대 2^20, 각 숫자 마다 +,- 분기
 * 공간 복잡도 O(N) -> 재귀 호출 스택
 */
public class Programmers43165 {

    public static int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }

    private static int dfs(int[] numbers, int target, int depth, int sum) {
        if (depth == numbers.length) return sum == target ? 1 : 0;
        return dfs(numbers, target, depth + 1, sum + numbers[depth])
                + dfs(numbers, target, depth + 1, sum - numbers[depth]);
    }

    private static int solution2(int[] numbers, int target) {
        int answer = 0;
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{0, 0});

        while (!stack.isEmpty()) {
            int[] pop = stack.pop();
            int depth = pop[0];
            int sum = pop[1];

            if (depth == numbers.length) {
                if (sum == target) answer++;
                continue;
            }

            stack.push(new int[]{depth + 1, sum + numbers[depth]});
            stack.push(new int[]{depth + 1, sum - numbers[depth]});
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] ints = {1, 1, 1, 1, 1};
        System.out.println(solution2(ints, 3));
    }
}
