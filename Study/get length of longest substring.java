// 최장 부분 문자 수열의 길이 구하기

public int lengthOfLongestSubstring(String s){

    int maxLen = 0;

    HashMap<Character, Integer> map = new HashMap<>();
    for(int start=0, end=0; end<s.length(); end++){
        if(map.containsKey(s.charAt(end))) start = Math.max(map.get(s.charAt(end)), start);

        maxLen = Math.max(maxLen, end-start+1);
        map.put(s.charAt(end), end+1);
    }

    return maxLen;
}
