package greedy.mst;

import java.util.*;

//프림은 정점 기준 우선순위 탐색
public class PrimMST {
    static class Node implements Comparable<Node> {
        int idx, weight;

        Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static int prim(int n, List<List<Node>> graph) {
        boolean[] visited = new boolean[n];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int totalCost = 0;

        pq.offer(new Node(0, 0)); // 시작 정점은 0

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (visited[curr.idx]) continue;

            visited[curr.idx] = true;
            totalCost += curr.weight;

            for (Node next : graph.get(curr.idx)) {
                if (!visited[next.idx]) {
                    pq.offer(next);
                }
            }
        }

        return totalCost;
    }

    /*
     * ✅ 프림 알고리즘 설명:
     * - 현재 연결된 정점 집합에서 가장 가중치가 작은 간선을 선택해 새로운 정점을 연결
     * - 우선순위 큐를 사용하여 최소 비용 정점을 빠르게 선택
     *
     * ✅ 시간복잡도:
     * - O(E log V), E: 간선, V: 정점
     *   (우선순위 큐로 간선 탐색)
     *
     * ✅ 사용에 적합한 경우:
     * - 정점이 많고, 간선 밀도가 높은 경우
     * - 인접 리스트가 주어지는 경우
     */
}
