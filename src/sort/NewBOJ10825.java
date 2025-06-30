package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//정렬 : NlogN
public class NewBOJ10825 {
    private static final FastReader reader = new FastReader();

    static class Score implements Comparable<Score> {
        private final String name;
        private final int korean;
        private final int english;
        private final int math;

        public Score(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        @Override
        public int compareTo(Score other) {
            // 분기마마 동일 한지 안한지를 하나씩 확인 함으로 굳이 다시 조건 확인 안해도 됨
            if (this.korean != other.korean) return other.korean - this.korean;
            if (this.english != other.english) return this.english - other.english;
            if (this.math != other.math) return other.math - this.math;
            return this.name.compareTo(other.name);
        }
    }


    static int N;
    static Score[] scores;

    static void input() {
        N = reader.nextInt();
        scores = new Score[N];
        for (int i = 0; i < N; i++) {
            scores[i] = new Score(
                    reader.next(),
                    reader.nextInt(),
                    reader.nextInt(),
                    reader.nextInt()
            );
        }
    }


    public void solution() {
        input();
        Arrays.sort(scores);
        for (Score score : scores) {
            System.out.println(score.name);
        }
    }

    public static void main(String[] args) {
        NewBOJ10825 solution = new NewBOJ10825();
        solution.solution();
    }

    static class FastReader {

        private BufferedReader br;
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
