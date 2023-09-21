import java.util.Stack;

class Solution {
    public static void main(String[] args) {

        int[][] maps = new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
        System.out.print(solution(maps));
    }

    public static int solution(int[][] maps) {
        int answer = -1;
        int[] move = new int[]{1, -1, 0, 0};

        boolean[][] visited = new boolean[maps.length][maps[0].length];
        Stack<int[]> stack = new Stack<>();
        int[] startIndex = {0, 0, 1};
        stack.push(startIndex);

        while(!stack.isEmpty()) {
            int[] index = stack.pop();
            int x = index[0];
            int y = index[1];
            int root = index[2];
            visited[x][y] = true;

            for (int i = 0; i < move.length; i++) {
                int mx = x + move[i];
                int my = y + move[move.length - (i + 1)];

                int[] nextIndex = {mx, my, root + 1};
                if (mx < 0 || my < 0 || mx >= maps.length || my >= maps[0].length) {
                    continue;
                }

                if (mx == maps.length - 1 && my == maps[0].length - 1) {
                    answer = answer == -1 ? root + 1 : (answer > root + 1 ? root + 1 : answer);
                    continue;
                }

                if (maps[mx][my] == 1 && !visited[mx][my]) {
                    stack.push(nextIndex);
                }
            }
        }
        return answer;
    }
}