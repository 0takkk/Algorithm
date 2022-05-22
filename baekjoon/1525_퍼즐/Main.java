import java.io.*;
import java.util.*;

public class Main {

    public static String target = "123456780";
    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                sb.append(st.nextToken());
            }
        }

        String start = sb.toString();
        System.out.println(bfs(start));
    }

    public static int bfs(String start){
        int time = 0;

        HashSet<String> set = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        set.add(start);

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                String now = q.poll();

                if(now.equals(target))
                    return time;

                int idx = now.indexOf('0');
                int x = idx / 3;
                int y = idx % 3;

                for(int j = 0; j < 4; j++){
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if(nx < 0 || nx > 2 || ny < 0 || ny > 2) continue;

                    int nIdx = nx * 3 + ny;
                    char c = now.charAt(nIdx);
                    String next = now.replace(c, '9');
                    next = next.replace('0', c);
                    next = next.replace('9', '0');

                    if(!set.contains(next)){
                        q.offer(next);
                        set.add(next);
                    }
                }
            }

            time++;
        }

        return -1;
    }

}
