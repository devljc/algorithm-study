package hash;

import java.util.HashMap;
import java.util.Map;

public class Programmers42578 {
    public int solution(String[][] clothes) {

        int answer = 1;
        Map<String, Integer> countMap = new HashMap<>();

        // 의류 종류별로 개수를 세는 해시맵 생성
        // 시간 복잡도 O(n) - clothes 배열을 한 번 순회
        for (String[] clothe : clothes) {
            String type = clothe[1];
            countMap.put(type, countMap.getOrDefault(type, 0) + 1);
        }

        // 각 의류 종류별로 입지 않는 경우를 포함하여 조합을 계산
        // 공간 복잡도 O(k) - k는 의류 종류의 수
        // 시간 복잡도 O(k) - countMap의 값들을 순회
        for (Integer value : countMap.values()) {
            // 각 의류 종류별로 입지 않는 경우를 포함하여 조합을 계산
            answer *= (value + 1);
        }
        return answer - 1;
    }

    public static void main(String[] args) {
        Programmers42578 solution = new Programmers42578();
        String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
        int result = solution.solution(clothes);
        System.out.println("Result: " + result); // Expected output: 5
    }

}
