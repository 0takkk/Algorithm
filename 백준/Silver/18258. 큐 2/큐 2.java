import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        LinkedList<Integer> queue = new LinkedList<>();

        while(n-->0) {
            st = new StringTokenizer(br.readLine());
            String comd = st.nextToken();

            switch (comd) {
                case "push":
                    queue.add(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    if(queue.isEmpty()) {
                        sb.append("-1\n");
                    }
                    else {
                        sb.append(queue.removeFirst()).append("\n");
                    }
                    break;
                case "size":
                    sb.append(queue.size()).append("\n");
                    break;
                case "empty":
                    if(queue.isEmpty()) {
                        sb.append("1\n");
                    }
                    else {
                        sb.append("0\n");
                    }
                    break;
                case "front":
                    if(queue.isEmpty()) {
                        sb.append("-1\n");
                    }
                    else {
                        sb.append(queue.get(0)).append("\n");
                    }
                    break;
                default:
                    if(queue.isEmpty()) {
                        sb.append("-1\n");
                    }
                    else {
                        sb.append(queue.get(queue.size()-1)).append("\n");
                    }
                    break;
            }
        }

        System.out.println(sb.toString());
    }

}
