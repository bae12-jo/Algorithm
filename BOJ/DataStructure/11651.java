import java.io.*;
import java.util.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine());
        
        PriorityQueue<Point> pq = new PriorityQueue<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.add(new Point(x, y));
        }
 
        while(!pq.isEmpty())
            System.out.println(pq.poll());
    }
}
 
class Point implements Comparable<Point>{
    int x, y;
 
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
 
    @Override
    public String toString() {
        return x + " " + y;
    }
 
    @Override
    public int compareTo(Point target) {
        if(this.y != target.y ) return this.y - target.y;
        return this.x - target.x;
    }  
}
