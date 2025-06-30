package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


/**
 * 문제: 숫자 카드 (BOJ 10815)
 * - 상근이가 가진 숫자 카드 목록에서 특정 숫자가 있는지 여부를 판단
 *
 * 입력 범위
 * N (1 ≤ N ≤ 500,000) : 상근이의 카드 수
 * M (1 ≤ M ≤ 500,000) : 질의 수
 *
 * 시간 복잡도
 * - 카드 입력: O(N)
 * - 질의 처리: O(M)
 * - 총합: O(N + M) ≈ 최대 1,000,000 (1초 이내 충분)
 *
 * 공간 복잡도
 * - HashSet 저장 공간: O(N)
 * - ≈ 500,000개 × 4바이트(int) ≈ 2MB 내외 (Java HashSet 약간의 오버헤드 포함 시 약 3~4MB 예상)
 *
 * 	•	HashSet은 내부적으로 HashMap을 사용합니다.
 * 	•	하지만 value를 따로 저장하지 않기 때문에 구조가 더 단순하고, 메모리/연산 비용이 조금 더 적습니다.
 * 	•	→ 그래서 일부 연산(add, contains, remove)에서 약간 더 빠를 수 있음
 *
 * 	- Split의 경우 내부적으로 정규식 엔진이 항상 돌고 , 문자열 객체를 새로 생성
 */
public class Boj10815 {

    public static void main(String[] args) {
        FastReader reader = new FastReader();

        // N개의 상근이 카드 입력
        int hasCardNum = reader.nextInt();
        Set<Integer> hasCards = new HashSet<>(hasCardNum);

        for (int i = 0; i < hasCardNum; i++) {
            hasCards.add(reader.nextInt());
        }

        // M개의 질의 처리
        int findCardNum = reader.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < findCardNum; i++) {
            int number = reader.nextInt();
            sb.append(hasCards.contains(number) ? "1" : "0").append(' ');
        }

        // 결과 출력
        System.out.println(sb);
    }

    // 빠른 입력 처리를 위한 유틸리티
    private static class FastReader {
        private final BufferedReader br;
        private StringTokenizer st;

        public FastReader() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
