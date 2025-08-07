package bruteForce;

import java.util.ArrayList;
import java.util.List;

public class Programmers86971 {
    /**
     * 1. 간선 제거
     * 2. 각 노드의 개수 측정
     * 3. 전체 길이 - 방문 노드 개수  = 다른 노드 개수
     * 4. 다른 노드 개수 - 방문 노드 개수 = Math.abs(), 절대값 차이
     * 5. min 변경
     *
     * 	시간 복잡도 O(N²)
     * 	•	간선을 하나씩 제거하며 N - 1번 DFS 수행
     * 	•	DFS는 한 번에 최대 N개의 노드를 방문할 수 있음
     *
     * 	공간 복잡도 O(N)
     * 	•	visited[], 인접 리스트 graph 등 N 크기의 자료구조만 사용
     */
    public static int solution(int n, int[][] wires) {
        Graph graph = new Graph(n);
        for (int[] wire : wires) {
            graph.addEdge(wire[0],wire[1]);
            graph.addEdge(wire[1],wire[0]);
        }

        int min = Integer.MAX_VALUE;
        for (int[] wire : wires) {
            boolean[] visited = new boolean[n + 1];
            visited[wire[0]] = true;
            visited[wire[1]] = true;

            int count = dfs(wire[0], wire[1], graph, visited);
            int extra = n - count;
            min = Math.min(min, Math.abs(extra - count));
        }
        return min;
    }

    private static int dfs(int current, int exclude, Graph graph, boolean[] visited) {
        int count = 1;
        for (Integer next : graph.getNode(current)) {
            if (!visited[next] && next != exclude) {
                visited[next] = true;
                count += dfs(next, exclude, graph, visited);
            }
        }
        return count;
    }

    // 인접 리스트
    public static class Graph{
        private final List<List<Integer>> graph ;

        public Graph(int nodeCount) {
            this.graph = new ArrayList<>();
            for (int i = 0; i <= nodeCount; i++) graph.add(new ArrayList<>());
        }

        public void addEdge(int v, int w) {
            graph.get(v).add(w);
        }

        public List<Integer> getNode(int node) {
            return graph.get(node);
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(4, new int[][]{{1, 2}, {2, 3}, {3, 4}}));
    }
}
