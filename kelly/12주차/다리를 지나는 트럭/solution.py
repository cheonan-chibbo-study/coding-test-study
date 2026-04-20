from collections import deque

def solution(bridge_length, weight, truck_weights):
    bridge = deque([0] * bridge_length)
    wait = deque(truck_weights)
    cur_weight = 0
    time = 0

    while wait:
        time += 1
        cur_weight -= bridge.popleft()

        if not wait:
            continue

        if cur_weight + wait[0] <= weight:
            cur_weight += wait[0]
            bridge.append(wait.popleft())
        else:
            bridge.append(0)

    time += bridge_length

    return time