from bisect import bisect

class Solution:
    def minimumDifference(self, nums: List[int]) -> int:
        S = sum(nums) # nums의 총합
        n = len(nums) // 2 # 전체 길이의 절반

        left_nums = nums[:n] # 좌측 배열
        right_nums = nums[n:] # 우측 배열

        # 주어진 배열로 {개수: 총합 리스트} 쌍의 딕셔너리를 만들어 반환한다
        def get_cnt_sum_dict(arr):
            result = {i:[] for i in range(len(arr) + 1)}

            def recurse(i, _sum, cnt):
                if i == len(arr):
                    result[cnt].append(_sum)
                    return
                # i번 째 숫자를 선택하는 경우
                recurse(i + 1, _sum + arr[i], cnt + 1)
                # i번 쨰 숫자를 선택하지 않는 경우
                recurse(i + 1, _sum, cnt)

            recurse(0, 0, 0)
            for v in result.values():
                v.sort()
            return result

        # 좌, 우측 배열 모두 {개수:합} 쌍의 딕셔너리를 구한다.
        left_cnt_sum_dict = get_cnt_sum_dict(left_nums)
        right_cnt_sum_dict = get_cnt_sum_dict(right_nums)

        answer = 1e9
        for left_cnt in range(n + 1): # 좌측 배열의 개수를 0개부터 N개까지 보면서
            right_cnt = n - left_cnt # 우측 배열의 개수를 구한다.
            right_sums = right_cnt_sum_dict[right_cnt] # 우측 배열의 합 리스트를 구한다.

            for left_sum in left_cnt_sum_dict[left_cnt]: # 좌측 배열의 합을 모두 보면서,
                target_sum = S//2 - left_sum # 양쪽 배열의 차이가 가장 작으려면, 우측 배열에서 나와야 하는 숫자

                # target_sum을 삽입했을 때, 정렬을 유지하는 가장 왼쪽 인덱스를 반환한다.
                idx = bisect.bisect_left(right_sums, target_sum)

                if idx < len(right_sums):
                    right_sum = right_sums[idx]
                    curr_sum = left_sum + right_sum
                    other_sum = S - curr_sum
                    diff = abs(curr_sum - other_sum)
                    answer = min(answer, diff)

                if idx > 0:
                    right_sum = right_sums[idx - 1] # target_sum 미만인 마지막 값
                    curr_sum = left_sum + right_sum
                    other_sum = S - curr_sum
                    diff = abs(curr_sum - other_sum)
                    answer = min(answer, diff)

        return answer


