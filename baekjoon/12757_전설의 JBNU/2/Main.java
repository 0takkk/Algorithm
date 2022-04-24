import java.io.*;
import java.security.Key;
import java.util.*;

public class Main {

    public static int k;
    public static TreeMap<Integer, Integer> db;
    public static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        db = new TreeMap<>();
        db.put(2000000000, -1);
        db.put(-2000000000, -1);

        while(n-->0){
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            db.put(key, val);
        }

        sb = new StringBuilder();

        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int key = Integer.parseInt(st.nextToken());

            if(command == 1){
                int val = Integer.parseInt(st.nextToken());
                db.put(key, val);
            } else if(command == 2){
                int val = Integer.parseInt(st.nextToken());
                update(key, val);
            } else {
                printVal(key);
            }
        }

        System.out.println(sb.toString());
    }

    public static void update(int key, int val){
        // ceilingKey -> treeMap에서 key보다 크거나 같은 값 중 최소 키 값
        // floorKey -> treeMap에서 key보다 작거나 같은 값 중 최대 키 값
        int rKey = db.ceilingKey(key);
        int lKey = db.floorKey(key);

        if(db.get(rKey) == db.get(lKey)){
            db.put(key, val);
        } else if(rKey - key < key - lKey && rKey - key <= k){
            db.put(rKey, val);
        } else if(rKey - key > key - lKey && key - lKey <= k){
            db.put(lKey, val);
        }
    }

    public static void printVal(int key){
        int rKey = db.ceilingKey(key);
        int lKey = db.floorKey(key);

        if(db.get(rKey) == db.get(lKey)){
            sb.append(db.get(key) + "\n");
        } else if(rKey - key == key - lKey && rKey - key <= k){
            sb.append("?\n");
        } else if(rKey - key < key - lKey && rKey - key <= k){
            sb.append(db.get(rKey) + "\n");
        } else if(rKey - key > key - lKey && key - lKey <= k){
            sb.append(db.get(lKey) + "\n");
        } else{
            sb.append("-1\n");
        }
    }

}
