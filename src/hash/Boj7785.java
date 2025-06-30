package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 시간 복잡도 O(N + KlogK) -> KlogK
// 공간 복잡도 O(N + K) -> O(N)
public class Boj7785 {

    private static final Set<String> workingEmployees = new HashSet<>();

    /**
     * 시간 복잡도 O(N) – 입력 개수만큼 add/remove
     * 공간 복잡도 O(N) - 최대 N개의 이름을 HashSet에 저장
     */
    static void input() {
        FastReader reader = new FastReader();
        int count = reader.nextInt();

        for (int i = 0; i < count; i++) {
            String name = reader.next();
            String status = reader.next();

            if ("enter".equals(status)) {
                workingEmployees.add(name); // O(1)
            } else {
                workingEmployees.remove(name); // O(1)
            }
        }
    }

    /**
     * 시간 복잡도 O(klogk) - 정렬
     * 공간 복잡도 O(N) - 새로운 리스트 생성
     * @return
     */
    static String solution() {
        List<String> working = new ArrayList<>(workingEmployees);
        working.sort(Collections.reverseOrder());
        return String.join("\n", working);
    }

    public static void main(String[] args) {
        input();
        System.out.print(solution());
    }

    public static class FastReader {
        private final BufferedReader br;
        private StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
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
