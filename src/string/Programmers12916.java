package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 문자열 내 p와 y개수 세기
 * p == y -> true
 * p != y -> false
 * p, y가 없을 경우 -> true
 * HashMap, Java 8의 HashMap은 버킷(bucket) 하나에 8개 이상의 노드가 충돌해도,
 * **전체 해시 테이블 크기(capacity)가 64 미만이면 트리로 전환하지 않고 계속 **LinkedList**로 유지돼.
 * 그 이상은 레드 블랙 트리로 변환 됨
 */
public class Programmers12916 {

    // 시간 복잡도: O(n) — n은 문자열 길이 (최대 50자로 제한되어 있으므로 실질적으론 O(1)처럼 동작)
    // 공간 복잡도: O(1) — int 변수 2개(pCount, yCount)만 사용
    public boolean solution(String s) {
        int pCount = 0;
        int yCount = 0;
        for (char c : s.toCharArray()) {
            char lowerCase = Character.toLowerCase(c);
            if (lowerCase == 'p') {
                pCount++;
            } else if (lowerCase == 'y') {
                yCount++;
            }
        }
        if (pCount == 0 && yCount == 0) return true;
        else return (pCount == yCount);
    }

    public static void main(String[] args) {
        Programmers12916 programmers12916 = new Programmers12916();
        FastReader fastReader = new FastReader();
        String containsPYOfString = fastReader.next();
        boolean solution = programmers12916.solution(containsPYOfString);
        System.out.println(solution);

    }


    public static class FastReader {
        private final BufferedReader br;
        // StringTokenizer는 문자열을 공백으로 나누어주는 클래스
        private StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
    }

}
