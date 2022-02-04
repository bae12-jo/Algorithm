import heapq
def solution(scoville, K):
    answer = 0
    
    # 힙으로 변환
    heapq.heapify(scoville)
    
    # 힙의 원소가 2개 이상임을 반복 조건으로 둠
    # pop이 먼저 되어 힙이 비어서 인덱스 에러가 나는 상황에 대비
    while len(scoville)>=2:
        # 최소값 반환
        a = heapq.heappop(scoville)
        
        # 최소값이 K보다 크면 종료
        if a>=K:
            return answer
        # 최소값과 그 다음 최소값을 연산해서 힙에 추가
        else:
            b = heapq.heappop(scoville)
            heapq.heappush(scoville, a+b*2)
            answer += 1
        
    if scoville[0]>=K:
        return answer
    # 문제 해결이 불가능한 경우 -1 return
    else:
        return -1
