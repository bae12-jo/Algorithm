// [2] Backtracking
// 카탈랑 수 한국어 설명: https://suhak.tistory.com/77
// worst time complexity: O(n^2)
// time complexity: O(4^n/square(n))

class Solution {
    
    // generate char array with ( and ) 
    private void generate(StringBuilder buffer, int opening, int closing, int n, List<String> combinations){
        // base case - when buffer is full
        if(buffer.length()==2*n){
            combinations.add(buffer.toString());
            return;
        }
        
        // put a opening bracket
        if(opening<n){
            buffer.append("(");
            generate(buffer, opening+1, closing, n, combinations);
            buffer.deleteCharAt(buffer.length()-1); // backtrack
        }
        // put a closing bracket after opening brackets
        if(closing<opening){
            buffer.append(")");
            generate(buffer, opening, closing+1, n, combinations);
            buffer.deleteCharAt(buffer.length()-1);
        }

    }
    
    public List<String> generateParenthesis(int n) {
        
        List<String> combinations = new ArrayList();
        // buffer, number of opening, number of closing, number of bracket pairs, result list
        generate(new StringBuilder(), 0, 0, n, combinations); 
        return combinations;
        
    }
}
