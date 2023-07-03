import java.util.*;

class Solution {
    public static class Node{
        int count, dist;

        public Node(int count, int dist) {
            this.count = count;
            this.dist = dist;
        }
    }

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        Stack<Node> delivery = new Stack<>();
        Stack<Node> pickup = new Stack<>();

        for(int i = 0; i < n; i++){
            if(deliveries[i] != 0) delivery.push(new Node(deliveries[i], i+1));
            if(pickups[i] != 0) pickup.push(new Node(pickups[i], i+1));
        }

        while(!delivery.isEmpty() || !pickup.isEmpty()){
            int deliveryCap = 0;
            int pickupCap = 0;
            int dist = 0;

            while(!delivery.isEmpty() && deliveryCap < cap){
                Node d = delivery.pop();
                dist = Math.max(dist, d.dist);

                if(deliveryCap + d.count <= cap){
                    deliveryCap += d.count;
                }
                else{
                    d.count -= (cap - deliveryCap);
                    delivery.push(d);
                    break;
                }
            }

            while(!pickup.isEmpty() && pickupCap < cap){
                Node p = pickup.pop();
                dist = Math.max(dist, p.dist);

                if(pickupCap + p.count <= cap){
                    pickupCap += p.count;
                }
                else {
                    p.count -= (cap - pickupCap);
                    pickup.push(p);
                    break;
                }
            }

            answer += 2L * dist;
        }

        return answer;
    }
}