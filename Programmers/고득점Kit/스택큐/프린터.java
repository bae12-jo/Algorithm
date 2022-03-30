// 우선순위큐를 활용한 방법

import java.util.*;

class Work{
    int priorities;
    int index;
    
    Work(int p, int i){
        this.priorities = p;
        this.index = i;
    }
}

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<Work> queue = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0; i<priorities.length; ++i){
            queue.offer(new Work(priorities[i], i));
            pq.offer(priorities[i]);
        }
        
        int max = pq.peek();
        while(!pq.isEmpty()){
            while(max>queue.peek().priorities) queue.offer(queue.poll());
            answer++;
            Work w = queue.poll();
            if(w.index==location) return answer;
            if(w.priorities==max) {
                pq.poll();
                max = pq.peek();
            }
        }
        
        
        return answer;
    }
}


// 배열을 정렬해서 유동 인덱스로 접근하는 방법
/* 가장 큰 수를 만날 때까지 큐를 돌리다가, 만나면 타겟 인덱스를 줄이고 출력 횟수는 늘림, 타겟 인덱스가 0 아래로 떨어지면 타겟에 도달했다는 뜻이므로 루프 종료 */

import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int l = location;

        Queue<Integer> que = new LinkedList<Integer>();
        for(int i : priorities){
            que.add(i);
        }

        Arrays.sort(priorities);
        int size = priorities.length-1;

        while(!que.isEmpty()){
            Integer i = que.poll();
            if(i == priorities[size - answer]){
                answer++;
                l--;
                if(l <0)
                    break;
            }else{
                que.add(i);
                l--;
                if(l<0)
                    l=que.size()-1;
            }
        }

        return answer;
    }
}

