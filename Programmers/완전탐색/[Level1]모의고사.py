def solution(answers):
  
    # 수포자가 문제를 찍는 패턴
    sheet1 = [1, 2, 3, 4, 5]
    sheet2 = [2, 1, 2, 3, 2, 4, 2, 5]
    sheet3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]
    
    # 수포자별 득점을 저장할 리스트
    scores = [0, 0, 0]
    
    # 최다 득점자를 저장할 리스트
    answer = []
    
    # 패턴과 정답이 동일할 경우 득점+1
    # 패턴 길이로 모듈러 연산해주기
    for i, ans in enumerate(answers):
        if ans == sheet1[i%len(sheet1)]:
                scores[0] += 1
        if ans == sheet2[i%len(sheet2)]:
                scores[1] += 1
        if ans == sheet3[i%len(sheet3)]:
                scores[2] += 1
    
    # 문제별 최다 득점자의 번호 저장 (인덱스+1)
    for i, v in enumerate(scores):
	    if v == max(scores):
		    answer.append(i+1)
    
    return answer
