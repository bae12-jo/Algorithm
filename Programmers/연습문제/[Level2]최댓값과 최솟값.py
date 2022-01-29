def solution(s):
    arr = s.split()
    min = max = int(arr[0])
    for i in range(1, len(arr)):
        if int(arr[i]) < min:
            min = int(arr[i])
        if max < int(arr[i]):
            max = int(arr[i])
    answer = "{} {}".format(min, max)
    return answer
