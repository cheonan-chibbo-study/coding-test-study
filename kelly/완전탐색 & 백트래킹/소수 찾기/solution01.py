def solution(numbers):
    nums = [n for n in numbers]

    # 메서드
    def recursive(temp):
        if len(temp) == len(nums):
            return

        for i in range(len(nums)):
            if visited[i]:
                continue

            temp.append(nums[i])
            visited[i] = True
            candi.add(int(''.join(temp)))
            recursive(temp)

            temp.pop()
            visited[i] = False

    def is_target(num):
        if num in (0, 1):
            return False

        if num in (2, 3):
            return True

        for i in range(2, int(num ** 0.5) + 1):
            if num % i == 0:
                return False

        return True

    # 메인 로직
    candi = set()
    visited = [False] * len(nums)
    recursive([])

    answer = 0
    for c in candi:
        if is_target(c):
            answer += 1

    return answer