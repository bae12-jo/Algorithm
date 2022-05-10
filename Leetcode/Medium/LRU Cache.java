// LinkedHashMap을 사용

// input: command(create new object, put, get), integer key-value

class LRUCache extends LinkedHashMap<Integer, Integer>{
    
    private int capacity; // define maximum cache size

    public LRUCache(int capacity) {
        super(capacity, 0.75F, true); // 0.75F is default load factor, accessOrder true (no insertion order)
        this.capacity = capacity;
    }
    
    public int get(int key) {
        return super.getOrDefault(key, -1); // use parent object without any change
    }
    
    public void put(int key, int value) {
        super.put(key, value);        
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest){
        return size() > capacity; // size() is size of current linkedhashmap
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
