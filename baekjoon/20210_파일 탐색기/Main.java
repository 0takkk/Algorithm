import java.io.*;
import java.util.*;


public class Main {

    public static class MyString implements Comparable<MyString>{
        String str;

        public MyString(String str) {
            this.str = str;
        }

        @Override
        public int compareTo(MyString o) {
            int len1 = this.str.length();
            int len2 = o.str.length();

            int i = 0, j = 0;

            for(; i < len1 && j < len2; i++, j++){
                char c1 = this.str.charAt(i);
                char c2 = o.str.charAt(j);

                boolean isNum1 = isNumber(c1);
                boolean isNum2 = isNumber(c2);

                // 둘 다 숫자인 경우
                if(isNum1 && isNum2){
                    // 숫자 앞 0 개수 세기
                    int zero1 = 0; int zero2 = 0;
                    while(i < this.str.length() && this.str.charAt(i) == '0'){
                        zero1++;
                        i++;
                    }
                    while(j < o.str.length() && o.str.charAt(j) == '0'){
                        zero2++;
                        j++;
                    }

                    i = Math.min(i, len1);
                    j = Math.min(j, len2);

                    // 0을 제외한 숫자
                    StringBuilder sb1 = new StringBuilder();
                    StringBuilder sb2 = new StringBuilder();

                    while(i < len1 && isNumber(this.str.charAt(i))){
                        sb1.append(this.str.charAt(i++));
                    }
                    while(j < len2 && isNumber(o.str.charAt(j))){
                        sb2.append(o.str.charAt(j++));
                    }

                    i--;
                    j--;

                    // 0을 제거 했으므로 길이가 긴 것이 더 큰 숫자
                    if(sb1.length() > sb2.length()) return 1;
                    else if(sb1.length() < sb2.length()) return -1;


                    // 길이가 같은 경우 한자리씩 비교하며 두 수 비교
                    String num1 = sb1.toString();
                    String num2 = sb2.toString();
                    for(int a = 0, b = 0; a < num1.length() && b < num2.length(); a++, b++){
                        if(num1.charAt(a) > num2.charAt(b)) return 1;
                        else if(num1.charAt(a) < num2.charAt(b)) return -1;
                    }


                    // 숫자까지 같다면 0의 개수가 작은 순
                    if(zero1 != zero2) return zero1 - zero2;
                }

                // 둘 다 문자인 경우
                if(!isNum1 && !isNum2){
                    c1 = this.str.charAt(i);
                    c2 = o.str.charAt(j);

                    if(c1 == c2) continue;

                    boolean isUpper1 = (c1 - 'a') < 0 ;
                    boolean isUpper2 = (c2 - 'a') < 0;

                    int n1 = c1 - 'a' >= 0 ? c1 - 'a' : c1 - 'A';
                    int n2 = c2 - 'a' >= 0 ? c2 - 'a' : c2 - 'A';

                    // 둘다 대문자이거나 둘다 소문자
                    if((isUpper1 && isUpper2) || (!isUpper1 && !isUpper2)){
                        return n1 - n2;
                    }

                    // c1 : 소문자, c2 : 대문자
                    if(!isUpper1 && isUpper2){
                        // c1, c2가 같은 무자일 경우
                        if(n1 == n2) return 1;
                        return n1 - n2;
                    }

                    // c1 : 대문자, c2 : 소문자
                    if(isUpper1 && !isUpper2){
                        if(n1 == n2) return -1;
                        return n1 - n2;
                    }
                }

                // this.str : 문자, o.str : 숫자
                if(!isNum1 && isNum2){
                    return 1;
                }

                // this.str : 숫자, o.str : 문자
                if(isNum1 && !isNum2){
                    return -1;
                }
            }

            // 같은 문자인데 뒤에 다른 문자열이 붙는 경우 더 긴 문자열이 뒤로
            if(len1 != i) return 1;
            if(len2 != j) return -1;

            return 0;
        }

        public boolean isNumber(char c){
            return '0' <= c && c <= '9';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        ArrayList<MyString> list = new ArrayList<>();

        for(int i = 0; i < n; i++){
            list.add(new MyString(br.readLine()));
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (MyString myString : list) {
            sb.append(myString.str).append("\n");
        }

        System.out.println(sb.toString());
    }

}
