// 정규표현식 사용하기
// \\s+는 공백, \n. \t 같은 개행, 탭, 공백 문자를 의미한다 (\\s는 공백만)
// \\d는 숫자
// \\w는 문자

public static int countWords(String s) 
    { 
        
        if(s==null) return 0;
        
        String[] words = s.split("\\s+");
		
		for(String word: words) System.out.println(word);
        
        return words.length;
        
    }


// 직접 구현
// 이 문제에서 정규표현식을 일부러 막아뒀는지.. 1번이 안 먹혀서 아래처럼 풀어서 통과했다

class Solution 
{ 
    
    int countWords(String s) 
    { 
        
        int i = 0;
        int count = 0;
        boolean word = false;
         
        while (i < s.length())
        {
            if(s.charAt(i)==' '){
                i++;
                word = false;
            }else if(s.charAt(i)=='\\' && s.charAt(i+1)=='t'){
                i+=2;
                word = false;
            }else if(s.charAt(i)=='\\' && s.charAt(i+1)=='n'){
                i+=2;
                word = false;
            }else if(word){
                i++;
            }else{
                count++;
                word = true;
                i++;
            }
        }
        
        return count;
        
    }
}
