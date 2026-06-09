def solution(numbers, target):
    # 메서드
    def recursive(total_sum, seq):
        if seq == len(numbers):
            if total_sum == target:
                nonlocal answer
                answer += 1

            return

        for i in (-1, 1):
            total_sum += (numbers[seq] * i)
            recursive(total_sum, seq + 1)
            total_sum -= (numbers[seq] * i)

    # 메인 로직
    answer = 0
    recursive(0, 0)

    return answer