package hash;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Programmers42576 {

    // 시간 복잡도 O(n) - participants 배열을 순회하며 해시맵에 저장
    // 공간 복잡도 O(n) - 해시맵에 participants 배열의 모든 참가자를 저장하기 때문
    //정확성: 58.3
    //효율성: 41.7
    //합계: 100.0 / 100.0
    public String solutionByHashMap(String[] participants, String[] completions) {
        LocalDateTime now = LocalDateTime.now();
        String answer = "";
        HashMap<String, Integer> participantMap = new HashMap<>();

        // 참가자 배열을 해시맵에 저장
        // 동명이인의 경우 참가자 이름을 키로 하고, 해당 참가자의 수를 값으로 저장
        // 시간 복잡도 O(n) - participants 배열을 한 번 순회
        for (String participant : participants) {
            participantMap.put(participant, participantMap.getOrDefault(participant, 0) + 1);
        }

        // 완주한 참가자 배열을 순회하며 해시맵에서 1을 감소
        // 시간 복잡도 O(m) - completions 배열을 한 번 순회
        for (String completion : completions) {
            Integer count = participantMap.get(completion);
            if (count == null) continue;
            if (count > 1) {
                participantMap.put(completion, count - 1);
            } else {
                participantMap.remove(completion);
            }
        }

        // 해시맵에서 값이 1 이상인 참가자를 찾아서 반환
        // 시간 복잡도 O(1) - 이미 완주자 제거 후 남은 참가자는 하나이므로, 첫 번째 키를 가져오는 것으로 충분
        for (String participant : participantMap.keySet()) {
            answer = participant;
            break;
        }

        System.out.println("Map Execution time: " + java.time.Duration.between(now, LocalDateTime.now()).toMillis() + " ms");
        // total time complexity: O(n + m + n) = O(n)
        // total space complexity: O(n) - 해시맵에 참가자 수 만큼 저장
        return answer;
    }

    public String solutionBySorting(String[] participants, String[] completions) {
        LocalDateTime now = LocalDateTime.now();
        // 시간 복잡도 O(n log n) - 정렬에 의한 시간 복잡도
        // 공간 복잡도 O(n) - participant 배열과 completion 배열을 정렬하기 위한 공간
        java.util.Arrays.sort(participants);
        java.util.Arrays.sort(completions);

        for (int i = 0; i < completions.length; i++) {
            if (!participants[i].equals(completions[i])) {
                return participants[i];
            }
        }

        System.out.println("sorting Execution time: " + java.time.Duration.between(now, LocalDateTime.now()).toMillis() + " ms");
        return participants[participants.length - 1]; // 마지막 참가자가 완주하지 못한 경우
    }


    public static void main(String[] args) {
        Programmers42576 solution = new Programmers42576();
        String[] participants = {"leo", "kiki", "eden"};
        String[] completions = {"eden", "kiki"};

        System.out.println(solution.solutionByHashMap(participants, completions)); // Output: leo
        System.out.println(solution.solutionBySorting(participants, completions)); // Output: leo
    }

}
