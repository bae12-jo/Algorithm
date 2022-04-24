class Solution {
    public boolean isValid(String s) {
        
        HashMap<Character, Character> brackets = new HashMap<>(){{
            put('(', ')');
            put('{', '}');
            put('[', ']');
        }};
        
        Stack<Character> st = new Stack<>();
        
        for(char c: s.toCharArray()){
            // opening brackets
            if(brackets.containsKey(c)){
                st.push(c);
                continue;
            }
            // closing brackets at least second place
            else if(!st.isEmpty() && !brackets.containsKey(c)){
                if(brackets.get(st.peek())==c){
                    st.pop();
                    continue;
                }                
            }
            // closing brackets at first place
            return false;
        }
        
        if(!st.isEmpty()) return false;
        return true;
        
    }
}
