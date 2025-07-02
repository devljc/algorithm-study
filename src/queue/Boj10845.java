package queue;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 시간 복잡도 O(N)
// 공간 복잡도 O(N)
public class Boj10845 {
    //배열을 사용한 연산
    //큐 구조(큐 사용 없이)
    private static void solutionByDeque() {
        Reader reader = new Reader();
        Deque<Integer> queue = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        int commandNum = reader.nextInt();

        for (int i = 0; i < commandNum; i++) {
            String command = reader.next();
            switch (command) {
                case "push":
                    queue.offer(reader.nextInt());
                    break;

                case "pop":
                    if (queue.isEmpty()) {
                        sb.append(-1).append("\n");
                    } else {
                        sb.append(queue.poll()).append("\n");
                    }
                    break;

                case "size":
                    sb.append(queue.size()).append("\n");
                    break;

                case "empty":
                    sb.append(queue.isEmpty() ? 1 : 0).append("\n");
                    break;

                case "front":
                    sb.append(queue.isEmpty() ? -1 : queue.peek()).append("\n");
                    break;

                case "back":
                    sb.append(queue.isEmpty() ? -1 : queue.peekLast()).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    private static void solutionByArray() {
        Reader reader = new Reader();
        int startIdx = 0;
        int currentIdx = 0;
        int commandNum = reader.nextInt();
        int[] queue = new int[commandNum];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < commandNum; i++) {
            String command = reader.next();
            switch (command) {
                case "push":
                    queue[currentIdx++] = reader.nextInt();
                    break;

                case "pop":
                    if (startIdx == currentIdx) {
                        sb.append(-1).append("\n");
                    } else {
                        sb.append(queue[startIdx++]).append("\n");
                    }
                    break;

                case "size":
                    sb.append(currentIdx - startIdx).append("\n");
                    break;

                case "empty":
                    sb.append(startIdx == currentIdx ? 1 : 0).append("\n");
                    break;

                case "front":
                    sb.append(startIdx == currentIdx ? -1 : queue[startIdx]).append("\n");
                    break;

                case "back":
                    sb.append(startIdx == currentIdx ? -1 : queue[currentIdx - 1]).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        solutionByArray();
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
                } catch (Exception e) {
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
