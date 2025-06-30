package hash;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Programmers1845 {
    // 시간 복잡도: O(n) - nums 배열을 한 번 순회하여 Set에 저장
    // 공간 복잡도: O(n) - Set에 중복 제거된 포켓몬 종류를 저장
    public int solution(int[] nums) {
        // 중복 제거를 위해 Set 사용
        // 총 종류의 수는 nums.length / 2
        // set 길이가 nums.length / 2보다 크면 nums.length / 2를 반환
        int maxCount = nums.length / 2;
        Set<Integer> collect = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        return Math.min(maxCount, collect.size());
    }
}
