package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Programmers42627 {
    /**
     * 직렬 처리
     * 우선 순위
     * 가장 우선순위가 높은 작업을 대기 큐에서 꺼내서 하드디스크에 그 작업을 시킵니다.
     * 이때, 작업의 소요시간이 짧은 것, 작업의 요청 시각이 빠른 것, 작업의 번호가 작은 것 순으로 우선순위가 높습니다.
     * <p>
     * 반환 값:  모든 요청 작업의 반환 시간의 평균의 정수부분
     */

    public static int solution(int[][] jobs) {
        Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Task> tasks = new PriorityQueue<>();

        int time = 0;
        int idx = 0;
        int total = 0;
        int count = 0;

        while (count < jobs.length) {
            // 현재 시간까지 들어온 작업 모두 큐에 삽입
            while (idx < jobs.length && jobs[idx][0] <= time) {
                tasks.offer(new Task(idx, jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            // 큐에 작업 들어오면 처리 , 그외는 잡 배열에서 시간 스킵
            if (!tasks.isEmpty()) {
                Task task = tasks.poll();
                time += task.getInference();
                total += time - task.getRequestAt();
                count++;
            } else {
                time = jobs[idx][0];
            }
        }
        return total / jobs.length;
    }

    public static void main(String[] args) {
        int[][] jobs = {
                {0, 3},
                {1, 9},
                {3, 5}
        };
        System.out.println(solution(jobs));
    }

    public static class Task implements Comparable<Task> {

        private final int id;
        private final int requestAt;
        private final int inference;

        public int getRequestAt() {
            return requestAt;
        }

        public int getInference() {
            return inference;
        }

        public Task(int id, int requestAt, int inference) {
            this.id = id;
            this.requestAt = requestAt;
            this.inference = inference;
        }

        @Override
        public int compareTo(Task o) {
            if (this.inference != o.inference) {
                return this.inference - o.inference; // 1. 소요시간 기준
            } else if (this.getRequestAt() != o.getRequestAt()) {
                return this.getRequestAt() - o.getRequestAt();
            } else {
                return this.id - o.id;               // 3. ID 기준
            }
        }
    }
}
