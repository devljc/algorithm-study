package hash;

import java.util.*;
import java.util.stream.Collectors;


public class Programmers42579 {

    public static class PlayInfo implements Comparable<PlayInfo> {
        int index;
        int count;

        public PlayInfo(int index, int count) {
            this.index = index;
            this.count = count;
        }

        @Override
        public int compareTo(PlayInfo other) {
            if (this.count != other.count) {
                return Integer.compare(other.count, this.count); // 내림차순
            }
            return Integer.compare(this.index, other.index); // 오름차순
        }
    }

    //시간 복잡도  - O
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        Map<String, List<PlayInfo>> playCountMap = getPlaySortedCountMap(genres, plays);
        List<String> rankedGenreList = getRankedGenreList(genres, plays);
        for (String genre : rankedGenreList) {
            List<PlayInfo> playInfos = playCountMap.get(genre);
            answer.add(playInfos.get(0).index);
            if (playInfos.size() > 1) {
                answer.add(playInfos.get(1).index);
            }
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }

    private List<String> getRankedGenreList(String[] genres, int[] plays) {
        Map<String, Integer> genrePlayCount = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            genrePlayCount.merge(genres[i], plays[i], Integer::sum);
        }

        return genrePlayCount.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private Map<String, List<PlayInfo>> getPlaySortedCountMap(String[] genres, int[] plays) {
        Map<String, List<PlayInfo>> playCountMap = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int playCount = plays[i];
            // 장르별로 플레이 횟수를 저장하는 맵 생성
            playCountMap
                    .computeIfAbsent(genre, k -> new ArrayList<>())
                    .add(new PlayInfo(i, playCount));

        }

        for (List<PlayInfo> list : playCountMap.values()) {
            Collections.sort(list);
        }

        return playCountMap;
    }
}
