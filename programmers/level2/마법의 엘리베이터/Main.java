public class Main {
    
    public static void main(String[] args) {
        System.out.println(solution(535));
    }

    public static int solution(int storey) {
        int answer = 0;

        while(storey > 0){
            int r = storey % 10;

            if(r > 5 || (r == 5 && storey / 10 % 10 >= 5)){
                answer += (10 - r);
                storey += (10 - r);
            } else {
                answer += r;
                storey -= r;
            }

            storey /= 10;
        }

        return answer + storey;
    }

}
