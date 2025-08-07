package graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Programmers43162 {
    /**
     * [문제: 네트워크 (프로그래머스 43162)]
     *
     * 1. 방문하지 않은 컴퓨터를 시작점으로 삼아 DFS/BFS 수행
     * 2. 연결된 모든 컴퓨터를 탐색하며 방문 처리
     * 3. 탐색이 완료될 때마다 네트워크 개수 증가
     *
     * 시간 복잡도: O(N^2)
     *  - 인접 행렬을 탐색하므로 각 정점마다 최대 N번 순회
     *
     * 공간 복잡도:
     *  - 재귀 DFS: O(N)  (최대 재귀 깊이)
     *  - 스택 기반 DFS / BFS: O(N)  (큐 or 스택의 최대 크기)
     *  - visited 배열: O(N)
     *  → 총 공간 복잡도: O(N)
     */
    public static int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsRecursive(i, computers, visited);
                answer++;
            }
        }
        return answer;
    }

    private static void bfs(int start, int[][] computers, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            for (int i = 0; i < computers.length; i++) {
                if (!visited[i] && computers[current][i] == 1) {
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }
    }

    private static void dfsRecursive(int current, int[][] computers, boolean[] visited) {
        visited[current] = true;
        for (int i = 0; i < computers.length; i++) {
            if (!visited[i] && computers[current][i] == 1) {
                visited[i] = true;
                dfsRecursive(i, computers, visited);
            }
        }
    }

    private static void dfsStack(int start, int[][] computers, boolean[] visited) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        visited[start] = true;

        while (!stack.isEmpty()) {
            Integer current = stack.pop();
            for (int i = 0; i < computers.length; i++) {
                if (!visited[i] && computers[current][i] == 1) {
                    visited[i] = true;
                    stack.push(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
    }
}
