package greedy;

import java.util.Arrays;

public class Programmers42885 {

    public static int solution(int[] people, int limit) {

        Arrays.sort(people);
        int answer = 0;
        int left = 0;
        int right = people.length - 1;
        while (left <= right) {
            if (people[left] + people[right] <= limit) left++;
            right--;
            answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] people = {70,  80, 50};
        int limit = 100;
        System.out.println(solution(people, limit));
    }
}
