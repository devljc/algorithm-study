package greedy;

import java.util.Arrays;
import java.util.Comparator;

// 가장 적은 비용의 간선을 우선 연결, 중복 제거, 크루스칼 ElogE
public class Programmers42861 {

    public static int solution(int n, int[][] costs) {
        int total = 0;
        UnionFind uf = new UnionFind(n);
        Arrays.sort(costs, Comparator.comparingInt(a -> a[2]));

        for (int[] cost : costs) {
            if (uf.union(cost[0], cost[1])) {
                total += cost[2];
            }
        }

        return total;
    }

    public static void main(String[] args) {
        int[][] costs = {
                {0, 1, 1},
                {0, 2, 2},
                {1, 2, 5},
                {1, 3, 1},
                {2, 3, 8}
        };
        int solution = solution(4, costs);
        System.out.println(solution);
    }

    public static class UnionFind {
        private final int[] parent;
        private final int[] rank;

        // 초기화: 각 노드는 자기 자신이 부모, 랭크는 0, 루트 노드 합치기
        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        // Find: 루트 찾기 + 경로 압축
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);  // 경로 압축
            }
            return parent[x];
        }

        // Union: 랭크 비교해서 작은 트리를 큰 트리에 붙이기
        public boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) return false;

            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else {
                parent[rootB] = rootA;
                if (rank[rootA] == rank[rootB]) {
                    rank[rootA]++;
                }
            }
            return true;
        }
    }
}
