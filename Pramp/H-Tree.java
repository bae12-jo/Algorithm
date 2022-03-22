/*
 * H-Tree Construction

An H-tree is a geometric shape that consists of a repeating pattern resembles the letter “H”.

It can be constructed by starting with a line segment of arbitrary length, drawing two segments of the same length at right angles to the first through its endpoints, and continuing in the same vein, reducing (dividing) the length of the line segments drawn at each stage by √2.

Here are some examples of H-trees at different levels of depth:
IMAGES MISSING : 

at depth = 1  THERE IS ONLY ONE H

at depth = 2  THERE IS ONE H AND SURROUNDED BY 4 OTHER H'S FROM EACH CORNER (TOTALLY 5)

at depth = 3  THERE IS ONE H SURROUNDED BY 4 OTHER H'S (TOTAL 5) AND EACH OF 4 H'S SURROUNDED BY 16 H'S (TOTAL 21)




Write a function drawHTree that constructs an H-tree, given its center (x and y coordinates), a starting length, and depth. Assume that the starting line is parallel to the X-axis.

Use the function drawLine provided to implement your algorithm. In a production code, a drawLine function would render a real line between two points. However, this is not a real production environment, so to make things easier, implement drawLine such that it simply prints its arguments (the print format is left to your discretion).

Analyze the time and space complexity of your algorithm. In your analysis, assume that drawLine's time and space complexities are constant, i.e. O(1).

Constraints:

[time limit] 5000ms

[input] double x

[input] double y

[input] double length

[input] double depth

0 ≤ depth ≤ 10
 */

//SOLUTION:


import java.util.*;

class Solution {
  
  public static void drawLine(int x1, int y1, int x2, int y2){
    // Assume this will draw line in production
  }
  
  public static class void drawHTree(double x, double y, double length, double depth){
    
    // Edge Case Covering
    if(depth==0) return;
    
    // draw - (parallel to X-axis) 
    int x1 = x - length >> 1;
    int x2 = x + length >> 1;
    drawLine(x1, y, x2, y);
    
    int nextLength = length / Math.sqrt(2); // length / 2**(1/2)
    
    // draw left |
    int y1 = y - length >> 1;
    int y2 = y + length >> 1;
    drawLine(x1, y1, x1, y2);
    // call drawHTree on left vertiacal line
    drawTree(x1, y1, nextLength, depth--);
    drawTree(x1, y2, nextLength, depth--);
    
    // draw right |
    drawLine(x2, y1, x2, y2);
    // call drawHTree on right vertiacal line
    drawTree(x2, y1, nextLength, depth--);
    drawTree(x2, y2, nextLength, depth--);
    
    return x;
  }
  
  public static void main( String args[] ) {
    
  }
  
}
