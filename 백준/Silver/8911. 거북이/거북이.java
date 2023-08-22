import java.io.*;
import java.util.*;

public class Main {

    public static class Robot {
        int x, y;
        int dir;
        int maxX, minX;
        int maxY, minY;

        public Robot(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            maxX = maxY = minX = minY = 0;
        }

        public void turnLeft() {
            dir--;
            if(dir == -1) dir = 3;
        }

        public void turnRight() {
            dir++;
            if(dir == 4) dir = 0;
        }

        public void moveFront() {
            x += dx[dir];
            y += dy[dir];
            calRange();
        }

        public void moveBack() {
            x -= dx[dir];
            y -= dy[dir];
            calRange();
        }

        private void calRange() {
            maxX = Math.max(maxX, x);
            minX = Math.min(minX, x);
            maxY = Math.max(maxY, y);
            minY = Math.min(minY, y);
        }

        public int calTotalRange() {
            int xMove = maxX - minX;
            int yMove = maxY - minY;
            return xMove * yMove;
        }
    }

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0) {
            String input = br.readLine();
            Robot robot = new Robot(0, 0, 0);
            for(int i = 0; i < input.length(); i++) {
                char comd = input.charAt(i);

                switch (comd) {
                    case 'F':
                        robot.moveFront();
                        break;
                    case 'B':
                        robot.moveBack();
                        break;
                    case 'L':
                        robot.turnLeft();
                        break;
                    default:
                        robot.turnRight();
                        break;
                }
            }

            int range = robot.calTotalRange();
            sb.append(range).append("\n");
        }

        System.out.println(sb.toString());
    }

}
