import  java.util.*;

class Solution {
    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words = {"hot","dot","dog","lot","log","cog"};
        System.out.print(solution(begin, target, words));
    }
    public static int solution(String begin, String target, String[] words) {
        int answer = 0;
        int result = 0;

        LinkedList<String> list = new LinkedList<>();
//        boolean[] visited = new boolean[words.length];
        HashMap<String, Boolean> visited = new HashMap<>();

        for (String word : words) {
            visited.put(word, false);
        }
        list.offer(begin);
        visited.put(begin, true);
        while(!list.isEmpty()) {
            for (int y = 0; y < list.size(); y++) {
                String word = list.poll();

                if (word.equals(target)) {
                    return result;
                }

                for (String searchWord : visited.keySet()) {

                    if (!visited.get(searchWord)) {
                        int wrongCnt = 0;
                        for (int i = 0; i < searchWord.length(); i++) {
                            if (searchWord.charAt(i) != (word.charAt(i))) {
                                wrongCnt++;
                                if (wrongCnt > 1) break;
                            }
                        }

                        if(wrongCnt == 1) {
                            list.offer(searchWord);
                            visited.put(searchWord, true);
                        }
                    }
                }
            }
            result++;
        }

        return answer;
    }
}