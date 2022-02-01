class Solution:
    def reorderLogFiles(self, logs: List[str]) -> List[str]:
        
        # split letter and digit logs by index 1
        letter, digit = [], []
        for log in logs:
            if log.split()[1].isdigit():
                digit.append(log)
            else:
                letter.append(log)
        
        # sort letter by content first, indentifier second
        letter.sort(key=lambda x: (x.split()[1:], x.split()[0]))
        
        # return letter and digit resepectively
        return letter + digit
