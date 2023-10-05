import java.util.*;

class Solution {
  public static void main(String[] args) {
    int[][] rectangle = new int[][]{{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};

    System.out.print(solution(rectangle, 1, 3, 7, 8));
  }

  public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
    int answer = 0;
    int result = 0;

    LinkedList<int[]> queue = new LinkedList<>();
    HashMap<String, Boolean> visited = new HashMap<>();
    int[] first = new int[]{characterX, characterY};
    queue.add(first);
    visited.put(first[0] + "|" + first[1], true);


    int[][] calc = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    while (!queue.isEmpty()) {
      int queueSize = queue.size();
      for (int r = 0; r < queueSize; r++) {
        int[] position = queue.poll();
        System.out.println(Arrays.toString(position) + "|" + result);

        int x = position[0];
        int y = position[1];

        if (x == itemX && y == itemY) {
          return result;
        }
        for (int i = 0; i < calc.length; i++) {
          int[] calcXY = new int[]{x + calc[i][0], y + calc[i][1]};

          boolean isRight = false;
          boolean isInclude = false;

          for (int j = 0; j < rectangle.length; j++) {
            if ((rectangle[j][0] == calcXY[0] || rectangle[j][2] == calcXY[0]) && rectangle[j][1] <= calcXY[1] && rectangle[j][3] >= calcXY[1]) {
              isRight = !isInclude && true;
              continue;
            } else if ((rectangle[j][1] == calcXY[1] || rectangle[j][3] == calcXY[1]) && rectangle[j][0] <= calcXY[0] && rectangle[j][2] >= calcXY[0]) {
              isRight = !isInclude && true;
              continue;
            }
            isInclude = (rectangle[j][1] < calcXY[1] && rectangle[j][3] > calcXY[1] && rectangle[j][0] < calcXY[0] && rectangle[j][2] > calcXY[0]);
            if ((rectangle[j][1] < calcXY[1] && rectangle[j][3] > calcXY[1] && rectangle[j][0] < calcXY[0] && rectangle[j][2] > calcXY[0])) {
              isInclude = true;
              if (isRight) isRight = false;
            }
          }

          if (isRight && !visited.containsKey(calcXY[0] + "|" + calcXY[1])) {
            queue.add(calcXY);
            visited.put(calcXY[0] + "|" + calcXY[1], true);
          }
        }
      }
      result++;
    }

    return result;
  }
}