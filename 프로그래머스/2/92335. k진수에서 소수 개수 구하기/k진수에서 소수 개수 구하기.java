class Solution {
    public static int solution(int n, int k) {
        int answer = 0;

        String num = Integer.toString(n, k);
        String[] p = num.split("0");

        for (String s : p) {
            if(s.isBlank()) continue;
            if(isPrime(Long.parseLong(s))) answer++;
        }

        return answer;
    }

    public static boolean isPrime(long n) {
        if(n == 0 || n == 1) return false;

        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) return false;
        }

        return true;
    }
}