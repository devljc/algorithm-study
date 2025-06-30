package queue;

import java.util.ArrayList;
import java.util.List;

/*
 * 시간복잡도 O(n) – arr을 한 번 순회
 * 공간복잡도 O(n) – 중복 제거된 값들을 담기 위한 List 사용
 */
public class Programmers12906 {

    public int[] solution(int[] arr) {

        List<Integer> result = new ArrayList<>();
        int preValue = -1;
        for (int value : arr) {
            if (preValue != value) {
                preValue = value;
                result.add(value);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}
