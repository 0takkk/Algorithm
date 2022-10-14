import java.io.*;
import java.util.*;

public class Solution {

	public static class Node{
		int cnt;
		String map;
		int change;
		
		public Node(int cnt, String map, int change) {
			this.cnt = cnt;
			this.map = map;
			this.change = change;
		}
	}
	
	public static int n, m, k, ans;
	public static int[][] map, copy;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= t; tc++) {
			ans = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			map = new int[n][m];
			copy = new int[n][m];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < m; j++) {
					map[i][j] = copy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0, 0);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb.toString());		
	}
	
	
	public static void dfs(int idx, int cnt) {	
		if(cnt >= ans) return;
		
		if(idx == n) {
			if(check()) ans = Math.min(ans, cnt);
			return;
		}

		dfs(idx+1, cnt);
		
		injection(idx, 0);
		dfs(idx+1, cnt+1);
		
		injection(idx, 1);
		dfs(idx+1, cnt+1);
		
		for(int i = 0; i < m; i++) {
			map[idx][i] = copy[idx][i];
		}
	}
	
	public static void injection(int row, int state) {
		for(int i = 0; i < m; i++) {
			map[row][i] = state;
		}
	}
	
	
	public static boolean check() {
		for(int j = 0; j < m; j++) {
			int num = map[0][j];
			int count = 1;
			boolean flag = false;
			for(int i = 1; i < n; i++) {
				if(map[i][j] == num) {
					count++;
				}
				else {
					num = map[i][j];
					count = 1;
				}
				
				if(count >= k) {
					flag = true;
					break;
				}
			}
			
			if(!flag) return false;
		}
		
		return true;
	}
	
}
