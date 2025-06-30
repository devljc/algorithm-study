package queue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 시간 복잡도 O(NlogN)
 * 공간 복잡도 O(N)
 */
public class Programmers42587 {
    static class Process {
        int id;
        int priority;

        Process(int id, int priority) {
            this.id = id;
            this.priority = priority;
        }
    }

    public static int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Process> processQueue = new ArrayDeque<>();
        for (int i = 0; i < priorities.length; i++) {
            processQueue.offer(new Process(i, priorities[i]));
        }
        Arrays.sort(priorities);

        while (!processQueue.isEmpty()) {
            int priorityIdx = (priorities.length - 1) - answer;
            int priority = priorities[priorityIdx];
            Process process = processQueue.poll();
            if (process.priority == priority) {
                answer++;
                if (process.id == location) {
                    return answer;
                }
            } else {
                processQueue.offer(process);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int location = 2;
        int[] priorities = {2, 1, 3, 2};
        int solution = solution(priorities, location);
        System.out.println(solution);
    }
}
