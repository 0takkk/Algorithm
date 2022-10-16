import java.io.*;
import java.util.*;

public class Solution {

    public static class Customer{
        int num;
        int arrivalTime;
        int useReceptionNum;
        int receptionEndTime;

        int useRepairNum;
        int repairEndTime;

        public Customer(int num, int arrivalTime) {
            this.num = num;
            this.arrivalTime = arrivalTime;
        }
    }

    public static int n, m, k, a, b, ans;
    public static int[] receptionTime, repairTime;
    public static Queue<Customer> arrivalCustomer;
    public static PriorityQueue<Integer> emptyReception, emptyRepair;
    public static PriorityQueue<Customer> readyReception, readyRepair, useReception, useRepair;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 1; tc <= t; tc++) {
            ans = 0;

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            receptionTime = new int[n+1];
            repairTime = new int[m+1];
            arrivalCustomer = new ArrayDeque<>();
            emptyReception = new PriorityQueue<>();
            emptyRepair = new PriorityQueue<>();

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++){
                receptionTime[i] = Integer.parseInt(st.nextToken());
                emptyReception.add(i);
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= m; i++){
                repairTime[i] = Integer.parseInt(st.nextToken());
                emptyRepair.add(i);
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= k; i++){
                arrivalCustomer.add(new Customer(i, Integer.parseInt(st.nextToken())));
            }

            readyReception = new PriorityQueue<>(Comparator.comparingInt(c -> c.num));
            readyRepair = new PriorityQueue<>((c1, c2) -> {
                if(c1.receptionEndTime == c2.receptionEndTime) return c1.useReceptionNum - c2.useReceptionNum;
                return c1.receptionEndTime - c2.receptionEndTime;
            });
            useReception = new PriorityQueue<>((c1, c2) -> {
                if (c1.receptionEndTime == c2.receptionEndTime) return c1.useReceptionNum - c2.useReceptionNum;
                return c1.receptionEndTime - c2.receptionEndTime;
            });
            useRepair = new PriorityQueue<>(Comparator.comparingInt(c -> c.repairEndTime));

            goCarCenter();


            sb.append("#").append(tc).append(" ").append(ans == 0 ? -1 : ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void goCarCenter(){
        int remainCustomer = k;
        int time = 0;

        while(remainCustomer > 0){

            while(!useRepair.isEmpty() && useRepair.peek().repairEndTime <= time){
                Customer customer = useRepair.poll();
                if(customer.useReceptionNum == a && customer.useRepairNum == b) ans += customer.num;
                remainCustomer--;
                emptyRepair.add(customer.useRepairNum);
            }

            while(!readyRepair.isEmpty() && readyRepair.peek().receptionEndTime <= time){
                if(emptyRepair.isEmpty()) break;

                Customer customer = readyRepair.poll();
                int repairNum = emptyRepair.poll();
                customer.useRepairNum = repairNum;
                customer.repairEndTime = time + repairTime[repairNum];
                useRepair.add(customer);
            }

            while(!useReception.isEmpty() && useReception.peek().receptionEndTime <= time){
                if(emptyRepair.isEmpty()){
                    emptyReception.add(useReception.peek().useReceptionNum);
                    readyRepair.add(useReception.poll());
                }
                else{
                    Customer customer = useReception.poll();
                    int repairNum = emptyRepair.poll();
                    customer.useRepairNum = repairNum;
                    customer.repairEndTime = time + repairTime[repairNum];
                    useRepair.add(customer);

                    emptyReception.add(customer.useReceptionNum);
                }
            }

            while(!readyReception.isEmpty()){
                if(emptyReception.isEmpty()) break;

                Customer customer = readyReception.poll();
                int receptionNum = emptyReception.poll();
                customer.useReceptionNum = receptionNum;
                customer.receptionEndTime = time + receptionTime[receptionNum];
                useReception.add(customer);
            }

            while(!arrivalCustomer.isEmpty() && arrivalCustomer.peek().arrivalTime <= time){
                if(emptyReception.isEmpty()) {
                    readyReception.add(arrivalCustomer.poll());
                }
                else{
                    Customer customer = arrivalCustomer.poll();
                    int receptionNum = emptyReception.poll();
                    customer.useReceptionNum = receptionNum;
                    customer.receptionEndTime = time + receptionTime[receptionNum];
                    useReception.add(customer);
                }
            }

            time++;
        }
    }

}
