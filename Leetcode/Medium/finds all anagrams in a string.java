class Solution {
    
    private int[] letterP = new int[26]; // Lowercase English Letter for p
    private int[] letterS = new int[26]; // Lowercase English Letter for s
    
    public List<Integer> findAnagrams(String s, String p) {
        
        // edge case
        if(s.length()<p.length()) return Collections.emptyList();
        
        // get num of each char in string p
        for(char c: p.toCharArray()) letterP[c-'a']++;
        
        int sl = s.length();
        int pl = p.length();
        
        List<Integer> res = new ArrayList<>();
        
        // init letterS
        for(int i=0; i<pl; i++) letterS[s.charAt(i)-'a']++;
        if(Arrays.equals(letterP, letterS)) res.add(0);
        
        int left = 0, right = pl;
        while(right<sl){
            letterS[s.charAt(left)-'a']--;
            letterS[s.charAt(right)-'a']++;
            if(Arrays.equals(letterP, letterS)) res.add(left+1);
            left++;
            right++;
        }
        
        return res;        
        
    }
}
