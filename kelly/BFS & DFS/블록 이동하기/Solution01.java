import java.util.*;

class Solution {

    int N;
    int[][] newBoard;
    Set<Node> visited;

    public int solution(int[][] board) {
        int[][] newBoard = new int[board.length + 2][board[0].length + 2];
        for (int row = 0; row < newBoard.length; row++) {
            for (int col = 0; col < newBoard[row].length; col++) {
                if (row == 0 || row == newBoard.length - 1 || col == 0 || col == newBoard[row].length - 1) {
                    newBoard[row][col] = 1;
                } else {
                    newBoard[row][col] = board[row - 1][col - 1];
                }
            }
        }

        this.N = board.length;
        this.newBoard = newBoard;
        this.visited = new HashSet<>();

        // 메인 로직
        Deque<Node> dq = new ArrayDeque<>();
        Node start = new Node(1, 1, 1, 2, 0);
        dq.add(start);
        visited.add(start);

        while (!dq.isEmpty()) {
            Node cur = dq.poll();

            if (cur.isArrive()) {
                return cur.step;
            }

            for (Node next: getNext(cur)) {
                dq.offer(next);
                visited.add(next);
            }
        }

        return -4444;
    }

    private List<Node> getNext(Node cur) {
        List<Node> result = new ArrayList<>();

        // 상 & 하 & 좌 & 우 이동
        for (Node moved: cur.move()) {
            result.add(moved);
        }

        // 회전
        for (Node rotated: cur.rotate()) {
            result.add(rotated);
        }

        return result;
    }

    class Node {
        int r1, c1, r2, c2, step;

        public Node(int r1, int c1, int r2, int c2, int step) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.step = step;
        }

        public boolean isArrive() {
            return (r1 == N && c1 == N) || (r2 == N && c2 == N);
        }

        public List<Node> move() {
            List<Node> result = new ArrayList<>();
            int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

            for (int[] d: dir) {
                Node next = new Node(r1 + d[0], c1 + d[1], r2 + d[0], c2 + d[1], step + 1);
                if (next.isSafe()) {
                    result.add(next);
                }
            }

            return result;
        }

        public List<Node> rotate() {
            List<Node> rotted = new ArrayList<>();

            // 1. 현재 가로 모드면 세로 모드로 회전
            if (r1 == r2) {
                if (newBoard[r1 + 1][c1] == 0 && newBoard[r2 + 1][c2] == 0) {
                    rotted.add(new Node(r1, c1, r1 + 1, c1, step + 1));
                    rotted.add(new Node(r2 + 1, c2, r2, c2, step + 1));
                }

                if (newBoard[r1 - 1][c1] == 0 && newBoard[r2 - 1][c2] == 0) {
                    rotted.add(new Node(r1, c1, r1 - 1, c1, step + 1));
                    rotted.add(new Node(r2 - 1, c2, r2, c2, step + 1));
                }
            } else {  // 2. 현재 세로 모드면 가로 모드로 회전
                if (newBoard[r1][c1 + 1] == 0 && newBoard[r2][c2 + 1] == 0) {
                    rotted.add(new Node(r1, c1, r1, c1 + 1, step + 1));
                    rotted.add(new Node(r2, c2 + 1, r2, c2, step + 1));
                }

                if (newBoard[r1][c1 - 1] == 0 && newBoard[r2][c2 - 1] == 0) {
                    rotted.add(new Node(r1, c1, r1, c1 - 1, step + 1));
                    rotted.add(new Node(r2, c2 - 1, r2, c2, step + 1));
                }
            }

            List<Node> result = new ArrayList<>();
            for (Node next : rotted) {
                if (next.isSafe()) {
                    result.add(next);
                }
            }

            return result;
        }

        public boolean isSafe() {
            return newBoard[r1][c1] == 0 && newBoard[r2][c2] == 0 && !visited.contains(this);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Node)) return false;
            Node n = (Node)o;

            boolean isSame1 = (n.r1 == this.r1) && (n.c1 == this.c1) && (n.r2 == this.r2) && (n.c2 == this.c2);
            boolean isSame2 = (n.r1 == this.r2) && (n.c1 == this.c2) && (n.r2 == this.r1) && (n.c2 == this.c1);

            return isSame1 || isSame2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r1, c1) + Objects.hash(r2, c2);
        }
    }
}