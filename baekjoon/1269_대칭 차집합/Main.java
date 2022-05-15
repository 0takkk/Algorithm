import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        HashSet<Integer> setA = new HashSet<>();
        HashSet<Integer> setB = new HashSet<>();

        int total = a + b;

        st = new StringTokenizer(br.readLine());
        while(a-->0){
            setA.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        while(b-->0){
            setB.add(Integer.parseInt(st.nextToken()));
        }

        int dupl = 0;
        for (int A : setA) {
            if(setB.contains(A)) dupl++;
        }

        System.out.println(total - dupl*2);
    }

}
