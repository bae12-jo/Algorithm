// https://the-dev.tistory.com/3 삭제 참고

class Trie {
    
    class TrieNode{
        
        private TrieNode[] links; // R links to node children -- 각 레이어마다 문자 개수만큼 포인터 필요
        
        private final int R = 26; // R is same with the number of alphabet
        
        private boolean isEnd; // accepting node (false면 단어의 시작 혹은 중간, true면 단어의 마지막)
        
        public TrieNode(){
            links = new TrieNode[R];
        }
        
        public boolean containsKey(char ch){
            return links[ch-'a']!=null;
        }
        
        public TrieNode get(char ch){
            return links[ch-'a'];
        }
        
        public void put(char ch, TrieNode node){
            links[ch-'a'] = node;
        }
        
        public void setEnd(){
            isEnd = true;
        }
        
        public boolean isEnd(){
            return isEnd;
        }        
    
    }
    
    
    private TrieNode root;
    
    public Trie() {
    
        root = new TrieNode();
        
    }
    
    // inserts a word into the trie
    public void insert(String word) {
        
        TrieNode node = root;
        
        for(int i=0; i<word.length(); ++i){
            char currChar = word.charAt(i);
            if(!node.containsKey(currChar)){
                node.put(currChar, new TrieNode());
            }
            node = node.get(currChar);
        }
        node.setEnd();
    }
    
    // search a prefix or whole key in trie and returns the node where search ends
    private TrieNode searchPrefix(String word){
        TrieNode node = root;
        
        for(int i=0; i<word.length(); ++i){
            char currKey = word.charAt(i);
            if(node.containsKey(currKey)){
                node = node.get(currKey);
            }else{
                return null;
            }
        }
        return node;
    }
    
    // return if there is any word in the trie that starts with the given prefix
    public boolean search(String word) {
        
        TrieNode node = searchPrefix(word);
        if(node==null) return false;
        return node!=null & node.isEnd();
        
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node!=null;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
