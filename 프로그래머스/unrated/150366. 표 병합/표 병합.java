import java.util.*;

class Solution {
    public static final int SIZE = 50;
    public static String[][] map;
    public static int[][] mapMerge;
    public static int mergeIdx = 0;
    public static ArrayList<String> ans;

    public static String[] solution(String[] commands) {
        String[] answer = {};
        mergeIdx = 0;
        ans = new ArrayList<>();

        map = new String[SIZE+1][SIZE+1];
        mapMerge = new int[SIZE+1][SIZE+1];

        for(int i = 1; i <= SIZE; i++) {
            for(int j = 1; j <= SIZE; j++) {
                map[i][j] = "";
            }
        }

        for (String command : commands) {
            String[] comd = command.split(" ");

            switch (comd[0]) {
                case "UPDATE":
                    update(comd);
                    break;
                case "MERGE":
                    merge(comd);
                    break;
                case "UNMERGE":
                    unmerge(comd);
                    break;
                default:
                    print(comd);
                    break;
            }
        }

        answer = new String[ans.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = ans.get(i);
        }

        return answer;
    }

    public static void update(String[] comd) {
        if(comd.length == 3) {
            String val1 = comd[1];
            String val2 = comd[2];

            for(int i = 1; i <= SIZE; i++) {
                for(int j = 1; j <= SIZE; j++) {
                    if(map[i][j].equals(val1)) {
                        map[i][j] = val2;
                    }
                }
            }
        }
        else {
            int r = Integer.parseInt(comd[1]);
            int c = Integer.parseInt(comd[2]);
            String val = comd[3];

            int num = mapMerge[r][c];
            map[r][c] = val;
            if(num != 0) {
                for(int i = 1; i <= SIZE; i++) {
                    for(int j = 1; j <= SIZE; j++) {
                        if(mapMerge[i][j] == num) {
                            map[i][j] = val;
                        }
                    }
                }
            }
        }
    }

    public static void merge(String[] comd) {
        int r1 = Integer.parseInt(comd[1]);
        int c1 = Integer.parseInt(comd[2]);
        int r2 = Integer.parseInt(comd[3]);
        int c2 = Integer.parseInt(comd[4]);

        int num1 = mapMerge[r1][c1];
        int num2 = mapMerge[r2][c2];
        String val1 = map[r1][c1];
        String val2 = map[r2][c2];

        if(num1 == 0 && num2 == 0) {
            mergeIdx++;
            mapMerge[r1][c1] = mapMerge[r2][c2] = mergeIdx;

            if(val1.isBlank()) {
                map[r1][c1] = val2;
            }
            else {
                map[r2][c2] = val1;
            }
        }
        else if(num1 == 0 && num2 != 0) {
            mapMerge[r1][c1] = num2;

            if(val1.isBlank()) {
                map[r1][c1] = val2;
            }
            else {
                for(int i = 1; i <= SIZE; i++) {
                    for(int j = 1; j <= SIZE; j++) {
                        if(mapMerge[i][j] == num2) {
                            map[i][j] = val1;
                        }
                    }
                }
            }
        }
        else if(num1 != 0 && num2 == 0) {
            mapMerge[r2][c2] = num1;

            if(val1.isBlank()) {
                for(int i = 1; i <= SIZE; i++) {
                    for(int j = 1; j <= SIZE; j++) {
                        if(mapMerge[i][j] == num1) {
                            map[i][j] = val2;
                        }
                    }
                }
            }
            else {
                map[r2][c2] = val1;
            }
        }
        else {
            if(num1 == num2) return;

            if(val1.isBlank()) {
                for(int i = 1; i <= SIZE; i++) {
                    for(int j = 1; j <= SIZE; j++) {
                        if(mapMerge[i][j] == num1) {
                            map[i][j] = val2;
                        }
                    }
                }
            }
            else {
                for(int i = 1; i <= SIZE; i++) {
                    for(int j = 1; j <= SIZE; j++) {
                        if(mapMerge[i][j] == num2) {
                            map[i][j] = val1;
                        }
                    }
                }
            }

            for(int i = 1; i <= SIZE; i++) {
                for(int j = 1; j <= SIZE; j++) {
                    if(mapMerge[i][j] == num2) {
                        mapMerge[i][j] = num1;
                    }
                }
            }
        }
    }

    public static void unmerge(String[] comd) {
        int r = Integer.parseInt(comd[1]);
        int c = Integer.parseInt(comd[2]);

        int num = mapMerge[r][c];
        String val = map[r][c];

        if(num == 0) return;

        for(int i = 1; i <= SIZE; i++) {
            for(int j = 1; j <= SIZE; j++) {
                if(mapMerge[i][j] == num) {
                    mapMerge[i][j] = 0;
                    map[i][j] = "";
                }
            }
        }

        map[r][c] = val;
    }

    public static void print(String[] comd) {
        int r = Integer.parseInt(comd[1]);
        int c = Integer.parseInt(comd[2]);

        String val = map[r][c];

        if(val.isBlank()) {
            ans.add("EMPTY");
        }
        else {
            ans.add(val);
        }
    }
}