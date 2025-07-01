package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Boj10828 {

    private static final Deque<Integer> stack = new ArrayDeque<>();

    private static void solution() {
        Reader reader = new Reader();
        int commandLineNum = reader.nextInt();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < commandLineNum; i++) {
            int action = action(reader);
            if (action == -2) continue;
            result.append(action)
                    .append("\n");
        }
        System.out.println(result);
    }

    private static int action(Reader reader) {
        switch (reader.next()) {
            case "push": {
                stack.push(reader.nextInt());
                return -2;
            }
            case "pop": {
                return stack.peekLast() == null ? -1 : stack.pop();
            }
            case "size": {
                return stack.size();
            }
            case "empty": {
                return stack.isEmpty() ? 1 : 0;
            }
            default: {
                return stack.peek() == null ? -1 : stack.peek();
            }
        }
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
