import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(99, new int[]{2, 15, 22, 55, 55}));
    }

    public static long solution(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works) {
            pq.offer(work);
        }

        while(n > 0){
            int work = pq.poll();
            if(work == 0) break;
            pq.offer(work-1);
            n--;
        }

        while(!pq.isEmpty()){
            int work = pq.poll();
            answer += Math.pow(work, 2);
        }

        return answer;
    }

}
