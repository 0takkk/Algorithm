import java.io.*;
import java.util.*;

public class Main {

    public static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static HashMap<Integer, HashSet<Integer>> map;
    public static int a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new HashMap<>();

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        Pos[] pos = new Pos[n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pos[i] = new Pos(x, y);
            if(!map.containsKey(x)) map.put(x, new HashSet<>());
            map.get(x).add(y);
        }

        int ans = 0;
        for (Pos p : pos) {
            int x = p.x;
            int y = p.y;

            if(up(x,y) && right(x, y) && cross(x, y)) ans++;
        }

        System.out.println(ans);
    }

    public static boolean right(int x, int y) {
        if(!map.containsKey(x+a)) return false;
        return map.get(x+a).contains(y);
    }

    public static boolean up(int x, int y) {
        return map.get(x).contains(y+b);
    }

    public static boolean cross(int x, int y) {
        if(!map.containsKey(x+a)) return false;
        return map.get(x+a).contains(y+b);
    }

}