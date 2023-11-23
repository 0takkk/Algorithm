import java.util.*;

class Solution {
    public static int[][] solution(int[][] rc, String[] operations) {
        int n = rc.length;
        int m = rc[0].length;
        Deque<Integer> left = new ArrayDeque<>();
        Deque<Deque<Integer>> mid = new ArrayDeque<>();
        Deque<Integer> right = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            left.addLast(rc[i][0]);
            right.addLast(rc[i][m-1]);
            Deque<Integer> tmp = new ArrayDeque<>();
            for(int j = 1; j < m-1; j++) {
                tmp.addLast(rc[i][j]);
            }
            mid.addLast(tmp);
        }

        for (String operation : operations) {
            if(operation.equals("Rotate")) {
                mid.peekFirst().addFirst(left.pollFirst());
                right.addFirst(mid.peekFirst().pollLast());
                mid.peekLast().addLast(right.pollLast());
                left.addLast(mid.peekLast().pollFirst());
            }
            else {
                left.addFirst(left.pollLast());
                mid.addFirst(mid.pollLast());
                right.addFirst(right.pollLast());
            }
        }

        int[][] answer = new int[n][m];

        for(int i = 0; i < n; i++) {
            answer[i][0] = left.pollFirst();
            answer[i][m-1] = right.pollFirst();
            Deque<Integer> tmp = mid.pollFirst();
            for(int j = 1; j < m-1; j++) {
                answer[i][j] = tmp.pollFirst();
            }
        }

        return answer;
    }
}