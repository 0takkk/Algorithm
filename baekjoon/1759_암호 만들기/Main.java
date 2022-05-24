import java.io.*;
import java.util.*;

public class Main {

    public static int l, c;
    public static char[] alpa;
    public static HashSet<Character> set = new HashSet<>(List.of('a', 'e', 'i', 'o', 'u'));
    public static StringBuilder sb = new StringBuilder();
    public static char[] code;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        alpa = new char[c];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < c; i++){
            alpa[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(alpa);
        code = new char[l];


        backtracking(0, 0);
        System.out.println(sb.toString());
    }

    public static void backtracking(int cnt, int idx){
        if(cnt == l){
            if(check()){
                StringBuilder sb1 = new StringBuilder();
                for(char c : code){
                    sb1.append(c);
                }
                sb.append(sb1.toString()+"\n");
            }
            return;
        }

        for(int i = idx; i < c; i++){
            code[cnt] = alpa[i];
            backtracking(cnt+1, i+1);
        }
    }

    public static boolean check(){
        boolean flag = false;
        int count = 0;

        for(char c : code){
            if(set.contains(c)){
                flag = true;
            }
            else{
                count++;
            }

            if(flag && count >= 2){
                return true;
            }
        }

        return false;
    }

}
