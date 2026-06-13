package study2.week4.가로등설치하기;

import java.io.*;
import java.util.*;

public class Solution {

    static class Gap {
        int left;
        int right;
        int dist;

        Gap(int left, int right, int dist) {
            this.left = left;
            this.right = right;
            this.dist = dist;
        }
    }

    static int N;
    static int[] pos;
    static int[] prev;
    static int[] next;
    static boolean[] alive;

    static int first;
    static int last;
    static int nextId;

    static PriorityQueue<Gap> pq;

    static void cleanPQ() {
        while (!pq.isEmpty()) {
            Gap g = pq.peek();

            if (!alive[g.left]
                    || !alive[g.right]
                    || next[g.left] != g.right) {
                pq.poll();
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int Q = Integer.parseInt(br.readLine());

        StringTokenizer st =
                new StringTokenizer(br.readLine());

        st.nextToken(); // 100

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int maxSize = Q + M + 5;

        pos = new int[maxSize];
        prev = new int[maxSize];
        next = new int[maxSize];
        alive = new boolean[maxSize];

        pq = new PriorityQueue<>((a, b) -> {

            if (a.dist != b.dist) {
                return b.dist - a.dist;
            }

            return pos[a.left] - pos[b.left];
        });

        int[] init = new int[M + 1];

        for (int i = 1; i <= M; i++) {
            init[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= M; i++) {

            pos[i] = init[i];
            alive[i] = true;

            if (i > 1) {
                prev[i] = i - 1;
            }

            if (i < M) {
                next[i] = i + 1;
            }
        }

        first = 1;
        last = M;
        nextId = M + 1;

        for (int i = 1; i < M; i++) {
            pq.add(new Gap(
                    i,
                    i + 1,
                    pos[i + 1] - pos[i]
            ));
        }

        StringBuilder sb = new StringBuilder();

        for (int q = 2; q <= Q; q++) {

            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());

            // 가로등 추가
            if (cmd == 200) {

                cleanPQ();

                Gap g = pq.poll();

                int leftId = g.left;
                int rightId = g.right;

                int newPos =
                        (pos[leftId] + pos[rightId] + 1) / 2;

                int id = nextId++;

                pos[id] = newPos;
                alive[id] = true;
                // 10----50-----100
                //left Id = 10
                //next[10] = 50 10 의 다음은 50
                //50 의 전은 10
                next[leftId] = id;
                prev[id] = leftId;
                //50 의 다음은 100
                // 100의 전은 50
                next[id] = rightId;
                prev[rightId] = id;

                pq.add(new Gap(
                        leftId,
                        id,
                        pos[id] - pos[leftId]
                ));

                pq.add(new Gap(
                        id,
                        rightId,
                        pos[rightId] - pos[id]
                ));
            }

            // 가로등 제거
            else if (cmd == 300) {

                int d = Integer.parseInt(st.nextToken());

                int L = prev[d];
                int R = next[d];

                alive[d] = false;

                if (d == first) {
                    first = R;
                }

                if (d == last) {
                    last = L;
                }

                if (L != 0) {
                    next[L] = R;
                }

                if (R != 0) {
                    prev[R] = L;
                }

                if (L != 0 && R != 0) {
                    pq.add(new Gap(
                            L,
                            R,
                            pos[R] - pos[L]
                    ));
                }
            }

            // 최소 전력 계산
            else if (cmd == 400) {

                cleanPQ();

                int maxGap = pq.isEmpty() ? 0 : pq.peek().dist;

                int leftNeed =
                        2 * (pos[first] - 1);

                int rightNeed =
                        2 * (N - pos[last]);

                int answer =
                        Math.max(maxGap,
                                Math.max(leftNeed, rightNeed));

                sb.append(answer).append('\n');
            }
        }

        System.out.print(sb);
    }
}