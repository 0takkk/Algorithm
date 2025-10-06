import java.util.*;
import java.util.stream.Collectors;

class Solution {
    
    public static char[][] operations = {
            {'+', '-', '*'},
            {'+', '*', '-'},
            {'-', '+', '*'},
            {'-', '*', '+'},
            {'*', '+', '-'},
            {'*', '-', '+'}
    };

    public static long solution(String expression) {
        long answer = 0L;

        List<Long> numbers = Arrays.stream(expression.split("[+\\-*]"))
                .mapToLong(Long::parseLong)
                .boxed()
                .collect(Collectors.toList());

        String operatorString = expression.replaceAll("[0-9]", "");
        ArrayList<Character> operators = operatorString.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toCollection(ArrayList::new));
        
        for (char[] operation : operations) {
            List<Long> tempNumbers = new ArrayList<>(numbers);
            List<Character> tempOperators = new ArrayList<>(operators);

            for (char oper : operation) {
                for(int i = 0; i < tempOperators.size(); i++) {
                    if(oper == tempOperators.get(i)) {
                        Long a = tempNumbers.get(i);
                        Long b = tempNumbers.get(i+1);

                        tempNumbers.remove(i);
                        tempNumbers.remove(i);
                        tempNumbers.add(i, cal(a, b, tempOperators.get(i)));
                        tempOperators.remove(i);
                        i--;
                    }
                }
            }

            answer = Math.max(answer, Math.abs(tempNumbers.get(0)));
        }

        return answer;
    }

    public static long cal(long a, long b, char oper) {
        switch (oper) {
            case '+':
                return a+b;
            case '-':
                return a-b;
            default:
                return a*b;
        }
    }
}