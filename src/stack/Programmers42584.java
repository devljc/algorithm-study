package stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

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
