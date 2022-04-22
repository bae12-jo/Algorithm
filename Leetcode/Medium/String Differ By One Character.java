// O(nm) solution

class Solution {
    public boolean differByOne(String[] dict) {
        
        if(dict==null || dict.length<2) return false;
        
        Set<String> set;
        
        for(int i=0; i<dict[0].length(); ++i){
            set = new HashSet<>();
            for(String s: dict){
                //StringBuilder sb = new StringBuilder(s.length());
                //sb.append(s.substring(0, i));
                //sb.append(".");
                //sb.append(s.substring(i+1));
                if(!set.add(s.substring(0, i)+s.substring(i+1))) return true;
            }
        }
        
        return false;
        
    }
}

// O(nm)solution with long testcase

class Solution {
    public boolean differByOne(String[] dict) {
        Long[] hashTable = new Long[dict.length];
        for (int i = 0; i < dict.length; i++) {
            String w = dict[i];
            long hash = 0;
            for (int j = 0; j < w.length(); j++) {
                hash = hash * 26 + (w.charAt(j) - 'a');

            }
            hashTable[i] = hash;
        }
        Set<Long> set = new HashSet<>();
        long base = 1;
        for (int i = dict[0].length() - 1; i >= 0; i--) {
            set.clear();
            for (int j = 0; j < dict.length; j++) {
                long cur = hashTable[j] - (dict[j].charAt(i) - 'a') * base;
                if (set.contains(cur)) {
                    return true;
                }
                set.add(cur);
            }
            base *= 26;
        }
        return false;
    }
}
