import java.io.*;
import java.util.*;

public class Main {

    public static int size, m;
    public static ArrayList<Integer> list;
    public static int[] selected;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();

        st = new StringTokenizer(br.readLine());

        while(n-->0){
            int num = Integer.parseInt(st.nextToken());

            if(!set.contains(num)){
                set.add(num);
                list.add(num);
            }
        }

        size = list.size();
        Collections.sort(list);
        selected = new int[m];

        dfs(0, 0);

        System.out.println(sb.toString());
    }

    public static void dfs(int idx, int cnt){
        if(cnt == m){
            for(int i = 0; i < m; i++){
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = idx; i < size; i++){
            selected[cnt] = list.get(i);
            dfs(i, cnt+1);
        }
    }

}
