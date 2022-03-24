import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution("abcdcba"));
        System.out.println(solution("abacde"));
    }

    public static int solution(String s) {

        for(int len = s.length(); len > 0; len--){
            for(int start = 0; start + len <= s.length(); start++){
                boolean flag = true;

                for(int i = 0; i < len / 2; i++){
                    if(s.charAt(i+start) != s.charAt(start + len - i - 1)){
                        flag = false;
                        break;
                    }
                }

                if(flag) return len;
            }
        }

        return -1;
    }

}

