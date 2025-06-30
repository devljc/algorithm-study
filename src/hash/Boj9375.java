package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 시간 복잡도 O(T * N) - T: 테스트 케이스 수, N: 각 테스트 케이스에서 옷의 개수
// 공간 복잡도 O(K) - K: 의상 종류 수 (종류별 개수를 해시맵에 저장)
public class Boj9375 {

    private static void solution() {
        FastReader reader = new FastReader();
        int testCase = reader.nextInt();
        for (int i = 0; i < testCase; i++) {
            HashMap<String, Integer> closet = new HashMap<>();
            int typeCount = reader.nextInt();
            for (int j = 0; j < typeCount; j++) {
                reader.next();
                String wearType = reader.next();
                closet.put(wearType, closet.getOrDefault(wearType, 0) + 1);
            }
            int result = calculateCombination(closet);
            System.out.println(result);
        }
    }

    private static int calculateCombination(Map<String, Integer> closet) {
        int combination = 1;
        for (Integer value : closet.values()) {
            combination *= (value + 1); //해당 의상 종류를 입지 않았을 때 +1
        }
        return combination - 1; // 아무것도 입지 않은 조합 제거 -1
    }

    public static void main(String[] args) {
        solution();
    }

    public static class FastReader {
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
