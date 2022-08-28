package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static class Pos{
		int x, y;
		int dist;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Pos(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
	public static int n, ans = Integer.MAX_VALUE;
	public static int[][] map;
	public static boolean[][] visited;
	
	public static int[] dx = {0, 0, 1, -1};
	public static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int name = 2;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] == 1) {
					namingIsland(new Pos(i, j), name++);
				}
			}
		}
		
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] >= 2) {
					visited = new boolean[n][n];
					getDist(new Pos(i, j));
				}
			}
		}
		
		System.out.println(ans);
	}
	
	public static void namingIsland(Pos pos, int name) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(pos);
		map[pos.x][pos.y] = name;
		
		while(!q.isEmpty()) {
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(!isRange(nx, ny)) continue;
				
				if(map[nx][ny] == 1) {
					map[nx][ny] = name;
					q.offer(new Pos(nx, ny));
				}
			}
		}
	}
	
	public static void getDist(Pos pos) {
		Queue<Pos> q = new ArrayDeque<>();
		q.offer(pos);
		visited[pos.x][pos.y] = true;
		
		int name = map[pos.x][pos.y];
		
		while(!q.isEmpty()) {
			Pos p = q.poll();
			int x = p.x;
			int y = p.y;
			int dist = p.dist;
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(!isRange(nx, ny)) continue;
				
				if(!visited[nx][ny] && map[nx][ny] != name) {
					visited[nx][ny] = true;
					if(map[nx][ny] == 0) {
						q.offer(new Pos(nx, ny, dist+1));
					} else {
						ans = Math.min(ans, dist);
					}
				}
			}
		}
	}
	
	public static boolean isRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
}
