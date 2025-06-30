package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시간 복잡도: O(ΣM) - 총 문자열 길이의 합 (입력 전체 탐색)
 * 공간 복잡도: O(1) - 입력 한 줄씩 처리, StringBuilder 제외
 */
public class Boj9012 {

    private static void solution() {
        Reader reader = new Reader();
        int inputCount = reader.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inputCount; i++) {
            sb.append(isVPS(reader.next()) ? "YES\n" : "NO\n");
        }
        System.out.print(sb);
    }

    private static boolean isVPS(String s) {
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            if (answer < 0) return false;
            answer += s.charAt(i) == '(' ? 1 : -1;
        }

        return answer == 0;
    }

    public static void main(String[] args) {
        solution();
    }

    private static class Reader {
        private final BufferedReader br;
        private StringTokenizer st;

        public Reader() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
