/* (1) Spiral BackTracking with right-hand rule
* Programming Style 1 -> constrained programming: mask visited cells as virtual obstacles
* Programming Style 2 -> backtracking : come back to the previous cell and to explore the alternative path
*

/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

class Solution {
    
    private int[][] D = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 0: up, 1: right, 2: down, 3: left
    private Set<Pair<Integer, Integer>> visited = new HashSet();
    private Robot robot;
    
    private void backtrack(){
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
    
    private void moveToClean(int row, int col, int d){
        visited.add(new Pair(row, col));
        robot.clean();
        
        for(int i=0; i<4; ++i){
            int newDir = (d+i)%4;
            int newRow = row + D[newDir][0];
            int newCol = col + D[newDir][1];
            
            if(!visited.contains(new Pair(newRow, newCol)) && robot.move()){
                moveToClean(newRow, newCol, newDir);
                backtrack();
            }
            
            robot.turnRight();
        }
            
    }
    
    public void cleanRoom(Robot robot) {
        
        this.robot = robot;
        moveToClean(0, 0, 0);
        
    }
}
