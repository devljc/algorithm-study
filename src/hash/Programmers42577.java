package hash;

import java.util.Arrays;

public class Programmers42577 {
    public boolean solution(String[] phone_book) {
        boolean answer = true;

        // 시간 복잡도: O(n log n) - 문자열 정렬
        Arrays.sort(phone_book);

        // 공간 복잡도: O(n) - 정렬된 문자열 배열 저장
        for (int i = 1; i < phone_book.length - 1; i++) {
            // 현재 문자열과 다음 문자열의 접두사 여부 확인
            if (phone_book[i].startsWith(phone_book[i - 1])) {
                answer = false;
                break;
            }
        }

        // total 시간 복잡도: O(n log n) - 정렬에 의한 시간 복잡도
        return answer;
    }
}
