package samsung01;

import java.io.*;
import java.util.*;

public class Solution {

	public static class Node{
		int cnt;
		String map;
		int change;
		
		public Node(int cnt, String map, int change) {
			super();
			this.cnt = cnt;
			this.map = map;
			this.change = change;
		}
		
		
		
	}
	
	public static int n, m, k, ans;
	public static int[][] map;
	
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
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			dfs(0, 0);
			
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb.toString());		
	}
	
	
	public static void dfs(int idx, int cnt) {	
		if(check()) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		if(cnt >= ans) return;
		if(idx == n) return;
		
		int[] copy = map[idx].clone();
		
		dfs(idx+1, cnt);
		
		Arrays.fill(map[idx], 0);
		dfs(idx+1, cnt+1);
		
		Arrays.fill(map[idx], 1);
		dfs(idx+1, cnt+1);
		
		map[idx] = copy.clone();
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
