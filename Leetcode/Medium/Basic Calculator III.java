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
                    case '*':
                        prev *= num;
                        break;
                    case '/':
                        prev /= num;
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
