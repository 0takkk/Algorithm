import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        char[] ch = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : ch) {
            switch (c){
                case '(':
                    stack.push(c);
                    break;
                case ')':
                    while(stack.peek() != '('){
                        sb.append(stack.pop());
                    }
                    stack.pop();
                    break;
                case '*':
                case '+':
                case '-':
                case '/':
                    while(!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(c)){
                        sb.append(stack.pop());
                    }
                    stack.push(c);
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());
    }

    public static int getPriority(char c){
        switch (c){
            case '(':
            case ')':
                return -1;
            case '*':
            case '/':
                return 1;
            default:
                return 0;
        }
    }

}
