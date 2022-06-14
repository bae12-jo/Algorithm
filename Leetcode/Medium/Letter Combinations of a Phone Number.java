// brute force polynomial O(n^4) n=3, 81
// backtracking exponential*2 O(4^n) n=3, 64
// backtracking is more efficient as n increases

class Solution {
    
    private List<String> res = new ArrayList<String>();
    private HashMap<Character, String> phone = new HashMap<Character, String>(){{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "qprs");
        put('8', "tuv");
        put('9', "wxyz");        
    }}; 
    
    private void backtrack(int idx, StringBuilder buffer, String digits){
        // base condition
        if(buffer.length()==digits.length()){
            res.add(buffer.toString());
            return;
        }
        
        // get letters based on digit
        String letters = phone.get(digits.charAt(idx));
        
        for(char letter: letters.toCharArray()){
            buffer.append(letter); // add letter to result
            backtrack(idx+1, buffer, digits); // recursion
            buffer.deleteCharAt(buffer.length()-1); // backtrack
        }
    }
    
    public List<String> letterCombinations(String digits) {
        
        // edge case
        if(digits.length()==0) return res;
        
        backtrack(0, new StringBuilder(), digits);
        
        return res;
        
    }
}
