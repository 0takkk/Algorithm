import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(8, 2, new String[] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"}));
    }

    public static String solution(int n, int k, String[] cmd) {
        String answer = "";

        Stack<Integer> removed = new Stack<>();
        int size = n;

        for (String s : cmd) {
            char c = s.charAt(0);

            if(c == 'U') k -= Integer.parseInt(s.substring(2));
            else if(c == 'D') k += Integer.parseInt(s.substring(2));
            else if(c == 'C'){
                removed.add(k);
                size--;
                if(k == size) k--;
            }
            else if(c == 'Z'){
                if(removed.pop() <= k) k++;
                size++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++)
            sb.append("O");

        while(!removed.isEmpty()){
            sb.insert(removed.pop().intValue(), "X");
        }

        answer = sb.toString();
        return answer;
    }

}
