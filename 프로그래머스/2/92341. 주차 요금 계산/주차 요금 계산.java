import java.util.*;

class Solution {
    public static class Car{
        String num;
        int fee;

        public Car(String num, int fee) {
            this.num = num;
            this.fee = fee;
        }
    }

    public static int[] solution(int[] fees, String[] records) {
        int[] answer = {};

        HashMap<String, Integer> totalTimes = new HashMap<>();
        HashMap<String, Integer> inTimes = new HashMap<>();

        for (String record : records) {
            String[] rec = record.split(" ");

            if(rec[2].equals("IN")) {
                inTimes.put(rec[1], calcTime(rec[0]));
            }
            else {
                int inTime = inTimes.get(rec[1]);
                int outTime = calcTime(rec[0]);
                totalTimes.put(rec[1], totalTimes.getOrDefault(rec[1], 0) + (outTime - inTime));

                inTimes.remove(rec[1]);
            }
        }

        int out = calcTime("23:59");
        for (String carNum : inTimes.keySet()) {
            totalTimes.put(carNum, totalTimes.getOrDefault(carNum, 0) + (out - inTimes.get(carNum)));
        }

        Car[] cars = new Car[totalTimes.size()];
        int idx = 0;

        for (String carNum : totalTimes.keySet()) {
            int fee = fees[1];
            int totalTime = totalTimes.get(carNum);

            if(totalTime > fees[0]) {
                fee += (int)Math.ceil((double) (totalTime - fees[0]) / fees[2]) * fees[3];
            }

            cars[idx++] = new Car(carNum, fee);
        }

        Arrays.sort(cars, (Comparator.comparing(o -> o.num)));

        answer = new int[cars.length];
        for(int i = 0; i < cars.length; i++) {
            answer[i] = cars[i].fee;
        }

        return answer;
    }

    public static int calcTime(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}