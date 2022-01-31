def solution(n, lost, reserve):

    diff_lost = set(lost) - set(reserve)
    diff_reserve = set(reserve) - set(lost)

    for ans in diff_lost:
        if ans-1 in diff_reserve:
            diff_reserve.remove(ans-1)
        elif ans+1 in diff_reserve:
            diff_reserve.remove(ans+1)
        else:
            n-=1

    return n
