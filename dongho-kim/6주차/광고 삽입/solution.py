def to_second(time_str):
    h, m, s = map(int, time_str.split(':'))
    return h * 3600 + m * 60 + s

def to_str(second):
    h = second // 3600
    m = (second % 3600) // 60
    s = second % 60
    return f'{h:02d}:{m:02d}:{s:02d}'

def solution(play_time, adv_time, logs):
    play_sec = to_second(play_time)
    adv_sec = to_second(adv_time)

    if play_sec <= adv_sec:
        return "00:00:00"

    total_time = [0] * (play_sec + 1)
    for log in logs:
        start_str, end_str = log.split('-')
        start_s = to_second(start_str)
        end_s = to_second(end_str)
        total_time[start_s] += 1
        total_time[end_s] -= 1

    for i in range(1, play_sec + 1):
        total_time[i] += total_time[i - 1]

    for i in range(1, play_sec + 1):
        total_time[i] += total_time[i - 1]

    max_value = total_time[adv_sec - 1]
    answer_sec = 0
    for start_s in range(1, play_sec - adv_sec + 1):
        end_s = start_s + adv_sec - 1
        curr_value = total_time[end_s] - total_time[start_s - 1]

        if curr_value > max_value:
            max_value = curr_value
            answer_sec = start_s

    return to_str(answer_sec)
