class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        
        result = collections.defaultdict(list)
        
        for word in strs:
            result[''.join(sorted(word))].append(word)
        
        return result.values()
