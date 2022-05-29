class Solution {
    public int evalRPN(String[] tokens) {
        
        Stack<Integer> stack = new Stack<Integer>();
        
        for(String s: tokens){
            switch(s){
                case "+":
                    stack.push(stack.pop()+stack.pop());
                    break;
                case "-":
                    stack.push(stack.pop()*-1+stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop()*stack.pop());
                    break;
                case "/":
                    int divisor = stack.pop();
                    int dividend = stack.pop();
                    stack.push(dividend/divisor);
                    break;
                default:
                    stack.push(Integer.valueOf(s));
                    break;
            }
        }
        
        int result = 0;
        for(int n: stack) result += n;
        
        return result;
        
    }
}
