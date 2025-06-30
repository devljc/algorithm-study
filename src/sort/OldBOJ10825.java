package sort;

import global.FastReader;

import java.util.*;
import java.util.stream.Collectors;

public class OldBOJ10825 {
    private final FastReader reader = new FastReader();
    //0: 국어, 1: 수학, 2: 영어
    public void solution() {
        int studentsNum = reader.nextInt();
        Map<String, int[]> scoreMap = new HashMap<>();
        for (int i = 0; i < studentsNum; i++) {
            String name = reader.next();
            int[] scores = new int[3];
            for (int j = 0; j < 3; j++) {
                scores[j] = reader.nextInt();
            }
            scoreMap.put(name, scores);
        }

        Set<Map.Entry<String, int[]>> entries = scoreMap.entrySet();
        List<Map.Entry<String, int[]>> collect = entries.stream()
                .sorted(koreanDesc()
                        .thenComparing(englishAsc())
                        .thenComparing(mathDesc())
                        .thenComparing(alphabetaAsc()))
                .collect(Collectors.toList());

        collect.forEach(v -> {
            System.out.println(v.getKey());
        });
    }

    private Comparator<Map.Entry<String, int[]>> koreanDesc() {
        return (o1, o2) -> o2.getValue()[0] - o1.getValue()[0];
    }

    private Comparator<Map.Entry<String, int[]>> englishAsc() {
        return ((o1, o2) -> {
            if (o1.getValue()[0] == o2.getValue()[0]) {
                return o1.getValue()[1] - o2.getValue()[1];
            }
            return 0;
        });
    }

    private Comparator<Map.Entry<String, int[]>> mathDesc() {
        return ((o1, o2) -> {
            if (o1.getValue()[0] == o2.getValue()[0] && o1.getValue()[1] == o2.getValue()[1]) {
                return o2.getValue()[2] - o1.getValue()[2];
            }
            return 0;
        });
    }

    private Comparator<Map.Entry<String, int[]>> alphabetaAsc() {
        return (o1, o2) -> {
            if (o1.getValue()[0] == o2.getValue()[0] && o1.getValue()[1] == o2.getValue()[1] && o1.getValue()[2] == o2.getValue()[2]) {
                return o1.getKey().compareTo(o2.getKey());
            }
            return 0;
        };
    }

    public static void main(String[] args) {
        OldBOJ10825 oldBoj10825 = new OldBOJ10825();
        oldBoj10825.solution();
    }
}


