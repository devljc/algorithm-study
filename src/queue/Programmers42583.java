package queue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 문제: 다리를 지나는 트럭 (복습)
 * <p>
 * 입력:
 * - bridge_length: 다리에 동시에 지나갈수 있는 차량 수, 1 <= N <= 10000
 * - weight: 하중, 1 <= W <= 10000
 * - truck_weights: 차량 무게 배열 1<= TW <= weight
 * <p>
 * 시간 복잡도 O(N)
 * 공간 복잡도 O(N)
 */
public class Programmers42583 {

    private static class Truck {
        int weight;
        int enterTime;

        public Truck(int weight, int enterTime) {
            this.weight = weight;
            this.enterTime = enterTime;
        }
    }

    private final Queue<Truck> bridge = new ArrayDeque<>();
    private int time = 0;
    private int currentWeight = 0;
    private int idx = 0;

    private int solution(int bridge_length, int weight, int[] truck_weights) {
        int total = truck_weights.length;
        while (idx < total || !bridge.isEmpty()) {
            time++;
            if (!bridge.isEmpty()) exit(time, bridge_length);
            if (idx < total) enter(weight, truck_weights);

        }
        return time;
    }

    //OUT: 다리를 빠져나간 트럭이 있다면 제거하고 하중 감소
    private void exit(int currentTime, int bridge_length) {
        Truck out = bridge.peek();
        // 현재 시간 - 진입 시간 = 지나온 시간 , 즉 총 다리 길이 이상이 되어야 통과
        int enterTime = currentTime - out.enterTime;
        if (bridge_length <= enterTime) {
            currentWeight -= bridge.poll().weight;
        }
    }

    //IN: 무게 합산이 다리 하중 이내 인지 확인
    private void enter(int weight, int[] truck_weights) {
        if (currentWeight + truck_weights[idx] <= weight) {
            bridge.offer(new Truck(truck_weights[idx], time));
            currentWeight += truck_weights[idx];
            idx++;
        }
    }

    public static void main(String[] args) {
        int bridgeLen = 100;
        int weight = 100;
        int[] truck_weights = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        Programmers42583 programmers42583 = new Programmers42583();
        System.out.println(programmers42583.solution(bridgeLen, weight, truck_weights));
    }
}
