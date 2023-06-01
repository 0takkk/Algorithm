import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> front = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> back = new PriorityQueue<>();

        front.offer(Integer.parseInt(br.readLine()));
        sb.append(front.peek()).append("\n");
        n--;

        while(n-->0){
            int num = Integer.parseInt(br.readLine());

            if(front.peek() > num) front.offer(num);
            else if(!back.isEmpty() && back.peek() >= num) front.offer(num);
            else back.offer(num);

            if(front.size() == back.size()+2){
                back.offer(front.poll());
            }
            else if(front.size() < back.size()){
                front.offer(back.poll());
            }

            sb.append(front.peek()).append("\n");
        }

        System.out.println(sb.toString());
    }
}
