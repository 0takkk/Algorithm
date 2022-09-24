import java.io.*;
import java.util.*;

public class Main {

    public static int n, size;
    public static ArrayList<Integer> corner, edge, in;
    public static boolean[] cornerVisited, edgeVisited, inVisited;
    public static int[][] map, rotate;
    public static int[][][] dice;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        size = n*n;

        corner = new ArrayList<>();
        edge = new ArrayList<>();
        in = new ArrayList<>();

        map = new int[n][n];
        rotate = new int[n][n];

        dice = new int[size+1][4][4];

        for(int i = 0; i < size; i++){
            st = new StringTokenizer(br.readLine());
            int zero = 0;
            int idx = Integer.parseInt(st.nextToken());

            for(int j = 0; j < 4; j++){
                int num = Integer.parseInt(st.nextToken());
                dice[idx][0][j] = num;
                if(num == 0) zero++;

                for(int k = 1; k < 4; k++){
                    dice[idx][k][(j+k)%4] = num;
                }
            }

            if(zero == 0) in.add(idx);
            else if(zero == 1) edge.add(idx);
            else corner.add(idx);
        }

        cornerVisited = new boolean[corner.size()];
        edgeVisited = new boolean[edge.size()];
        inVisited = new boolean[in.size()];


        setDice(0);
    }

    public static void setDice(int idx){
        if(idx == size){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    sb.append(rotate[i][j]).append(" ");
                }
                sb.append("\n");
            }

            System.out.println(sb.toString());

            System.exit(0);
            return;
        }

            int x = idx / n;
            int y = idx % n;


            if(isCorner(x, y)){
                for(int j = 0; j < corner.size(); j++){
                    if(!cornerVisited[j]){
                        cornerVisited[j] = true;
                        int num = corner.get(j);
                        map[x][y] = num;

                        for(int k = 0; k < 4; k++){
                            if(x == 0 && y == 0 &&
                                    dice[num][k][0] == 0 && dice[num][k][3] == 0){
                                rotate[x][y] = k;
                                setDice(idx+1);
                            }
                            else if(x == 0 && y == n-1
                                    && dice[num][k][0] == 0 && dice[num][k][1] == 0
                                    && dice[num][k][3] == dice[map[x][y-1]][rotate[x][y-1]][1]) {
                                rotate[x][y] = k;
                                setDice(idx + 1);
                            }
                            else if(x == n-1 && y == 0
                                    && dice[num][k][2] == 0 && dice[num][k][3] == 0
                                    && dice[num][k][0] == dice[map[x-1][y]][rotate[x-1][y]][2]){
                                rotate[x][y] = k;
                                setDice(idx + 1);
                            }
                            else if(x == n-1 && y == n-1
                                    && dice[num][k][1] == 0 && dice[num][k][2] == 0
                                    && dice[num][k][0] == dice[map[x-1][y]][rotate[x-1][y]][2]
                                    && dice[num][k][3] == dice[map[x][y-1]][rotate[x][y-1]][1]){
                                rotate[x][y] = k;
                                setDice(idx + 1);
                            }
                        }

                        cornerVisited[j] = false;
                    }
                }
            }else if(isEdge(x, y)){
                for(int j = 0; j < edge.size(); j++){
                    if(!edgeVisited[j]){
                        edgeVisited[j] = true;
                        int num = edge.get(j);
                        map[x][y] = num;

                        for(int k = 0; k < 4; k++) {
                            if (x == 0 && y != 0 && y != n - 1
                                    && dice[num][k][0] == 0
                                    && dice[num][k][3] == dice[map[x][y - 1]][rotate[x][y - 1]][1]) {
                                rotate[x][y] = k;
                                setDice(idx + 1);
                            } else if (x == n - 1 && y != 0 && y != n - 1
                                    && dice[num][k][2] == 0
                                    && dice[num][k][3] == dice[map[x][y - 1]][rotate[x][y - 1]][1]
                                    && dice[num][k][0] == dice[map[x - 1][y]][rotate[x - 1][y]][2]) {
                                rotate[x][y] = k;
                                setDice(idx + 1);
                            } else if (y == 0 && x != 0 && x != n - 1
                                    && dice[num][k][3] == 0
                                    && dice[num][k][0] == dice[map[x - 1][y]][rotate[x - 1][y]][2]) {
                                rotate[x][y] = k;
                                setDice(idx + 1);
                            } else if (y == n - 1 && x != 0 && x != n - 1
                                    && dice[num][k][1] == 0
                                    && dice[num][k][3] == dice[map[x][y - 1]][rotate[x][y - 1]][1]
                                    && dice[num][k][0] == dice[map[x - 1][y]][rotate[x - 1][y]][2]) {
                                rotate[x][y] = k;
                                setDice(idx + 1);
                            }
                        }

                        edgeVisited[j] = false;
                    }
                }
            }else{
                for(int j = 0; j < in.size(); j++){
                    if(!inVisited[j]){
                        inVisited[j] = true;
                        int num = in.get(j);
                        map[x][y] = num;

                        for(int k = 0; k < 4; k++) {
                            if(dice[num][k][3] == dice[map[x][y - 1]][rotate[x][y - 1]][1]
                                    && dice[num][k][0] == dice[map[x - 1][y]][rotate[x - 1][y]][2]){
                                rotate[x][y] = k;
                                setDice(idx + 1);
                            }
                        }

                        inVisited[j] = false;
                    }
                }
            }


    }


    public static boolean isCorner(int x, int y){
        return (x == 0 && y == 0) || (x == 0 && y == n-1) || (x == n-1 && y == 0) || (x == n-1 && y == n-1);
    }

    public static boolean isEdge(int x, int y){
        return (x == 0 && y != 0 && y != n-1) || (x == n-1 && y != 0 && y != n-1)
                || (y == 0 && x != 0 && x != n-1) || (y == n-1 && x != 0 && x != n-1);
    }

}
