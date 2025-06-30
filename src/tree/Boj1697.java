package tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Boj 1697 - 숨바꼭질
 *
 * 📌 문제 설명:
 * - 수빈이는 동생을 찾기 위해 숨바꼭질을 한다.
 * - 수빈이는 X에 있고, 동생은 Y에 있다.
 * - 수빈이는 1초마다 X-1, X+1, 2*X로 이동할 수 있다.
 * - 수빈이가 동생을 찾는 최소 시간을 구하라.
 *
 * 📥 입력값:
 * 1. 첫째 줄: 수빈이의 위치 X (0 <= X <= 100,000)
 * 2. 둘째 줄: 동생의 위치 Y (0 <= Y <= 100,000)
 *
 * 🧠 풀이 전략:
 * 1. BFS를 사용하여 최단 경로 탐색
 * 2. 큐를 사용하여 현재 위치와 시간을 저장
 * 3. 방문한 위치는 다시 방문하지 않도록 체크
 *
 * ✅ 시간 복잡도:
 * - O(N): 각 노드를 한 번씩 탐색 (N ≤ 100,000)
 *
 * ✅ 공간 복잡도:
 * - O(N): BFS 큐와 방문 배열 (N ≤ 100,000)
 */
public class Boj1697 {

    public void solution(){
        int x = 5;
        int y = 17;
        int result = bfs(x, y);
        System.out.println(result);
    }

    public int bfs(int x, int y) {
        if (x == y) return 0;

        int MAX = 100_001;
        int [] dist = new int[MAX]; // 거리 저장 배열
        boolean [] visited = new boolean[MAX]; // 방문 여부 배열
        Queue<Integer> queue = new ArrayDeque<>(); // 큐 생성

        // 초기 위치와 거리 설정
        queue.add(x);
        visited[x] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 다음 위치를 계산
            for (int next : new int[]{current - 1, current + 1, current * 2}) {
                if (0 <= next && next < MAX && !visited[next]) {
                    visited[next] = true; // 방문 체크
                    dist[next] = dist[current] + 1;// 현재 위치에서 다음 위치까지의 거리
                    queue.add(next);

                    if (next == y) {
                        return dist[next]; // 동생을 찾은 경우
                    }
                }
            }
        }
        return -1; // 동생을 찾지 못한 경우
    }

    public static void main(String[] args) {
        Boj1697 boj1697 = new Boj1697();
        boj1697.solution();
    }
}
