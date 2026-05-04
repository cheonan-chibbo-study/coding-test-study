from collections import deque

def solution(bridge_length, weight, truck_weights):
    ready = deque(truck_weights)
    bridge = deque([0] * bridge_length)

    total_w = 0
    time = 0

    while ready:
        time += 1
        total_w -= bridge.popleft()

        if (weight - total_w) >= ready[0]:
            cur = ready.popleft()
            total_w += cur
            bridge.append(cur)
        else:
            bridge.append(0)

    return time + bridge_length