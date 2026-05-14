package study1.week11.노란불신호등;
class Solution {

    static class Congruence {
        long r; // x ≡ r (mod m)
        long m;

        Congruence(long r, long m) {
            this.r = r;
            this.m = m;
        }
    }

    long answer = Long.MAX_VALUE;

    public int solution(int[][] signals) {
        dfs(0, null, signals);

        if (answer == Long.MAX_VALUE) return -1;
        return (int) answer;
    }

    private void dfs(int idx, Congruence cur, int[][] signals) {
        if (idx == signals.length) {
            // x가 t-1 이므로 실제 시간 t는 x+1
            answer = Math.min(answer, cur.r + 1);
            return;
        }

        int g = signals[idx][0];
        int y = signals[idx][1];
        int r = signals[idx][2];
        int period = g + y + r;

        // 노란불 구간: x % period == g ~ g+y-1
        for (int yellowResidue = g; yellowResidue < g + y; yellowResidue++) {
            Congruence next;
            if (cur == null) {
                next = new Congruence(yellowResidue, period);
            } else {
                next = merge(cur.r, cur.m, yellowResidue, period);
            }

            if (next != null) {
                // 이미 구한 답보다 현재 x가 커지면 더 볼 필요가 없음
                if (next.r + 1 >= answer) continue;
                dfs(idx + 1, next, signals);
            }
        }
    }

    // x ≡ a (mod m), x ≡ b (mod n) 병합
    private Congruence merge(long a, long m, long b, long n) {
        long g = gcd(m, n);

        // 나머지가 gcd 기준으로 다르면 해 없음
        if ((b - a) % g != 0) return null;

        long lcm = m / g * n;

        // m/g * k ≡ (b-a)/g (mod n/g)
        long m1 = m / g;
        long n1 = n / g;
        long diff = (b - a) / g;

        long inv = modInverse(m1, n1);
        long k = mod(diff * inv, n1);

        long x = a + m * k;
        x = mod(x, lcm);

        return new Congruence(x, lcm);
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    // a와 mod는 서로소
    private long modInverse(long a, long mod) {
        long[] res = extendedGcd(a, mod);
        long x = res[0];
        return mod(x, mod);
    }

    // return [x, y, gcd] where ax + by = gcd(a,b)
    private long[] extendedGcd(long a, long b) {
        if (b == 0) return new long[]{1, 0, a};

        long[] next = extendedGcd(b, a % b);
        long x = next[1];
        long y = next[0] - (a / b) * next[1];
        return new long[]{x, y, next[2]};
    }

    private long mod(long x, long m) {
        x %= m;
        if (x < 0) x += m;
        return x;
    }
}