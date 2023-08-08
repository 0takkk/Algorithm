import java.util.*;

class Solution {
    
    public class Truck {
        int weight;
        int endTime;
        
        public Truck(int w, int e) {
            this.weight = w;
            this.endTime = e;
        }
    }
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        Queue<Truck> q = new ArrayDeque<>();
        int truckIdx = 0;
        int totalWeight = 0;
        
        while(truckIdx < truck_weights.length) {
            answer++;
            
            if(!q.isEmpty() && q.peek().endTime == answer) {
                Truck truck = q.poll();
                totalWeight -= truck.weight;
            }
            
            if(q.size() < bridge_length && totalWeight + truck_weights[truckIdx] <= weight) {
                totalWeight += truck_weights[truckIdx];
                q.offer(new Truck(truck_weights[truckIdx], answer + bridge_length));
                truckIdx++;
            }
            
        }
        
        while(!q.isEmpty()) {
            answer = q.poll().endTime;
        }
        
        return answer;
    }
}