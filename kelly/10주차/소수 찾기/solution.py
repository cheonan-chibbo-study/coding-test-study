from itertools import permutations

def solution(numbers):
    # 메서드
    def get_candi_set():
        result = set()
        for i in range(1, len(numbers) + 1):
            for p in permutations(numbers, i):
                result.add(int("".join(p)))

        return result

    def is_target(num):
        if num < 2:
            return False

        for i in range(2, int(num ** 0.5) + 1):
            if num % i == 0:
                return False

        return True

    # 메인 로직
    candi_set = get_candi_set()
    print(candi_set)

    answer = 0
    for candi in candi_set:
        if is_target(candi):
            answer += 1

    return answer