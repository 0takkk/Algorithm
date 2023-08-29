import java.io.*;
import java.util.*;

public class Main {

    public static class Bucket {
        int a, b, c;

        public Bucket(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static int A, B, C;
    public static Queue<Bucket> q;
    public static boolean[][][] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        visited = new boolean[A+1][B+1][C+1];

        bfs();

        HashSet<Integer> set = new HashSet<>();
        for(int b = 0; b <= B; b++) {
            for(int c = 0; c <= C; c++) {
                if(visited[0][b][c]) {
                    set.add(c);
                }
            }
        }

        List<Integer> ans = new ArrayList<>(set);
        Collections.sort(ans);

        StringBuilder sb = new StringBuilder();
        for (Integer n : ans) {
            sb.append(n).append(" ");
        }
        System.out.println(sb.toString());
    }

    public static void bfs() {
        q = new ArrayDeque<>();
        q.offer(new Bucket(0, 0, C));
        visited[0][0][C] = true;

        while(!q.isEmpty()) {
            Bucket bucket = q.poll();
            int a = bucket.a;
            int b = bucket.b;
            int c = bucket.c;

            aToB(a, b, c);
            aToC(a, b, c);
            bToA(a, b, c);
            bToC(a, b, c);
            cToA(a, b, c);
            cToB(a, b, c);
        }
    }

    public static void aToB(int a, int b, int c) {
        if(isAEmpty(a) || isBFull(b)) return;

        int moveAmount = Math.min(B-b, a);
        a -= moveAmount;
        b += moveAmount;

        pollQ(a, b, c);
    }

    public static void aToC(int a, int b, int c) {
        if(isAEmpty(a) || isCFull(c)) return;

        int moveAmount = Math.min(C-c, a);
        a -= moveAmount;
        c += moveAmount;

        pollQ(a, b, c);
    }

    public static void bToA(int a, int b, int c) {
        if(isBEmpty(b) || isAFull(a)) return;

        int moveAmount = Math.min(A-a, b);
        b -= moveAmount;
        a += moveAmount;

        pollQ(a, b, c);
    }

    public static void bToC(int a, int b, int c) {
        if(isBEmpty(b) || isCFull(c)) return;

        int moveAmount = Math.min(C-c, b);
        b -= moveAmount;
        c += moveAmount;

        pollQ(a, b, c);
    }

    public static void cToA(int a, int b, int c) {
        if(isCEmpty(c) || isAFull(a)) return;

        int moveAmount = Math.min(A-a, c);
        c -= moveAmount;
        a += moveAmount;

        pollQ(a, b, c);
    }

    public static void cToB(int a, int b, int c) {
        if(isCEmpty(c) || isBFull(b)) return;

        int moveAmount = Math.min(B-b, c);
        c -= moveAmount;
        b += moveAmount;

        pollQ(a, b, c);
    }

    private static void pollQ(int a, int b, int c) {
        if(!visited[a][b][c]) {
            visited[a][b][c] = true;
            q.offer(new Bucket(a, b, c));
        }
    }

    public static boolean isAEmpty(int a) {
        return a == 0;
    }

    public static boolean isBEmpty(int b) {
        return b == 0;
    }

    public static boolean isCEmpty(int c) {
        return c == 0;
    }

    public static boolean isAFull(int a) {
        return a == A;
    }

    public static boolean isBFull(int b) {
        return b == B;
    }

    public static boolean isCFull(int c) {
        return c == C;
    }

}
