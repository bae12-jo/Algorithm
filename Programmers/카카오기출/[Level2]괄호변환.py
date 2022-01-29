def solution(p):
    # 1. 빈 문자열 출력
    if p=='':
        return p
    res = True
    cnt = 0
    for i in range(len(p)):
        # 균형 잡힌 문자열은 올바른 괄호 문자열에 포함 관계이므로 올바른 괄호 문자열 여부만 검사
        if p[i]=='(':
            cnt += 1
        else:
            cnt -= 1
        if cnt < 0:
            res = False
        if cnt == 0:
            # 3. 문자열 u가 올바른 괄호 문자열 일 때
            if res:
                # 2. 문자열을 u, v로 분리하고
                # 3-1. 문자열 u과 1단계부터 시행한 v의 결과를 출력
                return p[:i+1]+solution(p[i+1:])
            # 4. 문자열 u가 올바른 괄호 문자열이 아닐 때
            else:
                return '('+solution(p[i+1:])+')'+''.join(list(map(lambda x:'(' if x==')' else ')',p[1:i])))
