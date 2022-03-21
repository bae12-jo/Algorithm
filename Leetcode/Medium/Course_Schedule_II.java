class Solution {

     public int[] findOrder(int numCourses, int[][] prerequisites) {
      
         List<List<Integer>> adjList = new ArrayList<>();
         Queue<Integer> queue = new LinkedList<>();
         int[] result = new int[numCourses];
         int[] indegree = new int[numCourses];
        
         for(int i=0; i<numCourses; ++i){
             adjList.add(new ArrayList<>());
         }
         
         for(int[] course: prerequisites){
             int preCourse = course[1];
             int nextCourse = course[0];
             adjList.get(preCourse).add(nextCourse);
             indegree[nextCourse]++;
         }
         
         for(int course=0; course<numCourses; ++course){
             if(indegree[course]==0) queue.offer(course);
         }
         
         int index = 0;
         while(!queue.isEmpty()){
             int currCourse = queue.poll();
             result[index++] = currCourse;
             for(int course: adjList.get(currCourse)){
                 if(--indegree[course]==0) queue.offer(course);
             }
         }
         
         if(index!=numCourses) return new int[]{};
         return result;
     
     }

}
