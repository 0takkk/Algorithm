import java.io.*;
import java.util.*;

public class Main {

    public static class Room{
        int comd, attack, hp;

        public Room(int comd, int attack, int hp) {
            this.comd = comd;
            this.attack = attack;
            this.hp = hp;
        }
    }

    public static int n;
    public static Room[] rooms;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int atk = Integer.parseInt(st.nextToken());

        rooms = new Room[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int comd = Integer.parseInt(st.nextToken());
            int attack = Integer.parseInt(st.nextToken());
            int hp = Integer.parseInt(st.nextToken());

            rooms[i] = new Room(comd, attack, hp);
        }

        long left = 1L;
        long right = 1_000_000_000_000L * n;

        while(left <= right){
            long mid = (left + right) / 2;

            if(game(atk, mid)) right = mid-1;
            else left = mid+1;
        }

        System.out.println(left);
    }

    public static boolean game(int atk, long maxHp){
        long curAtk = atk;
        long curHp = maxHp;

        for(int i = 0; i < n; i++){
            if(rooms[i].comd == 1){
                int monsterHp = rooms[i].hp;
                int monsterAtk = rooms[i].attack;

                long cnt = monsterHp / curAtk;
                if(monsterHp % curAtk == 0) cnt--;

                curHp -= (monsterAtk * cnt);

                if(curHp <= 0) return false;
            }
            else{
                curAtk += rooms[i].attack;
                curHp = Math.min(maxHp, curHp + rooms[i].hp);
            }
        }

        return true;
    }

}
