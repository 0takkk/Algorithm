import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        String[] solution = solution(
                new String[][]{
                        {"science", "12:40", "50"},
                        {"music", "12:20", "40"},
                        {"history", "14:00", "30"},
                        {"computer", "12:30", "100"}
                });

        for (String s : solution) {
            System.out.print(s + " ");
        }
    }

    public static class Task implements Comparable<Task>{
        String name;
        int start;
        int remain;

        public Task(String name, int start, int remain) {
            this.name = name;
            this.start = start;
            this.remain = remain;
        }

        @Override
        public int compareTo(Task o) {
            return this.start - o.start;
        }
    }

    public static String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        ArrayList<String> ans = new ArrayList<>();

        Task[] tasks = new Task[plans.length];
        for (int i = 0; i < plans.length; i++) {
            String[] plan = plans[i];
            tasks[i] = new Task(plan[0], timeToMin(plan[1]), Integer.parseInt(plan[2]));
        }

        Arrays.sort(tasks);

        Stack<Task> ready = new Stack<>();

        for(int i = 0; i < plans.length - 1; i++){
            Task now = tasks[i];
            Task next = tasks[i+1];

            int nowEnd = now.start + now.remain;

            if(nowEnd <= next.start){
                ans.add(now.name);

                int time = next.start - nowEnd;

                while(time > 0 && !ready.isEmpty()){
                    int del = ready.peek().remain - time;
                    ready.peek().remain = Math.max(0, del);
                    if(del <= 0){
                        time = -1 * del;
                        ans.add(ready.pop().name);
                    }else{
                        time = 0;
                    }
                }
            }
            else {
                now.remain = nowEnd - next.start;
                ready.push(now);
            }
        }

        ans.add(tasks[tasks.length-1].name);

        while(!ready.isEmpty()){
            ans.add(ready.pop().name);
        }

        for(int i = 0; i < plans.length; i++){
            answer[i] = ans.get(i);
        }

        return answer;
    }

    public static int timeToMin(String time){
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }

}
