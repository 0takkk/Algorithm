import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int n, m, ans = Integer.MAX_VALUE;
    public static int[] pickedChicken;
    public static boolean[] visited;
    public static ArrayList<Pos> chickens, homes;
    public static int chickenSize;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        chickens = new ArrayList<>();
        homes = new ArrayList<>();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int num = Integer.parseInt(st.nextToken());
                if(num == 1){
                    homes.add(new Pos(i, j));
                }
                if(num == 2){
                    chickenSize++;
                    chickens.add(new Pos(i, j));
                }
            }
        }

        pickedChicken = new int[m];
        visited = new boolean[chickenSize];

        pick(0, 0);
        System.out.println(ans);
    }

    public static void pick(int idx, int cnt){
        if(cnt == m){
            ans = Math.min(ans, calcDist());
            return;
        }

        for(int i = idx; i < chickenSize; i++){
            if(!visited[i]){
                visited[i] = true;
                pickedChicken[cnt] = i;
                pick(i+1, cnt+1);
                visited[i] = false;
            }
        }
    }

    public static int calcDist(){
        int distSum = 0;
        for (Pos home : homes) {
            int dist = Integer.MAX_VALUE;
            for (int pick : pickedChicken) {
                dist = Math.min(dist, (Math.abs(home.x - chickens.get(pick).x) + Math.abs(home.y - chickens.get(pick).y)));
            }
            distSum += dist;
        }

        return distSum;
    }

}
