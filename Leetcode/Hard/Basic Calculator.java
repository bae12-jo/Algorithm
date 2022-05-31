// stack

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


// recursion


class Solution {
    
    public int calculate(String s) {
        
        // init queue
        Queue<Character> tokens = new ArrayDeque<Character>();
        for(char c: s.toCharArray()) if(c!=' ') tokens.offer(c);
        tokens.offer('+'); // to calculate last tmp sum with prev one
        
        return calculate(tokens);
    }
    
    private int calculate(Queue<Character> tokens){
        
        char preOp = '+'; // base operators is +
        int num = 0, sum = 0, prev = 0; // current number, on-going result, lase number which is not calculated yet
        
        while(!tokens.isEmpty()){
            
            char c = tokens.poll();
            if(Character.isDigit(c)) num = num*10 + c-'0';
            else if(c=='(') num = calculate(tokens);
            else{
                switch(preOp){
                    case '+':
                        sum += prev;
                        prev = num; // signed num
                        break;
                    case '-':
                        sum += prev;
                        prev = -num; // signed num
                        break;
                }
                
                if(c==')') break; // go prev func
                preOp = c; // update operator
                num = 0; // init current number
            }   
        }
        
        return sum + prev;
        
    }
}
