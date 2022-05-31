// Top-down

class Solution {
    public int longestStrChain(String[] words) {
        
        Set<String> wordSet = new HashSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        Collections.addAll(wordSet, words);
        int maxLen = 0;
        for(String word: words) maxLen = Math.max(maxLen, longestStrChain(word, wordSet, map)); // O(N)
        return maxLen;
        
    }
    
    private int longestStrChain(String word, Set<String> wordSet, Map<String, Integer> map){
        
        // memoization
        if(map.containsKey(word)) return map.get(word);
        
        int maxLen = 1;
        StringBuilder sb = new StringBuilder(word);
        
        for(int i=0; i<word.length(); i++){ // O(L)
            sb.deleteCharAt(i);
            String newWord = sb.toString(); // O(L)
            
            if(wordSet.contains(newWord)){
                int currLen = 1+longestStrChain(newWord, wordSet, map);
                maxLen = Math.max(currLen, maxLen);
            }
            sb.insert(i, word.charAt(i));
        }
        // memoization
        map.put(word, maxLen);
        
        return maxLen;
        
    }
}

// Bottom-up

class Solution {
    public int longestStrChain(String[] words) {
        Map<String, Integer> dp = new HashMap<>(); // Space: O(N)

        // Sorting the list in terms of the word length.
        Arrays.sort(words, (a, b) -> a.length() - b.length()); // O(NlogN)

        int longestWordSequenceLength = 1;

        for (String word : words) {
            int presentLength = 1;
            // Find all possible predecessors for the current word by removing one letter at a time.
            for (int i = 0; i < word.length(); i++) { // O(L)
                StringBuilder temp = new StringBuilder(word);
                temp.deleteCharAt(i);
                String predecessor = temp.toString(); // O(L)
                int previousLength = dp.getOrDefault(predecessor, 0);
                presentLength = Math.max(presentLength, previousLength + 1);
            }
            dp.put(word, presentLength);
            longestWordSequenceLength = Math.max(longestWordSequenceLength, presentLength);
        }
        return longestWordSequenceLength;
    }
}
