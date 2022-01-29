def solution(s):
    answer = 1000
    for i in range(1, len(s)+1):
        res = ""
        cnt = 1
        tmp = s[:i]
        
        for j in range(i, len(s)+i, i):
            if s[j:j+i] == tmp:
                cnt += 1
            else:
                if cnt == 1:
                    res += tmp
                else:
                    res += str(cnt)+tmp
                    print(res)
                tmp = s[j:j+i]
                cnt = 1
        answer = min(answer, len(res))
    return answer
