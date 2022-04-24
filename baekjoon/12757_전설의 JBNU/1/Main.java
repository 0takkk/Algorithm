import java.io.*;
import java.util.*;

public class Main {

    public static int k;
    public static HashMap<Integer, Integer> db;
    public static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        db = new HashMap<>();
        list = new ArrayList<>();

        while(n-->0){
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());

            db.put(key, val);
            list.add(key);
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();

        while(m-->0){
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int key = Integer.parseInt(st.nextToken());

            if(num == 1){
                int value = Integer.parseInt(st.nextToken());

                int idx = lowerBound(key);
                if(idx == list.size()-1){

                    if(list.get(list.size()-1) < key){
                        list.add(key);
                    }else{
                        list.add(idx, key);
                    }
                } else{
                    list.add(idx, key);
                }

                db.put(key, value);
            }
            else if (num == 2) {
                int value = Integer.parseInt(st.nextToken());

                if(db.containsKey(key)){
                    db.put(key, value);
                } else{
                    int idx = lowerBound(key);
                    if(idx == 0){
                        if(Math.abs(list.get(idx) - key) <= k){
                            db.put(list.get(idx), value);
                        }
                    }
                    else {
                        int a = key - list.get(idx-1);
                        int b = list.get(idx) - key;

                        if(a <= k && b <= k){
                            if(a == b) continue;
                            idx = a < b ? idx-1 : idx;
                        } else if(a <= k){
                            idx = idx-1;
                        } else if(b <= k){
                            idx = idx;
                        } else {
                            continue;
                        }

                        db.put(list.get(idx), value);
                    }
                }
            }
            else if(num == 3){
                if(db.containsKey(key)) sb.append(db.get(key)+"\n");
                else{
                    int idx = lowerBound(key);

                    if(idx == 0){
                        if(Math.abs(list.get(idx) - key) <= k){
                            sb.append(db.get(list.get(idx)) + "\n");
                        }
                        else sb.append("-1\n");
                    }
                    else{
                        int a = Math.abs(key - list.get(idx-1));
                        int b = Math.abs(list.get(idx) - key);

                        if(a <= k && b <= k){
                            if(a == b) {
                                sb.append("?\n");
                                continue;
                            }
                            idx = a < b ? idx-1 : idx;
                        } else if(a <= k){
                            idx = idx-1;
                        } else if(b <= k){
                            idx = idx;
                        } else {
                            sb.append("-1\n");
                            continue;
                        }

                        sb.append(db.get(list.get(idx)) + "\n");
                    }
                }
            }
        }

        System.out.println(sb.toString());
    }

    public static int lowerBound(int target){
        int left = 0;
        int right = list.size()-1;

        while(left < right){
            int mid = (left + right) / 2;

            if(list.get(mid) >= target){
                right = mid;
            } else{
                left = mid+1;
            }
        }

        return right;
    }

}
