package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
	    System.out.println(solution("[](){}"));
        System.out.println(solution("}]()[{"));
        System.out.println(solution("[)(]"));
        System.out.println(solution("}}}"));

    }

    public static int solution(String s) {
        int answer = 0;
        char[] str = s.toCharArray();
        ArrayList<Character> cArray = new ArrayList<>();
        for(int i = 0; i < str.length; i++)
            cArray.add(str[i]);

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < cArray.size(); i++) {
            boolean flag = true;

            for (char c : cArray) {
                if (c == '[' || c == '{' || c == '(') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) {
                        flag = false;
                        break;
                    }
                    if (c == ']') {
                        if (stack.peek() == '[') {
                            stack.pop();
                        } else {
                            flag = false;
                            break;
                        }
                    } else if (c == '}') {
                        if (stack.peek() == '{')
                            stack.pop();
                        else {
                            flag = false;
                            break;
                        }
                    } else if (c == ')') {
                        if (stack.peek() == '(')
                            stack.pop();
                        else {
                            flag = false;
                            break;
                        }
                    }
                }
            }
            if(!stack.isEmpty()) flag = false;
            if (flag) answer++;
            stack.clear();
            cArray.add(cArray.get(0));
            cArray.remove(0);
        }
        return answer;
    }
}
