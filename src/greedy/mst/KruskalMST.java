package greedy.mst;

import java.util.Collections;
import java.util.List;

//크루스칼은 간선 정렬이 메인
public class KruskalMST {
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static class UnionFind {
        int[] parent, rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]); // 경로 압축
            return parent[x];
        }

        boolean union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return false;

            // Union by rank
            if (rank[rootX] < rank[rootY]) parent[rootX] = rootY;
            else {
                parent[rootY] = rootX;
                if (rank[rootX] == rank[rootY]) rank[rootX]++;
            }
            return true;
        }
    }

    public static int kruskal(int n, List<Edge> edges) {
        Collections.sort(edges); // 간선 비용 기준 정렬
        UnionFind uf = new UnionFind(n);
        int totalCost = 0;

        for (Edge edge : edges) {
            if (uf.union(edge.from, edge.to)) {
                totalCost += edge.weight;
            }
        }

        return totalCost;
    }

    /*
     * ✅ 크루스칼 알고리즘 설명:
     * - 간선을 가중치 기준으로 정렬한 뒤, 최소 간선부터 선택하며 사이클이 생기지 않도록 연결
     * - Union-Find로 사이클을 방지
     *
     * ✅ 시간복잡도:
     * - O(E log E) ≈ O(E log V)
     *   (간선 정렬 + Union-Find)
     *
     * ✅ 사용에 적합한 경우:
     * - 간선 수가 적거나, 간선 리스트가 이미 주어진 경우
     */
}
