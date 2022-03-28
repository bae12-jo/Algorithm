import java.util.*;

class Solution {
    
    static HashSet<Integer> permutation = new HashSet<>();
    
    // Check if it is prime or not
    public boolean isPrime(int num){
        
        if(num<2) return false;
        
        for(int i=2; i*i<=num; ++i){
            if(num%i==0) return false;
        }
        return true;
    }
    
    public void getPermutation(String prefix, String others){
        
        // Add prefix itself to HashSet
        if(!prefix.equals("")){
            permutation.add(Integer.parseInt(prefix));
        }
        
        // Call func recursively with prefix plus one from others and others except index i
        for(int i=0; i<others.length(); ++i){
            getPermutation(prefix+others.charAt(i), others.substring(0, i)+others.substring(i+1));
        }
    }
    
    public int solution(String numbers) {
        int answer = 0;
        
        // Get permutation from given numbers
        getPermutation("", numbers);
        
        // Check every number in hash set
        Iterator it = permutation.iterator();
        while(it.hasNext()){
            if(isPrime((int)it.next())) answer++;
        }
        
        return answer;
    }
}
