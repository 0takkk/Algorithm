import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println(solution(437674, 3));
        System.out.println(solution(110011, 10));
        System.out.println(solution(131, 10));
    }

    public static int solution(int n, int k) {
        int answer = 0;

        String convert_num = convert(n,k);

        System.out.println(convert_num);

        int idx = 0;
        for(int i = 1; i < convert_num.length(); i++){
            char c = convert_num.charAt(i);
            if(c == '0'){
                if(check2(Long.parseLong(convert_num.substring(idx, i)))) answer++;
                idx = i;
            }
        }

        if(convert_num.charAt(convert_num.length()-1) != '0')
            if(check2(Long.parseLong(convert_num.substring(idx)))) answer++;

//        for(String num : nums){
//            if(!num.equals("")) {
//                int e = Integer.parseInt(num);
//                if (check(e)) answer++;
//            }
//        }

        return answer;
    }

//    public static boolean check(int num){
//        if(num == 1) return false;
//
//        boolean[] arr = new boolean[num+1];
//        for(int i = 2; i <= num; i++)
//            arr[i] = true;
//
//        for(int i = 2; i*i <= num; i++){
//            for(int j = i*i; j <= num; j+=i){
//                arr[j] = false;
//            }
//        }
//
//        if(arr[num] == true) return true;
//        else return false;
//    }

    public static boolean check2(Long num){
        if(num == 0) return false;
        if(num == 1) return false;

        for(int i = 2; i <= (int)Math.sqrt(num); i++){
            if(num % i == 0)
                return false;
        }
        return true;
    }

    public static String convert(int n, int k){
        StringBuilder sb = new StringBuilder();

        while(n > 0){
            sb.append(n % k);

            n /= k;
        }
        return sb.reverse().toString();
    }

}
