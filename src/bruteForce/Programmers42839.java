package bruteForce;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 시간 복잡도 O(N! × N), 42000
 * 공간 복잡도 O(N + MAX)
 */
public class Programmers42839 {
    private static final Set<Integer> set = new HashSet<>();
    private static final int MAX = 1_000_001;
    private static final boolean[] isPrime;

    static {
        isPrime = new boolean[MAX];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        // 배수 제거
        for (int i = 2; i * i < MAX; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    public static int solution(String numbers) {

        boolean[] visited = new boolean[numbers.length()];
        dfs(numbers, "", visited);
        return set.size();
    }

    private static void dfs(String numbers, String current, boolean[] visited) {
        if (!current.isEmpty()) {
            int num = Integer.parseInt(current);
            if (isPrime[num]) set.add(num);
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(numbers, current + numbers.charAt(i), visited);
            visited[i] = false;

        }
    }

    public static void main(String[] args) {
        System.out.println(solution("011"));
    }
}
