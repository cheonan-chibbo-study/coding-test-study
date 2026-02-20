from heapq import heapify, heappush, heappop

class Solution:
    def smallestRange(self, nums: List[List[int]]) -> List[int]:
        # 전역 데이터
        k = len(nums)

        # 메인 로직
        min_value = float('inf')
        max_value = float('-inf')
        pq = []
        for i in range(k):
            target = nums[i][0]
            min_value = min(min_value, target)
            max_value = max(max_value, target)
            heappush(pq, (target, i))

        answer = [min_value, max_value]
        smallest_range = max_value - min_value

        list_indexes = [0] * k
        while True:
            value, idx = heappop(pq)
            list_indexes[idx] += 1

            if list_indexes[idx] == len(nums[idx]):
                break

            target = nums[idx][list_indexes[idx]]
            heappush(pq, (target, idx))

            min_value = pq[0][0]
            max_value = max(max_value, target)
            new_range = max_value - min_value

            if new_range < smallest_range:
                smallest_range = new_range
                answer = [min_value, max_value]

        return answer