import java.io.*;
import java.util.*;

public class Main {

    public static class Lecture implements Comparable<Lecture>{
        int day, price;

        public Lecture(int day, int price) {
            this.day = day;
            this.price = price;
        }


        @Override
        public int compareTo(Lecture o) {
            if(o.price == this.price) return o.day - this.day;
            return o.price - this.price;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Lecture[] lectures = new Lecture[n];
        int max = 0;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());

            max = Math.max(max, day);
            lectures[i] = new Lecture(day, price);
        }

        Arrays.sort(lectures);
        int[] cnt = new int[max+1];
        
        int ans = 0;
        for(int i = 0; i < n; i++) {
            Lecture lecture = lectures[i];

            boolean flag = true;
            for(int j = lecture.day; j <= max; j++) {
                if(cnt[j] + 1 > j) {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                ans += lecture.price;
                for(int j = lecture.day; j <= max; j++) {
                    cnt[j]++;
                }
            }
        }

        System.out.println(ans);
    }

}
