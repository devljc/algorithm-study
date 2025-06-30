package hash;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * BOJ 1620 - 나는야 포켓몬 마스터 이다솜
 * URL: https://www.acmicpc.net/problem/1620
 *
 * 요구사항:
 * - 숫자를 입력하면 포켓몬 이름을 출력
 * - 이름을 입력하면 포켓몬 번호를 출력
 *
 * 해결 전략:
 * - 번호 → 이름: 배열 사용 (index 접근 O(1))
 * - 이름 → 번호: HashMap 사용 (get O(1))
 *
 * 시간 복잡도:
 * - 포켓몬 입력 처리: O(N)
 * - 질문 처리: O(M)
 * - 전체: O(N + M)
 *
 * 공간 복잡도:
 * - 포켓몬 배열: O(N)
 * - 이름 → 번호 HashMap: O(N)
 * - 출력 결과 저장용 StringBuilder: O(M)
 * - 기타 변수: O(1) (무시 가능)
 * - 전체: O(N + M)
 */
public class Boj1620 {

    private static final Reader reader = new Reader();
    private static int n, m;
    private static String [] pokemons;
    private static String [] questions;

    private final Map<String, Integer> pokemonMap = new HashMap<>();

    public static void input(){
        n = reader.nextInt();
        m = reader.nextInt();

        pokemons = new String[n];
        questions = new String[m];

        for (int i = 0; i < n; i++) {
            pokemons[i] = reader.next();
        }

        for (int i = 0; i < m; i++) {
            questions[i] = reader.next();
        }
    }

    public String[] solution(String[] pokemons, String[] questions) {
        String[] answer = new String[questions.length];
        for (int i = 0; i < pokemons.length; i++) {
            pokemonMap.put(pokemons[i], i + 1);
        }

        for (int i = 0; i < answer.length; i++) {
            if (isInteger(questions[i])) {
                int pokemonNum = Integer.parseInt(questions[i]);
                answer[i] = pokemons[pokemonNum - 1];
            } else {
                answer[i] = String.valueOf(pokemonMap.get(questions[i]));
            }
        }
        return answer;
    }

    private boolean isInteger(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        input();
        Boj1620 boj1620 = new Boj1620();
        String[] answer = boj1620.solution(pokemons, questions);

        // 백준 제출을 위한 줄바꿈 출력
        StringBuilder sb = new StringBuilder();
        for (String ans : answer) {
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }

    static class Reader {
        private final BufferedReader br;
        private StringTokenizer st;

        public Reader() {
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

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}