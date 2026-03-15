def solution(play_time, adv_time, logs):
    def time_to_second(time):
        hour, minute, second = tuple(map(int, time.split(":")))

        result = 0
        result += (hour * 3600)
        result += (minute * 60)
        result += second
        return result

    play_time_seconds = time_to_second(play_time)
    adv_seconds = time_to_second(adv_time)

    if play_time_seconds <= adv_seconds:
        return "00:00:00"

    # imos
    arr = [0] * (play_time_seconds + 1)
    for log in logs:
        start_time, end_time = tuple(log.split("-"))
        start_sec = time_to_second(start_time)
        end_sec = time_to_second(end_time)
        arr[start_sec] += 1
        arr[end_sec] -= 1

    for i in range(1, len(arr)):
        arr[i] += arr[i - 1]

    # 총합 계산을 위한 2차 누적합
    for i in range(1, len(arr)):
        arr[i] += arr[i - 1]

    max_score = -float('inf')
    answer = -1
    for s in range(len(arr)):
        e = s + adv_seconds - 1
        if e >= len(arr): break

        score = 0
        if s == 0:
            score = arr[e]
        else:
            score = arr[e] - arr[s - 1]

        if score > max_score:
            max_score = score
            answer = s

    def second_to_time(sec):
        hour = sec // 3600
        minute = (sec % 3600) // 60
        second = sec % 60
        return f"{hour:02d}:{minute:02d}:{second:02d}"

    return second_to_time(answer)
