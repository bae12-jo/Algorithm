import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        double answer = 0;
        Character c1 = ' ';
        Character c2 = ' ';
        
        List<String> set1 = new ArrayList<>();
        List<String> set2 = new ArrayList<>();
        
        int i = 0;
        while(i<str1.length()-1){
            c1 = str1.charAt(i);
            c2 = str1.charAt(i+1);
            if(Character.isLetter(c1) && Character.isLetter(c2)){
                set1.add(String.valueOf(c1).toLowerCase()+String.valueOf(c2).toLowerCase());
            }
            i++;
        }
        
        i = 0;
        c1 = ' ';
        c2 = ' ';
        while(i<str2.length()-1){
            c1 = str2.charAt(i);
            c2 = str2.charAt(i+1);
            if(Character.isLetter(c1) && Character.isLetter(c2)){
                set2.add(String.valueOf(c1).toLowerCase()+String.valueOf(c2).toLowerCase());
            }
            i++;
        }
        
        Collections.sort(set1);
        Collections.sort(set2);
        
        if(set1.size()==0 && set2.size()==0) return 65536;
        
        List<String> Intersect = new ArrayList<>();
        List<String> Union = new ArrayList<>();
        
        // contains으로 하게 되면 원소를 중복 카운트 하게 됨, 한 번 비교한 원소는 지워줘야 함
        for(String s: set1){
            if(set2.remove(s)){
                Intersect.add(s);
            }
            Union.add(s);
        }
        
        for(String s: set2){
            Union.add(s);
        }
        
        double intersect = Intersect.size();
        double union = Union.size();
        answer = intersect/union;
        
        return (int)Math.floor(answer*65536);
    }
}
