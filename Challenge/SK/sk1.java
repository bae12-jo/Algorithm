import java.util.*;
import java.io.*;

// 1번 문제 - 동전 단가
// 동전 단가가 소수점 이하로 쪼개질 수 있으므로 double 붙여서 나눠주기

class sk1 {

    static class Coin implements Comparable<Coin>{
        int value;
		int pCost;
        double profit;

        Coin(int v, int c, double p){
            this.value = v;
			this.pCost = c;
            this.profit = p;
        }

        @Override
        public int compareTo(Coin o){
			if(o.profit == this.profit){
				return o.pCost > this.pCost ? 1 : -1;
			}
            return o.profit > this.profit ? 1 : -1;
        }
        
    }

    public static int solution(int money, int[] costs) {

        int[] profit = new int[6];
        int answer = 0;

        PriorityQueue<Coin> q = new PriorityQueue<>();

        q.offer(new Coin(1, costs[0], (double)1/costs[0]));
        q.offer(new Coin(5, costs[1], (double)5/costs[1]));
        q.offer(new Coin(10, costs[2], (double)10/costs[2]));
        q.offer(new Coin(50, costs[3], (double)50/costs[3]));
        q.offer(new Coin(100, costs[4], (double)100/costs[4]));
        q.offer(new Coin(500, costs[5], (double)500/costs[5]));

        while(money >= 0){
            
			if(money==0) break;
			
            Coin c = q.poll();
			//System.out.println("coin: "+c.value);
			if(c.profit>=0){
				int num = money/c.value;
				//System.out.println(num);
				money = money%c.value;
				answer += num*c.pCost;
			}
			//System.out.println("a: "+answer);
			//System.out.println("m: "+money);

        }

        return answer;
    }
	
	public static void main(String[] main){
		
		int money = 1999;
		int[] costs = {2, 11, 20, 100, 200, 600};
		
		/*
		int money = 4578;
		int[] costs = {1, 4, 99, 35, 50, 1000};
		*/
		
		System.out.println(solution(money, costs));
	}
}
