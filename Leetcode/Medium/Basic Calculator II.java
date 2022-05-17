// main idea -> 1+2 -> +1+2 (there is hidden + before first num)

class Solution {
    public int calculate(String s) {
        
        
        if(s == null || s.isEmpty()) return 0;
        
        Deque<Integer> stack = new ArrayDeque<>(); // add calculated num with prev operators
        
        int len = s.length();
        int currNum = 0;
        char operators = '+'; // to add first num into stack
        
        for(int i=0; i<len; i++){
            
            char currChar = s.charAt(i);
            
            // get operands
            if(Character.isDigit(currChar)) currNum = currNum*10 + currChar-'0';
            
            // get operators (operators will be passed to the next iteration)
            if(!Character.isDigit(currChar) && !Character.isWhitespace(currChar) || i==len-1){
                if(operators=='+') stack.addLast(currNum); // default for first number
                else if(operators=='-') stack.addLast(-currNum);
                else if(operators=='*') stack.addLast(stack.pollLast()*currNum);
                else if(operators=='/') stack.addLast(stack.pollLast()/currNum);
                operators = currChar; // push operators to next num
                currNum=0; // clear curr number
            }
                
        }
        
        int res = 0;
        while(!stack.isEmpty()) res += stack.pollFirst();
        
        return res;        
    }
}
