def solution(numbers, target):
    # 메서드
    def recursive(cur, temp):
        nonlocal answer

        if cur == len(numbers):
            if sum(temp) == target:
                answer += 1
            return

        for number in [-numbers[cur], numbers[cur]]:
            temp.append(number)
            recursive(cur + 1, temp)
            temp.pop()

    # 메인 로직
    answer = 0
    recursive(0, [])

    return answer