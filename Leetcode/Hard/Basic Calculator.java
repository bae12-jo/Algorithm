class Solution {
    public int calculate(String s) {
        
        Deque<Integer> stack = new ArrayDeque<>();
        int currNum = 0;
        int tmpRes = 0; // on-going res before parenthesis
        int sign = 1; // positive 1, negative -1
        
        for(int i=0; i<s.length(); i++){
            char currChar = s.charAt(i);
            
            // get current number
            if(Character.isDigit(currChar)) currNum = currNum*10 + currChar-'0';
            // get operators or parenthesis
            else if(currChar=='+'){
                tmpRes += sign*currNum;
                sign=1; // pass operator to next turn
                currNum=0;
            }else if(currChar=='-'){
                tmpRes += sign*currNum;
                sign=-1; // pass operator to next turn
                currNum=0;
            }else if(currChar=='('){
                stack.addLast(tmpRes);
                stack.addLast(sign); // pass operator to next turn
                sign=1;
                tmpRes=0;
            }else if(currChar==')'){
                tmpRes += sign*currNum;
                tmpRes *= stack.pollLast(); // adjuest unary operator
                tmpRes += stack.pollLast(); // add previous num before '('
                currNum=0;
            }
        }
        
        return tmpRes + (sign*currNum);
    }
}
