// indegree list를 이용한 방법

class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        
        List<String> queue = new ArrayList<>(); // answer
        
        //Set<String> available = new HashSet<String>(); // supplies
        //for(String s: supplies) available.add(s);
        Set<String> available = Stream.of(supplies).collect(Collectors.toCollection(HashSet::new));
        
        Map<String, Set<String>> ingredientMap = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();
        
        for(int i=0; i<recipes.length; i++){
            int prerequisite = 0;
            for(String s: ingredients.get(i)){
                if(!available.contains(s)){
                    prerequisite++;
                    ingredientMap.computeIfAbsent(s, set->new HashSet<>()).add(recipes[i]);
                }
            }
            if(prerequisite==0) queue.add(recipes[i]);
            else indegree.put(recipes[i], prerequisite);
        }
        
        for(int i=0; i<queue.size(); i++){
            String readyRecipe = queue.get(i);
            if(ingredientMap.containsKey(readyRecipe)){ // get ingredients of recipe ready
                for(String recipe: ingredientMap.remove(readyRecipe)){
                    if(indegree.merge(recipe, -1, Integer::sum)==0) queue.add(recipe);
                }
            }
        }
        
        return queue;
        
    }
}


// dfs를 이용한 코드

class Solution {
    private static final int NOT_VISITED = 0;
    private static final int VISITING = 1;
    private static final int VISITED = 2;
    
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, Integer> status = new HashMap<>();
        Map<String, List<String>> prereqs = new HashMap<>();
        
        for (int i = 0; i < recipes.length; ++ i) {
            status.put(recipes[i], NOT_VISITED);
            prereqs.put(recipes[i], ingredients.get(i));
        }
        
        for (String s: supplies) {
            status.put(s, VISITED);
        }
        
        List<String> output = new ArrayList<>();
        for (String s: recipes) {
            dfs (s, prereqs, status, output);
        }
        
        return output;
    }
    
    public boolean dfs(String s, Map<String, List<String>> prereqs, Map<String, Integer> status, List<String> output) {
        if (!status.containsKey(s)) {
            return false;
        }
        
        if (status.get(s) == VISITING) {
            return false;
        }
        
        if (status.get(s) == VISITED) {
            return true;
        }
        
        status.put(s, VISITING);
        for (String p: prereqs.get(s)) {
            if (!dfs(p, prereqs, status, output)) {
                return false;
            }
        }
        status.put(s, VISITED);
        output.add(s);
        
        return true;
    }
}
