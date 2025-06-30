package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Boj 1068 - 트리
 *
 * 📌 문제 설명:
 * - 트리가 부모 배열로 주어진다.
 * - 특정 노드를 삭제했을 때, 남은 트리에서 리프 노드(자식이 없거나 자식이 모두 삭제된 노드)의 개수를 출력하라.
 *
 * 📥 입력값:
 * 1. 첫째 줄: 노드의 개수 N (1 <= N <= 50)
 * 2. 둘째 줄: 각 노드의 부모를 나타내는 정수 N개 (index: 노드 번호, 값: 부모 번호), 루트 노드는 -1로 주어짐
 * 3. 셋째 줄: 삭제할 노드 번호
 *
 * 🧠 풀이 전략:
 * 1. 주어진 부모 배열을 자식 리스트로 변환하여 트리 구조 구성
 * 2. DFS를 이용하여 리프 노드 개수 탐색
 *     - 삭제된 노드는 아예 탐색하지 않음
 *     - 유효한 자식 노드가 하나도 없는 경우 리프 노드로 간주
 * 3. 삭제할 노드가 루트 노드인 경우, 전체 트리가 삭제되므로 리프 노드 수는 0
 *
 * 💡 예외 처리:
 * - 자식이 있지만 모두 삭제된 경우에도 리프 노드로 간주해야 함
 * - 삭제 노드가 루트인 경우 전체 트리 제거로 처리
 *
 * ✅ 시간 복잡도:
 * - O(N): 각 노드를 한 번씩 탐색 (N ≤ 50이므로 DFS로 충분)
 *
 * ✅ 공간 복잡도:
 * - O(N): 트리 구조를 저장하기 위한 리스트 배열 (N ≤ 50)
 * - O(N): DFS 호출 스택 (최대 N 깊이)
 * - O(N): 기타 변수
 */
public class Boj1068 {

    private int getLeafCount(List<Integer>[] tree, int node, int deletedNode) {
        List<Integer> child = tree[node];

        int count = 0;
        boolean hasLeaf = false;
        for (int childNode : child) {
            if (childNode != deletedNode) {
                count += getLeafCount(tree, childNode, deletedNode);
                hasLeaf = true;
            }
        }
        return hasLeaf ? count : 1;
    }

    private static List<Integer>[] getTree(int[] nodeArr) {
        int n = nodeArr.length;
        List<Integer>[] tree = new ArrayList[n];

        //초기화
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            if (nodeArr[i] == -1) continue;
            tree[nodeArr[i]].add(i);
        }
        return tree;
    }

    public static void main(String[] args) {
        Boj1068 main = new Boj1068();
        Reader reader = new Reader();

        int nodeCount = reader.nextInt();
        int[] nodeArr = new int[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            nodeArr[i] = reader.nextInt();
        }
        int deletedNode = reader.nextInt();


        int root = -1;
        for (int i = 0; i < nodeCount; i++) {
            if (nodeArr[i] == -1) {
                root = i;
                break;
            }
        }

        if (deletedNode == root) {
            System.out.println(0); // 트리 전체가 삭제됨
            return;
        }

        List<Integer>[] tree = getTree(nodeArr);

        int result = main.getLeafCount(tree, root, deletedNode);
        System.out.println(result);
    }

    static class Reader {
        BufferedReader br;
        StringTokenizer st;

        public Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
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
