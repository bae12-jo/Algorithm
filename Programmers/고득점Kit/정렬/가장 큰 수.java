import java.util.*;

class Solution {
    
    public String solution(int[] numbers) {
        
        StringBuilder sb = new StringBuilder();
        
        // convert int array to string array
        String[] strNumbers = new String[numbers.length];
        for(int i=0; i<numbers.length; ++i) strNumbers[i] = String.valueOf(numbers[i]);
        
        // sort string array with append two string in decreasing order
        Arrays.sort(strNumbers, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2){
                return (o2+o1).compareTo(o1+o2);
            }
        });
        
        // If all elements are 0, return 0
        if(strNumbers[0].equals("0")) return "0";
        
        // Otherwise, return all elements in order
        for(String strNum: strNumbers) sb.append(strNum);
        
        return String.valueOf(sb);
    }
}
