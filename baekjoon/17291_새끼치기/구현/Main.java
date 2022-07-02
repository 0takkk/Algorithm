import java.io.*;
import java.util.*;

public class Main {

    public static class Bug{
        int year, count;

        public Bug(int year, int count) {
            this.year = year;
            this.count = count;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Queue<Bug> q = new LinkedList<>();
        q.offer(new Bug(1, 0));

        for(int i = 2; i <= n; i++){
            int size = q.size();

            for(int j = 0; j < size; j++){
                Bug bug = q.poll();

                if((bug.year % 2 == 0 && bug.count+1 < 4) || (bug.year % 2 == 1 && bug.count+1 < 3))
                    q.offer(new Bug(bug.year, bug.count+1));

                q.offer(new Bug(i, 0));
            }

        }

        System.out.println(q.size());

    }

}
