def solution(new_id):
    # step 01 (문자를 모두 소문자로 변환)
    step1 = new_id.lower()

    # step 02 (주어진 문자열에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거한다.)
    allowed = {'-', '_', '.'}
    step2 = ''.join(
        c for c in step1
        if c.islower() or c.isdigit() or c in allowed
    )

    # step 03 (주어진 문자열에서 처음과 끝에 위치한 마침표(.)를 제거한다.)
    step3 = step2
    while '..' in step3:
        step3 = step3.replace('..', '.')

    # step 04 (주어진 문자열에서 처음과 끝에 위치한 마침표(.)를 제거한다.)
    step4 = step3.strip('.')

    # step 05 (주어진 문자열이 빈 문자열이라면, "a"를 대입한다.)
    step5 = step4 if step4 else "a"

    # step 06 (주어진 문자열의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거한다.)
    step6 = step5[:15].rstrip('.')

    # step 07 (주어진 문자열의 길이가 2자 이하라면, 마지막 문자를 문자열의 길이가 3이 될 때까지 반복해서 끝에 붙인다.)
    step7 = step6
    while len(step7) <= 2:
        step7 += step7[-1]

    return step7