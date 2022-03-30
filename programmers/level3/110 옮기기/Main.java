import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] tmp = solution(new String[]{"1110", "100111100", "0111111010"});
        for (String s : tmp) {
            System.out.println(s);
        }
    }

    public static String[] solution(String[] s) {
        StringBuilder sb;

        for(int i = 0; i < s.length; i++){
            String str = s[i];
            Stack<Character> stack = new Stack<>();
            int cnt = 0;

            for(int j = 0; j < str.length(); j++){
                char z = str.charAt(j);

                if(z == '0') {
                    if (stack.size() >= 2) {
                        char y = stack.pop();
                        char x = stack.pop();

                        if (x == '1' && y == '1') cnt++;
                        else{
                            stack.push(x);
                            stack.push(y);
                            stack.push(z);
                        }
                    } else stack.push(z);
                } else stack.push(z);
            }

            int idx = stack.size();
            boolean flag = false;
            sb = new StringBuilder();

            while(!stack.isEmpty()){
                if(!flag){
                    if(stack.peek() == '1') idx--;
                    else flag = true;
                }
                sb.insert(0, stack.pop());
            }

            while(cnt-->0){
                sb.insert(idx, "110");
                idx += 3;
            }

            s[i] = sb.toString();
        }

        return s;
    }

}
