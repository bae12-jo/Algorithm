class Solution
{
    
    static class Edge implements Comparable<Edge>{
        int to;
        int weight;
        
        Edge(int to, int weight){
            this.to=to;
            this.weight=weight;
        }
        
        @Override
        public int compareTo(Edge e){
            return this.weight - e.weight;
        }
    }
    
    //Function to find sum of weights of edges of the Minimum Spanning Tree.
    static int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) 
    {
        int count = -1;
        int result = 0;
        
        boolean visited[] = new boolean[V];
        
        PriorityQueue<Edge> q = new PriorityQueue<>();
        
        q.offer(new Edge(0, 0));
        
        
        while(!q.isEmpty()){
            if(count==V-1) break;
            
            Edge now = q.poll();
            
            if(visited[now.to]) continue;
            visited[now.to] = true;
            if(now.weight!=-1) result += now.weight;
            count++;
            
            for(ArrayList<Integer> next: adj.get(now.to)){
                int nextTo = next.get(0);
                int weight = next.get(1);
                if(!visited[nextTo]) q.offer(new Edge(nextTo, weight));
            }
        }
            
            return result;
            
    }
}
