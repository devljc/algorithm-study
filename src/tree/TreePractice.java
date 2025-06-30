package tree;

import java.util.*;

public class TreePractice {
    // int [] parent = {-1, 0, 0, 1, 1, 2, 2};
    private List<Integer>[] toTree(int[] parent) {
        List<Integer>[] tree = new ArrayList[parent.length];
        for (int i = 0; i < parent.length; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == -1) continue;
            tree[parent[i]].add(i);
        }
        return tree;
    }

    private void readDFS(List<Integer>[] parent, int depth) {
        List<Integer> child = parent[depth];
        System.out.println("depth = " + depth + ", child = " + child);

        for (Integer i : child) {
            readDFS(parent, i);
        }
    }

    private void readBFS(List<Integer>[] parent) {
        // depth 저장 배열
        int[] depth = new int[parent.length];
        // 노드를 계층 별로 타고 들어가기 위해 큐 사용
        Queue<Integer> queue = new LinkedList<>();
        // 루트 노드부터 시작
        queue.add(0);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.println(current + " depth = " + depth[current]);
            // 자식 노드들
            List<Integer> child = parent[current];
            for (Integer i : child) {
                // 자식 노드의 depth는 부모 노드의 depth + 1, ex) 부모노드 depth [0] = 0, 자식노드 depth [1] = 1
                depth[i] = depth[current] + 1;
                // 자식 노드를 큐에 추가
                queue.add(i);
            }
        }

        // 루프를 돌면서 , 각 노드 값을 인덱스로 depth 배열에 저장
    }

    private int getLeafCount(List<Integer>[] parent, int depth) {
        List<Integer> child = parent[depth];
        if (child.isEmpty()) {
            return 1; // leaf node
        }

        int count = 0;
        for (Integer i : child) {
            count += getLeafCount(parent, i);
        }
        return count;
    }

    public static void main(String[] args) {
        TreePractice treePractice = new TreePractice();
        int[] arr = {-1, 0, 0, 1, 1, 2, 2};
        List<Integer>[] tree = treePractice.toTree(arr);
        System.out.println("tree = " + Arrays.toString(tree));
        treePractice.readDFS(tree, 0);
        int leafCount = treePractice.getLeafCount(tree, 0);
        System.out.println("leafCount = " + leafCount);

        System.out.println("BFS");
        treePractice.readBFS(tree);
    }
}
