package queue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ 1966 - 프린터 큐
 * https://www.acmicpc.net/problem/1966
 * 요구사항:
 * 현재 Queue의 가장 앞에 있는 문서의 ‘중요도’를 확인한다.
 * 나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면, 이 문서를 인쇄하지 않고 Queue의 가장 뒤에 재배치 한다. 그렇지 않다면 바로 인쇄를 한다.
 * <p>
 * 입력 값:
 * 1. 첫 줄에 테스트케이스의 수
 * 2. 각 테스트케이스의 첫 번째 줄에는 문서의 개수 N(1 ≤ N ≤ 100)
 * 3. 몇 번째로 인쇄되었는지 궁금한 문서가 현재 Queue에서 몇 번째에 놓여 있는지를 나타내는 정수 M(0 ≤ M < N)
 * 4. 두 번째 줄에는 N개의 문서의 중요도가 주어짐
 * <p>
 * 출력 값:
 * 각 테스트케이스마다 문서가 몇 번째로 인쇄되는지 출력
 *
 * 풀이:
 * 1. Queue를 사용하여 문서를 저장
 * 2. Queue의 가장 앞에 있는 문서의 중요도를 확인
 * 3. 나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면, 이 문서를 인쇄하지 않고 Queue의 가장 뒤에 재배치
 * 4. 그렇지 않다면 바로 인쇄
 *
 * 시간 복잡도: O(n^2) -> n은 문서의 개수, 큐 에서 문서를 꺼내고 다시 넣는 작업이 n번 반복되기 때문
 * 공간 복잡도: O(n) -> 큐에 문서를 저장하기 때문
 *
 * 혹시 시간 여유가 있다면 PriorityQueue와 Map<Integer, Queue<Index>> 조합으로 더 최적화된 버전도 만들 수 있을 것 같습니다.
 */
public class Boj1966 {

    private static class Node {
        int priority; // 문서의 중요도
        int documentNumber; // 문서의 인덱스


        public Node(int priority, int documentNumber) {
            this.priority = priority;
            this.documentNumber = documentNumber;
        }

        public int getPriority() {
            return priority;
        }

        public int getDocumentNumber() {
            return documentNumber;
        }
    }

    public int solution(Deque<Node> queue, int target) {
        // ex) N = 6 M = 0, arr = {1, 1, 9, 1, 1, 1}
        // O(n^2) -> n은 문서의 개수, while (!queue.isEmpty())가 n번 반복되고, isMaxPriority()가 n번 반복되기 때문

        int count = 0; // 인쇄된 문서의 개수
        while (!queue.isEmpty()) {
            Node current = queue.pollFirst();
            boolean isPrint = isMaxPriority(queue, current);
            if (isPrint) {
                count++;
                if (current.getDocumentNumber() == target) {
                    return count;
                }
            }else{
                queue.addLast(current); // 현재 문서를 뒤로 보냄
            }
        }
        return -1; // 문서가 인쇄되지 않은 경우
    }

    //O(n) -> n은 문서의 개수
    private boolean isMaxPriority(Deque<Node> queue, Node current) {
        boolean isHigher = true;
        for (Node node : queue) {
            if (current.getPriority() < node.getPriority()) {
                isHigher = false;
                break;
            }
        }
        return isHigher;
    }

    public static void main(String[] args) {
        Reader reader = new Reader();
        Boj1966 boj1966 = new Boj1966();

        int testCase = reader.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < testCase; i++) {
            int n = reader.nextInt();
            int m = reader.nextInt();
            Deque<Node> queue = new ArrayDeque<>();
            for (int j = 0; j < n; j++) {
                int priority = reader.nextInt();
                queue.add(new Node(priority, j));
            }
            int result = boj1966.solution(queue, m);
            sb.append(result);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static class Reader {
        private final BufferedReader br;
        private StringTokenizer st;

        public Reader() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next(){
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt(){
            return Integer.parseInt(next());
        }
    }
}
