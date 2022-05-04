import java.util.*;

public class Main {

    public static void main(String[] args) {

        for(int i : solution(new int[] {180, 5000, 10, 600},
                new String[] {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"}))
            System.out.print(i + " ");
        System.out.println();

        for(int i : solution(new int[] {120, 0, 60, 591},
                new String[] {"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"}))
            System.out.print(i + " ");
        System.out.println();

        for(int i : solution(new int[] {1, 461, 1, 10},
                new String[] {"00:00 1234 IN"}))
            System.out.print(i + " ");
        System.out.println();

    }

    public static HashMap<String, Integer> time_map = new HashMap<>();
    public static HashMap<String, Integer> cost_map = new HashMap<>();

    public static int[] solution(int[] fees, String[] records) {

        HashMap<String, String> map = new HashMap<>();

        for(String record : records){
            String[] tmp = record.split(" ");
            String time = tmp[0];
            String carNum = tmp[1];

            if(map.containsKey(carNum)){
                getTime(carNum, map.get(carNum), time);

                map.remove(carNum);
            }else{
                map.put(carNum, time);
            }
        }

        if(!map.isEmpty()){
            for(Map.Entry<String, String> entry : map.entrySet()){
                getTime(entry.getKey(), entry.getValue(), "23:59");
            }
        }

        getCost(fees);

        ArrayList<String> arr = new ArrayList<>();
        for(Map.Entry<String, Integer> entry :cost_map.entrySet())
            arr.add(entry.getKey());

        Collections.sort(arr);

        int[] answer = new int[arr.size()];

        int idx = 0;

        for(String a : arr){
            answer[idx] = cost_map.get(a);
            idx++;
        }

        time_map.clear();
        cost_map.clear();
        return answer;
    }

    public static void getTime(String carNum, String inTime, String outTime){
        int h = Integer.parseInt(outTime.substring(0,2)) - Integer.parseInt(inTime.substring(0,2));
        int m = Integer.parseInt(outTime.substring(3)) - Integer.parseInt(inTime.substring(3));

        if(m < 0){
            h = h-1;
            m = 60 + m;
        }

        int totalTime = h * 60 + m;

        if(time_map.containsKey(carNum)) time_map.put(carNum, time_map.get(carNum) + totalTime);
        else time_map.put(carNum, totalTime);
    }

    public static void getCost(int[] fees){
        for(Map.Entry<String, Integer> entry :time_map.entrySet()){
            if(entry.getValue() <= fees[0]){
                cost_map.put(entry.getKey(), fees[1]);
            }
            else{
                int tmp = (int)Math.ceil((double)(entry.getValue() - fees[0]) / fees[2]);
                int totalCost = fees[1] + tmp * fees[3];
                cost_map.put(entry.getKey(), totalCost);
            }
        }
    }

}
