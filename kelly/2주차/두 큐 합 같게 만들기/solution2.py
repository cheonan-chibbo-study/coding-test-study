def solution(queue1, queue2):
    queue_size = len(queue1)
    total_queue = queue1 + queue2
    total_sum = sum(total_queue)
    queue1_sum = sum(queue1)
    queue2_sum = sum(queue2)
    left = 0
    right = len(queue1)

    cnt = 0
    while cnt < queue_size * 4:
        if (queue1_sum == queue2_sum):
            return cnt
        elif queue1_sum > queue2_sum:
            queue1_sum -= total_queue[left]
            queue2_sum = total_sum - queue1_sum
            left = (left + 1) % len(total_queue)
        else:
            queue1_sum += total_queue[right]
            queue2_sum = total_sum - queue1_sum
            right = (right + 1) % len(total_queue)

        cnt += 1

    return -1