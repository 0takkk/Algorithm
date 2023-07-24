import java.io.*;
import java.util.*;

public class Main {

    public static class TrieNode {
        HashMap<Character, TrieNode> child = new HashMap<>();
        boolean isLast;
        TrieNode fail;

        public void insert(String word) {
            TrieNode node = this;
            for(int i = 0; i < word.length(); i++) {
                node = node.child.computeIfAbsent(word.charAt(i), key -> new TrieNode());
            }

            node.isLast = true;
        }

        public void computeFailureLink() {
            Queue<TrieNode> q = new ArrayDeque<>();
            this.fail = this;
            q.add(this);

            while(!q.isEmpty()){
                TrieNode cur = q.poll();
                for(int i = 0; i < 26; i++){
                    char c = (char)(i+97);

                    TrieNode next = cur.child.get(c);
                    if(next == null) continue;

                    if(cur == this) {  // 루트의 자식이 실패하면 다시 루트로 돌아감.
                        next.fail = this;
                    } else {  // 아닌 경우, 부모의 실패 연결을 따라가면서 failure link를 찾는다.
                        TrieNode failureLinkNode = cur.fail;
                        while(failureLinkNode != this && failureLinkNode.child.get(c) == null) {
                            failureLinkNode = failureLinkNode.fail;
                        }

                        if(failureLinkNode.child.get(c) != null) {
                            failureLinkNode = failureLinkNode.child.get(c);
                        }
                        next.fail = failureLinkNode;
                    }

                    if(next.fail.isLast){
                        next.isLast = true;
                    }

                    q.offer(next);
                }
            }
        }

        public boolean ahoCorasick(String word) {
            TrieNode node = this;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                while(node != this && node.child.get(c) == null) {
                    node = node.fail;
                }

                if(node.child.get(c) != null) {
                    node = node.child.get(c);
                }

                if(node.isLast) return true;
            }
            return false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        TrieNode trieNode = new TrieNode();

        while (n-- > 0) {
            trieNode.insert(br.readLine());
        }

        trieNode.computeFailureLink();

        StringBuilder sb = new StringBuilder();
        int q = Integer.parseInt(br.readLine());

        while (q-- > 0) {
            if (trieNode.ahoCorasick(br.readLine())) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }

        System.out.println(sb.toString());
    }

}
