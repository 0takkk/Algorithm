import java.io.*;
import java.util.*;

public class Main {

    public static int[] months = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static class Person implements Comparable<Person> {
        String name;
        long fee;

        public Person(String name, long fee) {
            this.name = name;
            this.fee = fee;
        }

        @Override
        public int compareTo(Person o) {
            return this.name.compareTo(o.name);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        String duration = st.nextToken();
        int penalty = Integer.parseInt(st.nextToken());

        int dur = 0;
        String[] d = duration.split("/");
        dur += Integer.parseInt(d[0]) * 1440;
        String[] t = d[1].split(":");
        dur += (Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]));

        HashMap<String, HashMap<String, String>> map = new HashMap<>();
        HashMap<String, Long> fee = new HashMap<>();

        while(n-->0) {
            st = new StringTokenizer(br.readLine());
            String day = st.nextToken();
            String time = st.nextToken();
            String tool = st.nextToken();
            String name = st.nextToken();

            map.putIfAbsent(name, new HashMap<>());
            HashMap<String, String> borrow = map.get(name);

            if(borrow.containsKey(tool)) {
                String[] borrowedDay = borrow.get(tool).split(" ");
                int days = calcDay(borrowedDay[0], day);
                int times = calcTime(borrowedDay[1], time);

                int total = days * 1440 + times;
                if(total > dur) {
                    fee.put(name, fee.getOrDefault(name, 0L) + (long)(total - dur) * penalty);
                }

                borrow.remove(tool);
            }
            else {
                borrow.put(tool, day + " " + time);
            }
        }

        if(fee.isEmpty()) {
            System.out.println(-1);
            return;
        }

        ArrayList<Person> result = new ArrayList<>();
        for (String name : fee.keySet()) {
            result.add(new Person(name, fee.get(name)));
        }

        Collections.sort(result);

        StringBuilder sb = new StringBuilder();
        for (Person person : result) {
            sb.append(person.name).append(" ").append(person.fee).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int calcDay(String start, String end) {
        String[] s = start.split("-");
        int sMonth = Integer.parseInt(s[1]);
        int sDay = Integer.parseInt(s[2]);

        String[] e = end.split("-");
        int eMonth = Integer.parseInt(e[1]);
        int eDay = Integer.parseInt(e[2]);

        if(eMonth - sMonth > 0) {
            for(int i = sMonth; i < eMonth; i++) {
                eDay += months[i];
            }
        }

        return eDay - sDay;
    }

    public static int calcTime(String start, String end) {
        String[] s = start.split(":");
        int sHour = Integer.parseInt(s[0]);
        int sMinute = Integer.parseInt(s[1]);

        String[] e = end.split(":");
        int eHour = Integer.parseInt(e[0]);
        int eMinute = Integer.parseInt(e[1]);

        return (eHour * 60 + eMinute) - (sHour * 60 + sMinute);
    }

}