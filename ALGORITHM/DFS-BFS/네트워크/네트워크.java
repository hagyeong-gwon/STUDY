import java.util.Stack;

class Solution {
    public static void main(String[] args) {

        int[][] computers = new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.print(solution(3, computers));
    }

    public static int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];

        for (int startIndex = 0; startIndex < n; startIndex++) {
            Stack<Integer> stack = new Stack<>();

            if (!visited[startIndex]) {
                visited[startIndex] = true;
                stack.push(startIndex);
                answer++;
            }

            while(!stack.isEmpty()) {
                int nodeIndex = stack.pop();

                for (int j = 0; j < computers[nodeIndex].length; j++) {
                    if (computers[nodeIndex][j] == 1 && !visited[j]) {
                        stack.push(j);
                        visited[j] = true;
                    }
                }
            }
        }


        return answer;
    }
}