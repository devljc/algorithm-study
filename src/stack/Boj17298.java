package stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 시간 복잡도 O(N)
// 공간 복잡도 O(N)
public class Boj17298 {

    // 입력을 받아 int 배열로 반환 (시간 O(N), 공간 O(N))
    private static int[] input() {
        Reader reader = new Reader();
        int inputNum = reader.nextInt();
        int[] inputs = new int[inputNum];
        for (int i = 0; i < inputNum; i++) {
            inputs[i] = reader.nextInt();
        }
        return inputs;
    }

    public static void solution(int[] inputs) {
        Deque<Integer> stack = new ArrayDeque<>(); // 공간 O(N) 최악 시 모든 인덱스를 담음

        // 각 원소를 한 번씩만 stack에 push/pop 2N
        for (int i = 0; i < inputs.length; i++) {
            while (!stack.isEmpty() && inputs[stack.peek()] < inputs[i]) {
                inputs[stack.pop()] = inputs[i];
            }
            stack.push(i);
        }

        // O(N)
        while (!stack.isEmpty()) {
            inputs[stack.pop()] = -1;
        }

        // O(N)
        StringBuilder sb = new StringBuilder();
        for (int i : inputs) {
            sb.append(i).append(' ');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        solution(input());
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
