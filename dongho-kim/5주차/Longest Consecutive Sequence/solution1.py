# 무작위 순서의 정수 배열 nums가 주어질 때, 최장 연속된 요소 순서의 길이를 반환한다.
# O(N) 시간 복잡도 이내로 풀어야한다.

# 접근 방법
# 1. -10^9부터 10^9 까지 크기의 배열을 만들고, nums 순회하면서 숫자에 해당하는 인덱스에 값을 +1 한다. 그리고 해당 배열을 순회하면서 최장 길이를 측정한다. (O(10^18))
# 2. 최소 힙을 만들고 nums를 순회하면서 힙에 삽입한다. (O(nlogn))
# 3. 정렬해서 푼다. (O(nlogn))
# 4. Key랑 Value 모두 정수를 가지는 딕셔너리를 만들고, Key가 Value를 가리키는 의미로 사용한다.

class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        m = dict()
        for num in nums:
            if num not in m:
                m[num] = None

            if num - 1 in m:
                m[num - 1] = num
                m[num] = None

            if num + 1 in m:
                m[num] = num + 1

        length = dict()
        for k in m:
            if k not in length:
                length[k] = 1

            if k + 1 in length:
