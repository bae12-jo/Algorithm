// 시간 중심으로 짠 코드, 다리에 하나 밖에 올릴 수 없을 때는 0을 큐에 넣음

import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int limit = 0, index = 0;
        Queue<Integer> bridge = new LinkedList<>();
        
        while(true){
            // if last truck is on bridge, exit loop
            if(index==truck_weights.length) break;
            
            // if queue is full, remove first truck
            if(bridge.size()==bridge_length){
                limit-=bridge.poll();   
            // if total weight of trucks is not more than limit, add truck to bridge
            }else if(limit+truck_weights[index]<=weight){
                limit+=truck_weights[index];
                bridge.offer(truck_weights[index]);
                index++;
                answer++;
            // if total weight of trucks is over limit, add 0 to bridge
            }else{
                bridge.offer(0);
                answer++;
            }
            
        }
        
        // return minutes including extra time for last truck to pass the bridge
        return answer+bridge_length;
    }
}

// 객체 지향적으로 짠 코드

import java.util.*;

class Solution {
    class Truck {
        int weight;
        int move;

        public Truck(int weight) {
            this.weight = weight;
            this.move = 1;
        }

        public void moving() {
            move++;
        }
    }

    public int solution(int bridgeLength, int weight, int[] truckWeights) {
        Queue<Truck> waitQ = new LinkedList<>();
        Queue<Truck> moveQ = new LinkedList<>();

        for (int t : truckWeights) {
            waitQ.offer(new Truck(t));
        }

        int answer = 0;
        int curWeight = 0;

        while (!waitQ.isEmpty() || !moveQ.isEmpty()) {
            answer++;

            if (moveQ.isEmpty()) {
                Truck t = waitQ.poll();
                curWeight += t.weight;
                moveQ.offer(t);
                continue;
            }

            for (Truck t : moveQ) {
                t.moving();
            }

            if (moveQ.peek().move > bridgeLength) {
                Truck t = moveQ.poll();
                curWeight -= t.weight;
            }

            if (!waitQ.isEmpty() && curWeight + waitQ.peek().weight <= weight) {
                Truck t = waitQ.poll();
                curWeight += t.weight;
                moveQ.offer(t);
            }
        }

        return answer;
    }
}

