/* entrySet을 이용한 해시맵 정렬 */
import java.util.*;

class Solution {
    
    static class Genre implements Comparable<Genre>{
        
        String name;
        int count;
        
        Genre(String name, int count){
            this.name = name;
            this.count = count;
        }
        
        @Override
        public int compareTo(Genre g){
            return g.count - this.count;
        }
    }
    
    List<Integer> result = new ArrayList<>();
    // Set <name of genre, total play time>
    HashMap<String, Integer> playCounts = new HashMap<>();
    // Sort genre based on total play time
    PriorityQueue<Genre> genreOrder = new PriorityQueue<Genre>();
    // Set <name of genre, information of genre>
    HashMap<String, Object> playList = new HashMap<>();
    
    public int[] solution(String[] genres, int[] plays) {
        
        // Set <number of music, play time>
        HashMap<Integer, Integer> info;
        
        for(int i=0; i<genres.length; ++i){
            
            // Update total play time of each genres
            playCounts.put(genres[i], playCounts.getOrDefault(genres[i], 0)+plays[i]);
            
            // Store information of each music
            if(playList.containsKey(genres[i])){
                info = (HashMap<Integer, Integer>)playList.get(genres[i]);
            }else{
                info = new HashMap<Integer, Integer>();
            }
            info.put(i, plays[i]);
            playList.put(genres[i], info);
            
        }
        
        for(String s: playCounts.keySet()){
            genreOrder.offer(new Genre(s, playCounts.get(s)));
        }
        
        while(!genreOrder.isEmpty()){
            Genre g = genreOrder.poll();
            String key = g.name;
            
            playList.get(key);
            info = (HashMap<Integer, Integer>)playList.get(key);
            List<Map.Entry<Integer, Integer>> entryList = new LinkedList<>(info.entrySet());
            entryList.sort(new Comparator<Map.Entry<Integer, Integer>>(){
                @Override
                public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2){
                    return o2.getValue() - o1.getValue();
                }
            });
            
            int count = 0;
            for(Map.Entry<Integer, Integer> entry : entryList){
                if(count==2) break;
                result.add(entry.getKey());
                count++;
            }            
        }
        
        int[] answer = new int[result.size()];
        
        for(int i=0; i<result.size(); ++i){
            answer[i] = result.get(i);
        }        
        
        return answer;
    }
}

/* 정렬 결과 조회에 iterator 사용 */

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Collections;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Object> genresMap = new HashMap<String, Object>();      //<장르, 곡 정보> 
        HashMap<String, Integer> playMap = new HashMap<String, Integer>(); //<장르, 총 장르 재생수>
        ArrayList<Integer> resultAL = new ArrayList<Integer>();

        for(int i = 0; i < genres.length; i++){
            String key = genres[i];
            HashMap<Integer, Integer> infoMap;       // 곡 정보 : <곡 고유번호, 재생횟수>

            if(genresMap.containsKey(key)){
                 infoMap = (HashMap<Integer, Integer>)genresMap.get(key);
            }
            else {
                infoMap = new HashMap<Integer, Integer>();
            }

            infoMap.put(i, plays[i]);
            genresMap.put(key, infoMap);

            //재생수
            if(playMap.containsKey(key)){
                playMap.put(key, playMap.get(key) + plays[i]);
            }
            else {
                playMap.put(key, plays[i]);
            }
        }

        int mCnt = 0;
        Iterator it = sortByValue(playMap).iterator();

        while(it.hasNext()){
            String key = (String)it.next();
            Iterator indexIt = sortByValue((HashMap<Integer, Integer>)genresMap.get(key)).iterator();
            int playsCnt = 0;

            while(indexIt.hasNext()){
                resultAL.add((int)indexIt.next());
                mCnt++;
                playsCnt++;
                if(playsCnt > 1) break;
            }
        }

        int[] answer = new int[resultAL.size()];

        for(int i = 0; i < resultAL.size(); i++){
            answer[i] = resultAL.get(i).intValue();
        }

        return answer;
    }

    private ArrayList sortByValue(final Map map){
        ArrayList<Object> keyList = new ArrayList();
        keyList.addAll(map.keySet());

        Collections.sort(keyList, new Comparator(){
            public int compare(Object o1, Object o2){
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);

                return ((Comparable) v2).compareTo(v1);
            }
        });

        return keyList;
    }
}
