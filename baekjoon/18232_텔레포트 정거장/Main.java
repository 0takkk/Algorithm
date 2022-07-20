import java.util.*;
import java.io.*;

public class Main {

	public static class Node{
		int x, time;
		
		public Node(int x, int time) {
			this.x = x;
			this.time = time;
		}
	}
	
	public static int n, s, e;
	public static ArrayList<Integer>[] list;
	public static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        
        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
        	list[i] = new ArrayList<>();
        }
        
        while(m-->0) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	list[a].add(b);
        	list[b].add(a);
        }
        
        visited = new boolean[n+1];
        int ans = bfs();
        
        System.out.println(ans);
	}
	
	public static int bfs() {
		visited[s] = true;
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(s, 0));
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			int x = now.x;
			int time = now.time;
			
			if(x == e) return time;
			
			for(int i = 0; i < 2; i++) {
				int nx = 0;
				if(i == 0) nx = x-1;
				else nx = x+1;
				
				if(!isRange(nx)) continue;
				
				if(!visited[nx]) {
					visited[nx] = true;
					q.offer(new Node(nx, time+1));
				}
			}
			
			for(int next : list[x]) {
				if(!visited[next]) {
					visited[next] = true;
					q.offer(new Node(next, time+1));
				}
			}
		}
		
		return -1;
	}
	
	public static boolean isRange(int x) {
		return x > 0 && x <= n;
	}

}
