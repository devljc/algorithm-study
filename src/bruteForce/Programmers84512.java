package bruteForce;

import java.util.Map;

public class Programmers84512 {
    private static final Map<Character, Integer> vowels = Map.of(
            'A', 1,
            'E', 2,
            'I', 3,
            'O', 4,
            'U', 5
    );

    private static final int[] weight = {781, 156, 31, 6, 1};

    public static int solution(String word) {
        int answer = 0;
        char[] charArray = word.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            //본인 문자열 이전 모든 조합
            int order = vowels.get(charArray[i]) - 1;
            answer += order * weight[i];
        }
        // 길이 보정, 본인 포함
        return answer + word.length();
    }

    private static final String vowel = "AEIOU";

    //등비 수열 합
    private static int geometricSum(int size) {
        return size * ((int) Math.pow(size, size) - 1) / (size - 1);
    }

    /**
     * 풀이
     * 1. 각 자리의 조합, 등비 수열 합 구하기 5^5 + 5^4 + ... + 5^1
     * 2. 각 자리의 가중치는 반복마다 조합을 /5
     * 3. 본인 포함이므로 각 자리 계산 시 +1
     *
     * 시간 복잡도: O(N)  // 단어 길이 최대 5
     * 공간 복잡도: O(N)  // 문자 배열 toCharArray(), 또는 문자열 split 등
     */
    public static int solution2(String word) {
        int answer = 0, combinations = geometricSum(vowel.length());
        for (char c : word.toCharArray()) {
            int order = vowel.indexOf(c);
            int weight = combinations /= 5;
            answer += order * weight + 1;
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution2("I"));
    }
}
