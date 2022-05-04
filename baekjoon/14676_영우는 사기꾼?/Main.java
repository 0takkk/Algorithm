import java.io.*;
import java.util.*;

public class Main {

    public static ArrayList<Integer>[] list;
    public static int[] degree, buildingCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        degree = new int[n+1];
        buildingCnt = new int[n+1];
        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++)
            list[i] = new ArrayList<>();

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list[x].add(y);
            degree[y]++;
        }

        boolean flag = true;
        while(k-->0){
            st = new StringTokenizer(br.readLine());
            int comd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());

            flag = check(comd, a);

            if(!flag) break;
        }

        if(flag) System.out.println("King-God-Emperor");
        else System.out.println("Lier!");
    }

    public static boolean check(int comd, int a){
        if(comd == 1){
            if(degree[a] == 0){

                if(++buildingCnt[a] == 1) {
                    for (int next : list[a]) {
                        degree[next]--;
                    }
                }
            }
            else return false;
        }
        else{
            int cnt = --buildingCnt[a];
            if(cnt < 0) return false;

            if(cnt == 0){
                for(int next : list[a]){
                    degree[next]++;
                }
            }
        }

        return true;
    }

}
