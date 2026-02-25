class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        # 메인 로직
        answer = 0

        #✅ 각 숫자를 key값으로 하여 해시테이블을 만든다.
        num_dict = {}
        for num in nums:
            num_dict[num] = True

        #✅ 해시테이블을 순회한다.
        for num in num_dict:
            #✅ 만약 이전 숫자가 존재하지 않는다면, 개수를 1로 초기화한다.
            if num - 1 not in num_dict:
                cnt = 1
                target = num + 1

                #✅ 다음 숫자가 존재할 때까지 개수를 1씩 증가시킨다.
				#✅ 연속된 숫자 개수 최댓값을 업데이트한다.
                while target in num_dict:
                    target += 1
                    cnt += 1

                answer = max(answer, cnt)

        return answer