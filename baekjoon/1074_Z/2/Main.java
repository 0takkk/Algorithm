import java.io.*;
import java.util.*;

public class Main {

    public static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int size = (int)Math.pow(2, n);
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        partition(r, c, size);
        System.out.println(ans);
    }

    public static void partition(int r, int c, int size){
        if(size == 1) return;

        int nSize = size / 2;
        if(r < nSize && c < nSize) partition(r, c, nSize);
        else if(r < nSize && c >= nSize) {
            ans += size * size / 4;
            partition(r, c - nSize, nSize);
        }
        else if(r >= nSize && c < nSize){
            ans += (size * size / 4) * 2;
            partition(r-nSize, c, nSize);
        }
        else{
            ans += (size * size / 4) * 3;
            partition(r-nSize, c-nSize, nSize);
        }
    }

}
