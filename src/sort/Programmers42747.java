package sort;

import java.util.Arrays;


// 공간 복잡도 N
public class Programmers42747 {

    // 시간 복잡도 NlogN
    // 오름차순 정렬된 상태에서 citations[i] ≥ n - i를 처음 만족하는 순간의 n - i가 최대의 h-index
    public static int solution(int[] citations) {
        int n = citations.length;
        Arrays.sort(citations);

        for (int i = 0; i < n; i++) {
            int h = n - i;
            if(citations[i] >= h) {
                return h;
            }
        }
        return 0;
    }
    // 시간 복잡도 N
    public static int solution2(int[] citations) {
        int n = citations.length;
        int[] count = new int[n+1];

        // 각 인용 수별로 카운팅 (n번 이상은 전부 count[n]에 누적)
        for (int c : citations) {
            if (c >= n) {
                count[n]++;
            } else {
                count[c]++;
            }
        }
        // h편 이상의 논문이 각각 h번 이상 인용되었을 때, 가능한 최대의 h를 구하라.
        int sum = 0;
        for (int i = n; i >= 0; i--) {
            sum += count[i];
            if (sum >= i) return i;
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solution2(new int[]{3, 0, 6, 1, 5}));
    }

}
