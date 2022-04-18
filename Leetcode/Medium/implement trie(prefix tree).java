class Trie {
    
    class TrieNode{
        
        private TrieNode[] links; // R links to node children -- 각 레이어마다 문자 개수만큼 포인터 필요
        
        private final int R = 26; // R is same with the number of alphabet
        
        private boolean isEnd; // accepting node (false면 단어의 시작 혹은 중간, true면 단어의 마지막)
        
        public TrieNode(){
            links = new TrieNode[R];
        }
        
        public boolean containKeys(char c){
            return links[ch-'a']!=null;
        }
        
        pubilc TrieNode get(char c){
            return links[ch-'a'];
        }
        
        public void put(char ch, TrieNode node){
            linkes[ch-'a'] = node;
        }
        
        
    
    }

    public Trie() {
        
    }
    
    public void insert(String word) {
        
    }
    
    public boolean search(String word) {
        
    }
    
    public boolean startsWith(String prefix) {
        
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
