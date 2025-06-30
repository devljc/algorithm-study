package sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ15970 {

    private static final Input in = new Input();
    private static int N;
    private static ArrayList<Integer>[] coordinatesList;

    static void input() {
        N = in.nextInt();
        coordinatesList = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            coordinatesList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            int pos = in.nextInt();
            int color = in.nextInt();
            coordinatesList[color].add(pos);
        }
    }

    private void solution() {
        //색상 별 리스트 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(coordinatesList[i]);
        }

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            List<Integer> poses = coordinatesList[i];
            for (int j = 0; j < poses.size(); j++) {
                int left = getLeftDistance(j, i);
                int right = getRightDistance(j, i);
                int min = Math.min(left, right);
                sum += min;

            }
        }

        System.out.println(sum);
    }

    private int getLeftDistance(int index, int color) {
        if (index == 0) return Integer.MAX_VALUE;
        List<Integer> pos = coordinatesList[color];
        Integer current = pos.get(index);
        int left = pos.get(index - 1);
        return current - left;
    }

    private int getRightDistance(int index, int color) {
        List<Integer> pos = coordinatesList[color];
        if (index == pos.size() - 1) return Integer.MAX_VALUE;
        Integer current = pos.get(index);
        int right = pos.get(index + 1);
        return right - current;
    }

    public static void main(String[] args) {
        input();
        new BOJ15970().solution();
    }

    private static class Input {
        private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer st;

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        private int nextInt() {
            return Integer.parseInt(next());
        }
    }

}
