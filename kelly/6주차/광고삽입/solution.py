def solution(play_time, adv_time, logs):
    # 메서드
    def str_to_int(target):
        h, m, s = map(int, target.split(":"))
        return h * 3600 + m * 60 + s

    def int_to_str(target):
        h = str(target // 3600) if target // 3600 >= 10 else "0" + str(target//3600)
        target %= 3600
        m = str(target // 60) if target // 60 >= 10 else "0" + str(target // 60)
        s = str(target % 60) if target % 60 >= 10 else "0" + str(target % 60)

        return h + ":" + m + ":" + s

    # 메인 로직
    answer = 0

    #✅ 문자열로 주어진 시간을 정수형 단위시간(초)으로 변환하기
    unit_play_time, unit_adv_time = str_to_int(play_time), str_to_int(adv_time)
    dp = [0] * (unit_play_time + 1)

    for log in logs:
        start_time, end_time = log.split("-")
        start_time, end_time = str_to_int(start_time), str_to_int(end_time)

        #✅ 시작시간에 +1, 종료시간에 -1
        dp[start_time] += 1
        dp[end_time] -= 1

    #✅ DP테이블을 순회하며 기록된 정보를 통해 구간별 시청자 수를 기록한다.
    for i in range(1, unit_play_time):
        dp[i] = dp[i] + dp[i - 1]

    #✅ DP테이블을 순회하며 구간별 시청자 수를 통해 누적 시청자 수를 기록한다.
    for i in range(1, unit_play_time):
        dp[i] = dp[i] + dp[i - 1]

    #✅ DP테이블을 순회하며 광고 시간동안 누적 시청자 수가 가장 많은 구간을 구한다.
    result = dp[unit_adv_time - 1]
    for i in range(0, unit_play_time + 1 - unit_adv_time):
        if result < dp[i + unit_adv_time] - dp[i]:
            result = dp[i + unit_adv_time] - dp[i]
            answer = i + 1

    return int_to_str(answer)