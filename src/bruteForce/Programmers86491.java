package bruteForce;

public class Programmers86491 {

    public static int solution(int[][] sizes) {
        int maxH = 0;
        int maxW = 0;
        for (int[] size : sizes) {
            int h = Math.max(size[0], size[1]);
            int w = Math.min(size[0], size[1]);
            maxH = Math.max(maxH, h);
            maxW = Math.max(maxW, w);
        }
        return maxH * maxW;
    }

    public static void main(String[] args) {
        int[][] sizes1 = {
                {14, 4},
                {19, 6},
                {6, 16},
                {18, 7},
                {7, 11}
        };

        int[][] sizes2 = {
                {60, 50},
                {30, 70},
                {60, 30},
                {80, 40}
        };

        System.out.println(solution(sizes1));
    }
}
