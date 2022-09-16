import java.io.*;
import java.util.*;

public class Main {

    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for(int i = 1; i <= n; i++){
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int truth = Integer.parseInt(st.nextToken());

        // 진실을 알고 있는 사람은 부모를 0으로 해서 집합을 만듬.
        while(truth-->0){
            parent[Integer.parseInt(st.nextToken())] = 0;
        }

        ArrayList<Integer>[] party = new ArrayList[m];
        for(int i = 0; i < m; i++){
            party[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());

            boolean isKnowTruth = false;
            for(int j = 0; j < count; j++){
                int person = Integer.parseInt(st.nextToken());
                party[i].add(person);

                if(parent[person] == 0) isKnowTruth = true;
            }

            if(isKnowTruth) union(party[i]);
        }

//        System.out.println(Arrays.toString(parent));

        int ans = 0;
        for(int i = 0; i < m-1; i++){
            for(int j = 0; j < m; j++) {
                union(party[j]);
            }
        }

        for(int i = 0; i < m; i++){
            if(union(party[i]) != 0) ans++;
        }

//        System.out.println(Arrays.toString(parent));

        System.out.println(ans);
    }

    public static int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    public static int union(ArrayList<Integer> list){
        int x = find(list.get(0));

        for(int i = 1; i < list.size(); i++){
            int y = find(list.get(i));

            if(y < x) parent[x] = y;
            else parent[y] = x;
        }

        return find(x);
    }
}
