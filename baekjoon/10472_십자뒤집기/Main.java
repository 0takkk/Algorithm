import java.io.*;
import java.util.*;

public class Main {

    public static String target;
    public static HashSet<String> set;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();

        while(t-->0){
            set = new HashSet<>();

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 3; i++){
                sb.append(br.readLine());
            }

            target = sb.toString();

            result.append(bfs() + "\n");
        }
        System.out.println(result.toString());
    }

    public static int bfs(){
        Queue<String> q = new LinkedList<>();
        q.offer(".........");
        set.add(".........");
        int click = 0;

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++) {
                String str = q.poll();

                if (str.equals(target)) return click;

                for(int j = 0; j < 9; j++){
                    int x = j / 3;
                    int y = j % 3;

                    String changed = change(str, j);

                    for(int k = 0; k < 4; k++){
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        if(nx < 0 || nx > 2 || ny < 0 || ny > 2) continue;

                        changed = change(changed, nx * 3 + ny);
                    }

                    if(!set.contains(changed)){
                        set.add(changed);
                        q.offer(changed);
                    }
                }

            }
            click++;
        }

        return -1;
    }

    public static String change(String str, int idx){
        StringBuilder sb = new StringBuilder(str);
        if(sb.charAt(idx) == '.') sb.setCharAt(idx, '*');
        else sb.setCharAt(idx, '.');
        return sb.toString();
    }

}
