import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[k];
        for(int i = 0; i < k; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        HashSet<Integer> set = new HashSet<>();
        int count = 0;

        for(int i = 0; i < k; i++){
            int num = arr[i];
            if(set.isEmpty() || set.size() < n){
                set.add(num);
            }
            else{
                if(set.contains(num)) continue;

                ArrayList<Integer> list = new ArrayList<>();

                for(int j = i; j < k; j++){
                    int next = arr[j];
                    if(set.contains(next) && !list.contains(next)) {
                        list.add(next);
                    }
                }

                if(list.size() < n){
                    for (int s : set) {
                        if(!list.contains(s)){
                            set.remove(s);
                            break;
                        }
                    }
                }
                else{
                    set.remove(list.get(list.size()-1));
                }

                set.add(num);
                count++;
            }
        }

        System.out.println(count);
    }

}
