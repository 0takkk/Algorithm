import java.io.*;
import java.util.*;

public class Main {

    public static class Node{
        int h, t;

        public Node(int h, int t) {
            this.h = h;
            this.t = t;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Node[] days = new Node[n+1];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            days[i] = new Node(h, t);
        }

        int minHappy = 2000000000;
        int maxTired = -1;
        int maxHappy = -1;
        int minTired = 2000000000;

        Node[] young = new Node[n+1];
        Node[] old = new Node[n+1];

        young[0] = new Node(0, 0);
        for(int i = 1; i <= n; i++){
            if(days[i].h != 0 && days[i].h < minHappy){
                minHappy = days[i].h;
            }
            if(days[i].t != 0 && days[i].t > maxTired){
                maxTired = days[i].t;
            }

            young[i] = new Node(minHappy, maxTired);
        }

        for(int i = n; i > 0; i--){
            if(days[i].h != 0 && days[i].h > maxHappy){
                maxHappy = days[i].h;
            }
            if(days[i].t != 0 && days[i].t < minTired){
                minTired = days[i].t;
            }

            old[i] = new Node(maxHappy, minTired);
        }

        int k = -1;
        for(int i = n-1; i >= 0; i--){
            int yh = young[i].h;
            int yt = young[i].t;
            int oh = old[i+1].h;
            int ot = old[i+1].t;

            if(yh > oh && yt < ot){
                k = i;
                break;
            }
        }

        System.out.println(k);
    }

}
