import java.util.*;
import java.io.*;


public class Main
{
    public static class Node{
        int len, speed;

        public Node(int len, int speed){
            this.len = len;
            this.speed = speed;
        }
    }

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Node[] limit = new Node[n];
        Node[] real = new Node[m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());

            limit[i] = new Node(len, speed);
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());

            real[i] = new Node(len, speed);
        }

        int ans = 0;
        int lIdx = 0, rIdx = 0;

        while(lIdx < n || rIdx < m){
            int lSpeed = limit[lIdx].speed;
            int rSpeed = real[rIdx].speed;

            if(rSpeed > lSpeed) ans = Math.max(ans, rSpeed - lSpeed);

            if(limit[lIdx].len > real[rIdx].len){
                limit[lIdx].len -= real[rIdx].len;
                rIdx++;
            }
            else if(limit[lIdx].len < real[rIdx].len){
                real[rIdx].len -= limit[lIdx].len;
                lIdx++;
            }
            else{
                lIdx++;
                rIdx++;
            }
        }
        
        System.out.println(ans);
    }
}
