import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true){
            String input = br.readLine();

            if(input.equals(".")) break;

            Stack<Character> stack = new Stack<>();
            boolean flag = true;
            for(int i = 0; i < input.length(); i++){
                char c = input.charAt(i);

                if(c == '(' || c == '['){
                    stack.push(c);
                }
                else if(c == ')' || c == ']'){
                    if(stack.isEmpty()) {
                        flag = false;
                        break;
                    }
                    else if(c == ')'){
                        if(stack.peek() == '('){
                            stack.pop();
                        } else{
                            flag = false;
                            break;
                        }
                    }
                    else if(c == ']'){
                        if(stack.peek() == '['){
                            stack.pop();
                        } else {
                            flag = false;
                            break;
                        }
                    }
                }
            }

            if(flag && stack.isEmpty()) sb.append("yes\n");
            else sb.append("no\n");
        }

        System.out.println(sb.toString());
    }

}
