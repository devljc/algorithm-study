package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11652 {

    private static Reader reader = new Reader();

    private static int N;
    private static long[] cards;

    static void input() {
        N = reader.nextInt();
        cards = new long[N];
        for (int i = 0; i < N; i++) {
            cards[i] = reader.nextLong();
        }
    }

    public void solution() {
        input();
        Arrays.sort(cards); //N log N

        long mode = cards[0];
        int modeCount = 1;
        int currentCount = 1;
        // enhanced 를 사용하면 모드의 값과 비교해서 계속 같은 수만 나옴
        for (int i = 1; i < N; i++) {
            if (cards[i-1] == cards[i]) {
                currentCount++;
            } else {
                currentCount = 1;
            }

            if (modeCount < currentCount) {
                modeCount = currentCount;
                mode = cards[i];
            }
        }
        System.out.println("result: "+ mode + " " + modeCount);
    }

    public static void main(String[] args) {
        new BOJ11652().solution();
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
