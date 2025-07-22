package stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 📌 주식 가격이 떨어지지 않은 기간 계산 (O(n) 스택 알고리즘)
 *
 * ✅ 핵심 개념:
 *  - 각 시간대(i)에서 주식 가격이 떨어지기 전까지 유지된 시간을 계산함
 *  - 가격이 떨어진 시점을 만나면, 이전에 스택에 저장된 인덱스들의 유지 시간을 계산
 *  - 스택에는 가격이 **아직 떨어지지 않은 인덱스**만 유지됨 (단조 스택 패턴)
 *
 * ✅ 동작 원리:
 *  - 스택에는 가격이 유지되는 동안 인덱스를 계속 쌓음
 *  - 현재 가격이 이전 가격보다 떨어졌다면, 스택에서 꺼내면서 (pop)
 *      해당 인덱스의 유지 시간을 현재 인덱스와의 차로 계산
 *  - 끝까지 가격이 떨어지지 않은 항목은 전체 길이에서 인덱스를 빼서 유지 시간 계산
 *
 * ✅ 시간 복잡도: O(n)
 *  - 각 인덱스는 최대 한 번 push되고, 한 번 pop됨
 *
 * ✅ 예시:
 *  prices = [1, 2, 3, 2, 3] → answer = [4, 3, 1, 1, 0]
 *
 * ✅ 활용:
 *  - "가격이 떨어지는 시점" 또는 "다음으로 더 낮은 수가 나오는 시점"을 빠르게 구하는 데 활용
 *  - 일기 예보, 주식, 기온, 건물 높이, 수열 문제 등에서 자주 등장하는 패턴
 */
public class Programmers42584 {

    // 스택 사용
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            int count = 0;
            for (int i1 = i + 1; i1 < prices.length; i1++) {
                count++;
                if (prices[i] > prices[i1]) break;
            }
            answer[i] = count;
        }

        return answer;
    }

    //1. 가격이 떨어진 인덱스를 먼저 구해서 해당 위치 초기화
    //2. 나머지 부분은 지속적으로 유지 된것임으로 총길이 - 인덱스 - 1
    //스택 선형 알고리즘: 특히 이전보다 큰 값/작은 값을 찾거나 계속 유지되는 상태를 추적할 때 사용
    public int[] solution2(int[] prices) {
        int[] answer = new int[prices.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                int top = stack.pop();
                answer[top] = i - top;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int top = stack.pop();
            answer[top] = prices.length - 1 - top;
        }

        return answer;
    }


    public static void main(String[] args) {
        int[] solution = new Programmers42584().solution2(new int[]{1, 2, 3, 2, 3});
        System.out.println(Arrays.toString(solution));
    }

}
