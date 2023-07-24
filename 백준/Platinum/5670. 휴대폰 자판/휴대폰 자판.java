import java.io.*;
import java.util.*;

public class Main {

    public static class TrieNode {
        HashMap<Character, TrieNode> childNodes = new HashMap<>();
        boolean isLast;

    }

    public static class Trie {
        private TrieNode rootNode;

        public Trie() {
            rootNode = new TrieNode();
        }

        public void insert(String word){
            TrieNode node = this.rootNode;
            for(int i = 0; i < word.length(); i++){
                node = node.childNodes.computeIfAbsent(word.charAt(i), key -> new TrieNode());
            }
            node.isLast = true;
        }

        public int contains(String word){
            TrieNode node = this.rootNode;
            int cnt = 1;
            node = node.childNodes.get(word.charAt(0));

            for(int i = 1; i < word.length(); i++){
                if(node.childNodes.size() >= 2) {
                    cnt++;
                }
                else if(node.childNodes.size() == 1 && node.isLast){
                    cnt++;
                }

                char c = word.charAt(i);
                node = node.childNodes.get(c);
            }

            return cnt;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        String str = "";

        while((str = br.readLine()) != null){
            int n = Integer.parseInt(str);
            Trie trie = new Trie();
            String[] arr = new String[n];

            for(int i = 0; i < n; i++){
                arr[i] = br.readLine();
                trie.insert(arr[i]);
            }

            int cnt = 0;
            for (String s : arr) {
                cnt += trie.contains(s);
            }

            double avg = (double)cnt / n;
            sb.append(String.format("%.2f", avg)).append("\n");
        }

        System.out.println(sb.toString());
    }

}
