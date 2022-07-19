import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int s = toTime(st.nextToken());
        int e = toTime(st.nextToken());
        int q = toTime(st.nextToken());
        
        HashSet<String> start = new HashSet<>();
        HashSet<String> end = new HashSet<>();
        
        String str = null;
        while((str = br.readLine()) != null) {
        	st = new StringTokenizer(str);
        	int time = toTime(st.nextToken());
        	String name = st.nextToken();
        	
        	if(Math.min(0, s-60) <= time && time <= s) {
        		start.add(name);
        	}
        	
        	if(e <= time && time <= q) {
        		end.add(name);
        	}
        }
        
        int cnt = 0;
        
        for(String name : start) {
        	if(end.contains(name)) cnt++;
        }
        
        System.out.println(cnt);
	}
	
	public static int toTime(String time) {
		String[] str = time.split(":");
		return Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
	}

}
