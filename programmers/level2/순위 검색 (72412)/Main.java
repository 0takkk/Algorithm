package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String[] info = new String[] {"java backend junior pizza 150",
                "python frontend senior chicken 210",
                "python frontend senior chicken 150",
                "cpp backend senior pizza 260",
                "java backend junior chicken 80",
                "python backend senior chicken 50"};

        String[] query = new String[] {"java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150"};

        for(int i : solution(info, query))
            System.out.println(i);

        for(Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    static HashMap<String, ArrayList<Integer>> map = new HashMap<>();

    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        for(String in : info)
            makeKey(in);

        ArrayList<String> keys = new ArrayList<>();
        for(Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()){
            if(keys.contains(entry.getKey())) continue;
            else{
                keys.add(entry.getKey());
                Collections.sort(entry.getValue());
            }
        }

        for(int i = 0; i < query.length; i++){
            String[] tmp = query[i].split(" ");
            String key = tmp[0] + tmp[2] + tmp[4] + tmp[6];

            if(!map.containsKey(key)){
                answer[i] = 0;
                continue;
            }

            ArrayList<Integer> arr = map.get(key);

            if(tmp[7].equals("-")) answer[i] = arr.size();
            else{
                int score = Integer.parseInt(tmp[7]);
                answer[i] = binarySearch(arr, score);
            }
        }

        return answer;
    }

    public static void makeKey(String info){
        String[] in = info.split(" ");

        String[] language = {in[0], "-"};
        String[] job = {in[1], "-"};
        String[] career = {in[2], "-"};
        String[] food = {in[3], "-"};
        int score = Integer.parseInt(in[4]);

        for(int a = 0; a < language.length; a++){
            for(int b = 0; b < job.length; b++){
                for(int c = 0; c < career.length; c++){
                    for(int d = 0; d < food.length; d++){
                        String key = language[a] + job[b] + career[c] + food[d];

                        if(map.containsKey(key)){
                            ArrayList<Integer> arr = map.get(key);
                            arr.add(score);
                            map.put(key, arr);
                        }
                        else{
                            ArrayList<Integer> arr = new ArrayList<>();
                            arr.add(score);
                            map.put(key, arr);
                        }
                    }
                }
            }
        }
        return;
    }

    public static int binarySearch(ArrayList<Integer> arr, int score){
        int start = 0;
        int end = arr.size();


        while(end > start){
            int mid = (start + end) / 2;

            if(arr.get(mid) >= score) end = mid;
            else start = mid + 1;
        }

        return arr.size() - start;
    }
}
