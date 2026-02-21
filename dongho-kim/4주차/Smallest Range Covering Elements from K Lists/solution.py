from heapq import heappush, heappop

class Solution:
    def smallestRange(self, nums: List[List[int]]) -> List[int]:
        min_heap = []
        curr_max = -float('inf')

        # 1. 초기 세팅: 각 리스트의 첫 번째 요소를 힙에 삽입
        for i in range(len(nums)):
            # (값, 리스트 인덱스, 해당 리스트 내 요소 인덱스)
            val = (nums[i][0], i, 0)
            heappush(min_heap, val)
            curr_max = max(curr_max, nums[i][0])

        answer = [-float('inf'), float('inf')]

        while min_heap:
            curr_min, list_idx, val_idx = heappop(min_heap)

            # 현재 구간 [curr_min, curr_max]이 더 작은지 확인
            if curr_max - curr_min < answer[1] - answer[0]:
                answer = [curr_min, curr_max]

            # 최솟값을 뽑았던 리스트에서 다음 요소를 가져옴
            if val_idx + 1 < len(nums[list_idx]):
                next_val = nums[list_idx][val_idx + 1]
                heappush(min_heap, (next_val, list_idx, val_idx + 1))
                # 최댓값 갱신 (리스트 순회 없이 O(1)로 가능)
                curr_max = max(curr_max, next_val)
            else:
                # 하나의 리스트라도 소진되면 더 이상 모든 리스트를 포함할 수 없음
                break

        return answer
