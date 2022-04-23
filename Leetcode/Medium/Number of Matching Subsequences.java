//String: abcde
//words : abde -> a (abde, 1) -> b (abde, 2) -> d (abde, 3) -> e (abde, 4) -> 5 == 5
//        atse -> a (atse, 1) -> t (atse, 2) -> t (atse, 2)
//        ebcie -> e (ebcie, 1) -> e (ebcie, 1) -> e (ebcie, 1)

// 1. bucket array to store each word based on first character - 26
    
// 2. put words to bucket array based on its first character
    
// 3. search each character of string s, find out if there is subsequnce starts with the character
//    get every pair(word, index) from bucket and clear it
//    if there is, add 1 to index (for movind pointer to next index of subsequnces)
//    and put (word, index) to bucket again
//    if index of word is same with length of string ->  it's subsequences!
   

class Solution {
    
    public int numMatchingSubseq(String s, String[] words) {
        
        int ans = 0;
        
        // 1. bucket array to store each word based on first character
        ArrayList<CharNode>[] charBucket = new ArrayList[26];
        for(int i=0; i<26; ++i){
            charBucket[i] = new ArrayList<CharNode>();
        }
        
        for(String word: words){
            // 2. put all words as pair (word, index), index is 0 as default
            charBucket[word.charAt(0)-'a'].add(new CharNode(word, 0));
        }
        
        // search each character of string s, find out if there is subsequnce starts with the character
        for(char c: s.toCharArray()){
            ArrayList<CharNode> oneCharBucket = charBucket[c-'a']; // all words starts with a character of string
            charBucket[c-'a'] = new ArrayList<CharNode>(); // clear original array starts with a character
            
            if(oneCharBucket.size()==0) continue;
            
            // make new pair starts with next character and update index
            for(CharNode ch: oneCharBucket){
                ch.index++;
                if(ch.index == ch.word.length()) ans++; // it's a subsequence!
                else charBucket[ch.word.charAt(ch.index)-'a'].add(ch); // add new pair to charBucket
            }
            oneCharBucket.clear();
        }
        
        
        return ans;
        
    }
}

class CharNode{
    String word;
    int index;

    public CharNode(String word, int index){
        this.word = word;
        this.index = index;
    }
}
