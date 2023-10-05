import java.io.*;
import java.util.*;

public class Main {

    public static class Tree implements Comparable<Tree>{
        int x, y;
        int age;
        boolean isDead;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
            this.isDead = false;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }

    public static int n;
    public static int[][] map, s2d2;
    public static ArrayList<Tree> trees;
    public static Queue<Integer> deadTrees;

    public static int[] dx = {0, 0, 1, -1, -1, -1, 1, 1};
    public static int[] dy = {1, -1, 0, 0, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];
        s2d2 = new int[n+1][n+1];
        trees = new ArrayList<>();
        deadTrees = new ArrayDeque<>();

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                map[i][j] = 5;
                s2d2[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(m-->0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x, y, age));
        }

        Collections.sort(trees);

        while(k-->0) {
            spring();
            summer();
            fall();
            winter();
        }

        System.out.println(trees.size());
    }

    public static void spring() {
        for(int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            int x = tree.x;
            int y = tree.y;
            int age = tree.age;

            if(map[x][y] >= age) {
                map[x][y] -= age;
                tree.age++;
            }
            else {
                deadTrees.offer(i);
            }
        }
    }

    public static void summer() {
        while(!deadTrees.isEmpty()) {
            int idx = deadTrees.poll();
            Tree deadTree = trees.get(idx);
            map[deadTree.x][deadTree.y] += deadTree.age/2;
            deadTree.isDead = true;
        }
    }

    public static void fall() {
        ArrayList<Tree> newTrees = new ArrayList<>();

        for (Tree tree : trees) {
            if(!tree.isDead && tree.age % 5 == 0) {
                for(int i = 0; i < 8; i++) {
                    int nx = tree.x + dx[i];
                    int ny = tree.y + dy[i];

                    if(isRange(nx, ny)) {
                        newTrees.add(new Tree(nx, ny, 1));
                    }
                }
            }
        }

        for (Tree tree : trees) {
            if(!tree.isDead) {
                newTrees.add(tree);
            }
        }

        trees = newTrees;
    }

    public static void winter() {
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                map[i][j] += s2d2[i][j];
            }
        }
    }

    public static boolean isRange(int x, int y) {
        return x >= 1 && x <= n && y >= 1 && y <= n;
    }

}
