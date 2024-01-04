package DFS;

import java.util.*;

public class Trip {
  public static LinkedList<String> find(HashMap<String, LinkedList<String>> map, String now, Integer cnt, LinkedList<String> answer){
    answer.addLast(now);

    LinkedList<String> nextTickets = map.get(now);
    if (nextTickets == null || nextTickets.isEmpty()) {
      return answer;
    }
    nextTickets.sort((a, b) -> a.compareTo(b));

    for (Integer i = 0; i< nextTickets.size(); i++) {
      String next = nextTickets.poll();
      map.put(now, nextTickets);
//      answer.push(now);
      find(map, next, cnt, answer);
      if (answer.size() == cnt) return answer;

      answer.pollLast();

      nextTickets.addLast(next);
      map.put(now, nextTickets);
    }

    return answer;
  }
  public static void main(String[] args) {
    //    String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
    String[][] tickets = {{"ICN", "BOO"}, {"ICN", "COO"}, {"COO", "DOO"}, {"DOO", "COO"}, {"BOO", "DOO"},{"DOO", "BOO"}, {"BOO", "ICN"}, {"COO", "BOO"} };
    System.out.print(solution(tickets).toString());
  }

  public static LinkedList<String> solution(String[][] tickets) {
    LinkedList<String> answer = new LinkedList<>();
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
        map.put(key, words);
      }
    }

    System.out.println(map.toString());
//    map = {'ICN':['a', 'v']}

    return find(map, "ICN", tickets.length + 1, answer);
  }
}
