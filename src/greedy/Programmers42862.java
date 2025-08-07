package greedy;

/**
 * [문제 설명]
 * 전체 학생 수 n명 중 일부가 체육복을 도난당했고,
 * 여벌 체육복을 가져온 학생은 도난당하지 않았다면 다른 학생에게 체육복을 빌려줄 수 있습니다.
 * 단, 빌려줄 수 있는 대상은 앞번호나 뒷번호 학생뿐입니다.
 *
 * [입력]
 * - n: 전체 학생 수 (2 ≤ n ≤ 30)
 * - lost: 체육복을 도난당한 학생들의 번호 배열
 * - reserve: 여벌 체육복을 가져온 학생들의 번호 배열
 *
 * [목표]
 * - 최대한 많은 학생이 체육 수업을 들을 수 있도록 한 후, 수업을 들을 수 있는 학생 수를 반환합니다.
 *
 * [풀이 전략]
 * 1. 모든 학생이 체육복을 한 벌씩 가지고 있다고 가정하고 시작합니다.
 *    → answer = n
 * 2. 여벌 체육복을 가진 학생을 boolean[] 배열로 표시합니다.
 * 3. 도난당한 학생들을 순회하며
 *    3-1. 자신이 여벌을 가지고 있다면 빌려주지 않고 본인이 사용
 *    3-2. 그렇지 않으면 앞번호 → 뒷번호 순으로 여벌 확인 후 빌림
 * 4. 빌리는 데 성공한 경우 answer++
 *
 * [시간 복잡도]
 * - O(n + L), L: lost.length (최대 30)
 * - 배열 탐색, 조건 체크만 수행 → 매우 빠름
 *
 * [공간 복잡도]
 * - O(n)
 */
public class Programmers42862 {

    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = n;
        boolean[] hasExtra = new boolean[n + 2];
        boolean[] isLost = new boolean[n + 2];

        for (int i : reserve) hasExtra[i] = true;
        for (int i : lost) {
            if (hasExtra[i]) {
                hasExtra[i] = false; // 자기 여벌로 해결
            } else {
                isLost[i] = true;
                answer--; // 수업 불가 학생 감소
            }
        }

        for (int i : lost) {
            if (!isLost[i]) continue;

            if (hasExtra[i - 1]) {
                hasExtra[i - 1] = false;
                answer++;
            } else if (hasExtra[i + 1]) {
                hasExtra[i + 1] = false;
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int n = 5;
        int[] lost = {2, 4};
        int[] reverse = {3};
        int solution = solution(n, lost, reverse);
        System.out.println(solution);
    }
}
