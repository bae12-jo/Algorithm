import java.util.*;
import java.util.regex.*;

public class CountWords{
	
	public static int countOccurrence(String[] word, String str){
		
		int counter = 0;
		
		HashSet<String> set = new HashSet<String>();
		for(String string: word){
			set.add(string);
		}
		
		Pattern p = Pattern.compile("[a-zA-Z]+");
		Matcher m = p.matcher(str);
		
		/*
		* Matcher 클래스 주요 메소드
		* find() : 대상 문자열과 패턴이 일치하는 경우 true를 반환하고 그 위치로 이동
		* group() :  매칭된 부분을 반환
		*/
		
		while(m.find()){
			System.out.println(m.group()); 
			if(set.contains(m.group())) counter++;
		}
		
		return counter;		
	}
	
	public static void main(String[] args){
		
		String word[] = {"welcome", "to", "geeks", "portal"};
		String str = "geeksforgeeks is a computer science portal for 31 geeks.";
		System.out.println(countOccurrence(word, str));
		
	}

}
