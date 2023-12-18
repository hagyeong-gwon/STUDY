import java.util.*;

class Solution {
  public static void main(String[] args) {

    String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
    System.out.print(solution(tickets).toString());
  }

  public static String[] solution(String[][] tickets) {
    String[] answer = new String[tickets.length + 1];
    answer[0] = "ICN";
    HashMap<String, LinkedList<String>> map = new HashMap<>();

    for (int i = 0; i < tickets.length; i++) {
      String key = tickets[i][0];
      String value = tickets[i][1];

      LinkedList<String> words = map.get(key);
      if (words == null) {
        words = new LinkedList<String>();
        words.add(value);
        map.put(key, words);
      } else {
        words.add(value);
        words.sort((a, b) -> a.compareTo(b));
        map.put(key, words);
      }
    }


    for (int i = 1; i < answer.length; i++) {
      String now = answer[i - 1];
      LinkedList<String> nextPlaces = map.get(now);
      System.out.println(now);
      System.out.println(nextPlaces.toString());
      if (nextPlaces.size() > 0) {
        String next = nextPlaces.poll();

        answer[i] = next;
        map.put(now, nextPlaces);
      }
    }
    return answer;
  }
}