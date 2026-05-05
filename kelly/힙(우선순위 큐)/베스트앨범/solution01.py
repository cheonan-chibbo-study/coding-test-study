from heapq import heappush, heappop

def solution(genres, plays):
    category_map = {}
    for i in range(len(plays)):
        category_map[genres[i]] = category_map.get(genres[i], 0) + plays[i]

    category_pq = []
    for k, v in category_map.items():
        heappush(category_pq, (-v, k))

    answer = []
    while category_pq:
        cur_category = heappop(category_pq)[1]
        music_pq = []
        for idx in range(len(plays)):
            if genres[idx] == cur_category:
                heappush(music_pq, (-plays[idx], idx))

        for i in range(2):
            if not music_pq:
                break

            answer.append(heappop(music_pq)[1])

    return answer