import java.util.*;

class Solution {
    
    public class Expression {
        String a, oper, b, result;
        boolean isAdd;
        
        public Expression(String a, String oper, String b, String result) {
            this.a = a;
            this.oper = oper;
            this.b = b;
            this.result = result;
            this.isAdd = oper.equals("+");
        }
        
        public boolean isUnknownResult() {
            return result.equals("X");
        }
        
        public int getMinBase() {
            int max = 0;
            
            for(char c : a.toCharArray()) {
                max = Math.max(max, c - '0');
            }
            
            for(char c : b.toCharArray()) {
                max = Math.max(max, c - '0');
            }
            
            if(!this.isUnknownResult()) {
                 for(char c : result.toCharArray()) {
                    max = Math.max(max, c - '0');
                }
            }
            
            return max+1;
        }
        
        public boolean isValid(int base) {
            try {
                int aVal = toDecimal(a, base);
                int bVal = toDecimal(b, base);
                int resultVal = toDecimal(result, base);
                
                int calculated = isAdd ? aVal + bVal : aVal - bVal;
                
                return calculated == resultVal;
            } catch (Exception ex) {
                return false;
            }
        }
    }
    
    public String[] solution(String[] expressions) {
        List<String> result = new ArrayList<>();
        List<Expression> knownExpressions = new ArrayList<>();
        List<Expression> unknownExpressions = new ArrayList<>();
        
        for(String expression : expressions) {
            String[] arr = expression.split(" ");
            Expression e = new Expression(arr[0], arr[1], arr[2], arr[4]);
            
            if(e.isUnknownResult()) {
                unknownExpressions.add(e);
            } else {
                knownExpressions.add(e);
            }
        }
        
        int minBase = 0;
        for(Expression e : knownExpressions) {
            minBase = Math.max(minBase, e.getMinBase());
        }
        
        for(Expression e : unknownExpressions) {
            minBase = Math.max(minBase, e.getMinBase());
        }
        
        Set<Integer> possibleBases = new HashSet<>();
        for(int i = minBase; i <= 9; i++) {
            possibleBases.add(i);
        }
        
        for(Expression e : knownExpressions) {
            possibleBases.removeIf(base -> !e.isValid(base));
        }
        
        for(Expression e : unknownExpressions) {
            Set<String> possibleResults = new HashSet<>();
            
            for(int base : possibleBases) {
                int aVal = toDecimal(e.a, base);
                int bVal = toDecimal(e.b, base);
                int resultVal = e.isAdd ? aVal + bVal : aVal - bVal;
                
                String resultString = toBase(resultVal, base);
                
                possibleResults.add(resultString);
            }
            
            String answer;
            if (possibleResults.size() == 1) {
                answer = possibleResults.iterator().next();
            } else {
                answer = "?";
            }
            
            result.add(e.a + " " + e.oper + " " + e.b + " = " + answer);
        }
        
        return result.toArray(new String[0]);
    }
    
    public int toDecimal(String num, int base) {
        return Integer.parseInt(num, base);
    }
    
    public String toBase(int num, int base) {
        return Integer.toString(num, base);
    }
}