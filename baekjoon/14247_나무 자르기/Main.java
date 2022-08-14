import java.io.*;
import java.util.*;

public class Main {

    public static class Tree implements Comparable<Tree>{
        int h, w;

        public Tree(int h, int w) {
            this.h = h;
            this.w = w;
        }

        @Override
        public int compareTo(Tree o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Tree> trees = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            trees.offer(new Tree(Integer.parseInt(st.nextToken()), Integer.parseInt(st2.nextToken())));
        }

        long ans = 0;
        for(int i = 0; i < n; i++){
            Tree tree = trees.poll();
            ans += ((long)tree.h + (long)(tree.w * i));
        }

        System.out.println(ans);
    }

}
