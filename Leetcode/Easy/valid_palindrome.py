class Solution:
    def isPalindrome(self, s: str) -> bool:

        # 문자열 소문자 변환
        str = s.lower()

        # 영문, 숫자 외 문자 제거
        str = re.sub('[^a-z0-9]', '', str)

        # 팰린드롬 유무 판별 (발견하지 못했다면 True 반환 후 종료)
        return str == str[::-1]
