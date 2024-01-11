package DFS;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CoinGet {
  public static void main(String[] args) throws IOException {
    int[][] rectangle = new int[][]{{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};
    int characterX = 1;
    int characterY = 3;
    int itemX = 7;
    int itemY = 8;

    int[][] fullMap = new int[50][50];

    for (int i = 0; i < fullMap.length; i++) {
      Arrays.fill(fullMap[i], 0);
    }

    for (int i = 0; i < rectangle.length; i++) {

      int maxX = rectangle[i][2] * 2;
      int maxY = rectangle[i][3] * 2;
      int[] position = {rectangle[i][0] * 2, rectangle[i][1] * 2, maxX, maxY};
      System.out.println(Arrays.toString(position));
      Arrays.fill(fullMap[rectangle[i][1] * 2], rectangle[i][0] * 2, maxX, 1);
      Arrays.fill(fullMap[maxY], rectangle[i][0] * 2, maxX, 1);

      for (int x = rectangle[i][0] * 2; x < maxX + 1; x++) {
        int y = rectangle[i][1] * 2;
        fullMap[y][x] = 1;
        fullMap[maxY][x] = 1;

        for (int j = 0; j < rectangle.length; j++) {
          if (j == i) continue;

          if (rectangle[j][0]  * 2 < x && rectangle[j][2]  * 2 > x) {
            if (rectangle[j][1]  * 2< y && rectangle[j][3]  * 2 > y) {
              fullMap[y][x] = 0;
            }
            if (rectangle[j][1] * 2 < maxY && rectangle[j][3] * 2 > maxY) {
              fullMap[maxY][x] = 0;
            }
          }
        }
      }

      for (int y = rectangle[i][1] * 2; y < maxY + 1; y++) {
        int x = rectangle[i][0] * 2;

        fullMap[y][x] = 1;
        fullMap[y][maxX] = 1;



        for (int j = 0; j < rectangle.length; j++) {
          if (j == i) continue;
          if (rectangle[j][1]  * 2 < y && rectangle[j][3]  * 2 > y) {
            if (rectangle[j][0]  * 2 < x && rectangle[j][2]  * 2 > x) {
              fullMap[y][x] = 0;
            }
            if (rectangle[j][0] * 2 < maxX && rectangle[j][2] * 2 > maxX) {
              fullMap[y][maxX] = 0;
            }
          }
        }
      }
    }

//    String path = System.getProperty("user.dir") + "\\sampleSquare.txt";
    File file = new File("./sampleSquare.txt");
    if (!file.exists()) {
      file.createNewFile();
    }
    FileWriter fw = new FileWriter(file);
    BufferedWriter writer = new BufferedWriter(fw);

    for (int i = 0; i < fullMap.length; i++) {
      int [] a = fullMap[i];
      System.out.println(Arrays.toString(a).replace("0", "■").replace("1", "□").replaceAll("\\[|\\]",""));

      writer.write(Arrays.toString(a).replace("0", "■").replace("1", "□").replaceAll("\\[|\\]","") + '\n');

    }
    System.out.println(solution(fullMap, characterX * 2, characterY * 2, itemX * 2, itemY * 2) / 2);

  }
  public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
    System.out.println(characterX+ "|" +characterY+","+itemX+"|"+itemY);
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

        int x = position[0];
        int y = position[1];

        if (x == itemX && y == itemY) {
          return result;
        }

        for (int i = 0; i < calc.length; i++) {
          int[] calcXY = new int[]{x + calc[i][0], y + calc[i][1]};

          if (rectangle[calcXY[1]][calcXY[0]] == 1 && !visited.containsKey(calcXY[0] + "|" + calcXY[1]) ){
            visited.put(x + "|" + y, true);

            queue.add(calcXY);
            System.out.println(Arrays.toString(calcXY) + "|" + result);
            System.out.println(rectangle[calcXY[1]][calcXY[0]]);

          }
        }
      }
      result++;
    }

    return result;
  }
}
