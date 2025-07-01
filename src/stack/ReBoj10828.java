package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 복잡도 O(N)
// 공간 복잡도 O(N)
public class ReBoj10828 {

    private static void solution() {
        Reader reader = new Reader();
        int commandLineNum = reader.nextInt();
        int[] stack = new int[commandLineNum];
        StringBuilder sb = new StringBuilder();

        int topIdx = 0;
        for (int i = 0; i < commandLineNum; i++) {
            String command = reader.next();
            switch (command) {
                case "push":
                    stack[topIdx++] = reader.nextInt();
                    break;
                case "pop":
                    sb.append(topIdx == 0 ? -1 : stack[--topIdx]).append("\n");
                    break;
                case "top":
                    sb.append(topIdx == 0 ? -1 : stack[topIdx - 1]).append("\n");
                    break;
                case "size":
                    sb.append(topIdx).append("\n");
                    break;
                case "empty":
                    sb.append(topIdx == 0 ? 1 : 0).append("\n");
                    break;
            }
        }
        System.out.println(sb);
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
