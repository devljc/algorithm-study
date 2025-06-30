package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ7795 {

    private static final Reader reader = new Reader();
    private static int[] A, B;
    private static final StringBuilder sb = new StringBuilder();

    static void input() {
        int n = reader.nextInt();
        int m = reader.nextInt();
        A = new int[n + 1];
        B = new int[m + 1];

        for (int j = 1; j <= n; j++) {
            A[j] = reader.nextInt();
        }
        for (int k = 1; k <= m; k++) {
            B[k] = reader.nextInt();
        }
    }

    private int solution() {
        Arrays.sort(B);
        int result = 0;
        for (int i : A) {
            result += binarySearch(i);
        }
        return result;
    }

    public static void main(String[] args) {
        int testCase = reader.nextInt();
        for (int i = 0; i < testCase; i++) {
            input();
            int solution = new BOJ7795().solution();
            sb.append(solution);
            if (i != testCase - 1) {
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    private int binarySearch(int target) {
        int left = 1;
        int right = B.length - 1;
        int res = 0;

        while (left <= right) {

            int midIndex = (left + right) / 2;
            if (target > B[midIndex]) {
                res = midIndex;
                left = midIndex + 1;
            } else {
                right = midIndex - 1;
            }
        }
        return res;
    }

    static class Reader {
        BufferedReader br;
        StringTokenizer st;

        public Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
