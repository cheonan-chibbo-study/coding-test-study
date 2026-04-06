def solution(brown, yellow):
    area = brown + yellow
    for h in range(3, int(area ** 0.5) + 1):
        if area % h == 0:
            w = area // h
            if (w - 2) * (h - 2) == yellow:
                return [w, h]