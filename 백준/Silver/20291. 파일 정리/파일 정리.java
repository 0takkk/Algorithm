import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        HashMap<String, Integer> map = new HashMap<>();

        while(n-->0) {
            String file = br.readLine();
            String extension = file.split("\\.")[1];
            map.put(extension, map.getOrDefault(extension, 0) + 1);
        }

        String[] extensions = new String[map.size()];
        int idx = 0;
        for (String extension : map.keySet()) {
            extensions[idx++] = extension;
        }

        Arrays.sort(extensions);

        StringBuilder sb = new StringBuilder();
        for (String extension : extensions) {
            sb.append(extension).append(" ").append(map.get(extension)).append("\n");
        }

        System.out.println(sb.toString());
    }

}