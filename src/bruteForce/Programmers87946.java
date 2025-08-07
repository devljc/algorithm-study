package bruteForce;

public class Programmers87946 {

    private static boolean[] visited;
    private static int max = 0;

    public static int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(k, dungeons, 0);
        return max;
    }

    private static void dfs(int k, int[][] dungeons, int count) {
        max = Math.max(max, count);
        for (int i = 0; i < dungeons.length; i++) {
            int need = dungeons[i][0];
            int usage = dungeons[i][1];
            if (!visited[i] && k >= need) {
                visited[i] = true;
                dfs(k - usage, dungeons, count+1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int solution = solution(80, new int[][]{{80, 20}, {50, 40}, {30, 10}});
        System.out.println(solution);
    }
}
