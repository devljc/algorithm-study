package heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * [문제 설명]
 * 이중 우선순위 큐 구현 문제입니다.
 *
 * 연산 종류:
 * - "I 숫자" : 숫자를 삽입합니다.
 * - "D 1"   : 최댓값을 삭제합니다.
 * - "D -1"  : 최솟값을 삭제합니다.
 *
 * 중복된 값이 존재할 수 있으며, 삭제 연산 시 하나만 제거합니다.
 *
 * [목표]
 * 모든 연산을 처리한 후
 * - 큐가 비어있다면 "EMPTY"
 * - 비어있지 않다면 "최댓값 최솟값"을 출력합니다.
 *
 * [입력]
 * - 여러 테스트케이스 (T ≤ 100)
 * - 각 테스트케이스마다 연산 수 (1 ≤ N ≤ 1,000,000)
 * - 모든 연산은 순서대로 처리되어야 합니다.
 *
 * [풀이 순서]
 * 1. TreeMap<Integer, Integer> 사용 (값: 개수)
 * 2. 'I num' 이면 map에 삽입 (동일 숫자는 count 증가)
 * 3. 'D 1'이면 lastKey(), 'D -1'이면 firstKey() 가져와 삭제
 *    - count가 1이면 remove, 아니면 count 감소
 * 4. 모든 명령 처리 후
 *    - 비었으면 "EMPTY"
 *    - 아니면 lastKey + " " + firstKey 출력
 *
 * [시간 복잡도]
 * - 각 삽입/삭제: O(log N)
 * - 최대 연산 수: N
 * → 전체: O(N log N)
 *
 * [공간 복잡도]
 * - TreeMap 최대 N개 원소 저장 → O(N)
 * - 그 외 변수: O(1)
 * → 총 O(N)
 */
public class Boj7662 {

    public static List<List<Command>> input() {
        Reader reader = new Reader();
        return IntStream.range(0, reader.nextInt())
                .mapToObj(i -> readAllCommand(reader))
                .collect(Collectors.toList());
    }

    private static List<Command> readAllCommand(Reader reader) {
        return IntStream.range(0, reader.nextInt())
                .mapToObj(i -> new Command(reader.nextChar(), reader.nextInt()))
                .collect(Collectors.toList());
    }

    private static String solution(List<Command> commands) {
        TreeMap<Integer, Integer> queue = new TreeMap<>();
        for (Command command : commands) {
            char code = command.getCode();
            int value = command.getValue();

            if (code == 'I') {
                queue.put(value, queue.getOrDefault(value, 0) + 1);
            }

            if (!queue.isEmpty() && code == 'D') {
                int key = (value == 1) ? queue.lastKey() : queue.firstKey();
                if (queue.get(key) == 1) {
                    queue.remove(key);
                } else {
                    queue.put(key, queue.get(key) - 1);
                }
            }
        }

        return queue.isEmpty() ? "EMPTY" : queue.lastKey() + " " + queue.firstKey();
    }

    public static void main(String[] args) throws IOException {
        List<List<Command>> input = input();
        StringBuilder sb = new StringBuilder();
        for (List<Command> commands : input) {
            sb.append(solution(commands)).append('\n');
        }
        System.out.println(sb);
    }

    public static class Command {
        private final char code;
        private final int value;

        public Command(char code, int value) {
            this.code = code;
            this.value = value;
        }

        public char getCode() {
            return code;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Command{" +
                    "code=" + code +
                    ", value=" + value +
                    '}';
        }
    }

    public static class Reader {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public char nextChar() {
            return next().charAt(0);
        }

    }
}
