import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int len = query.length;
        int[] answer = new int[len];
        
        Map<String, List<Integer>> map = new HashMap<>();
        
        for(String in : info) {
            String[] arr = in.split(" ");
            String developer = arr[0] + arr[1] + arr[2] + arr[3];
            int score = Integer.parseInt(arr[4]);
            
            if(map.get(developer) == null) {
                List<Integer> list = new ArrayList<>();
                map.put(developer, list);
            }
            
            List<Integer> list = map.get(developer);
            list.add(score);
            map.put(developer, list);
        }
        
        for(String key : map.keySet()) {
            Collections.sort(map.get(key));
        } 
        
        for(int i = 0; i < len; i++) {
            String q = query[i];
            String[] arr = q.split(" ");
            
            String language = arr[0];
            String job = arr[2];
            String career = arr[4];
            String food = arr[6];
            int score = Integer.parseInt(arr[7]);
            
            Queue<String> queue = new LinkedList<>();
            if(language.equals("-")) {
                queue.add("cpp");
                queue.add("java");
                queue.add("python");
            } else {
                queue.add(language);
            }
            
            int size = queue.size();
            while(size-->0) {
                String str = queue.poll();
                if(job.equals("-")) {
                    queue.offer(str + "backend");
                    queue.offer(str + "frontend");
                } else {
                    queue.offer(str + job);
                }
            }
            
            size = queue.size();
            while(size-->0) {
                String str = queue.poll();
                if(career.equals("-")) {
                    queue.offer(str + "junior");
                    queue.offer(str + "senior");
                } else {
                    queue.offer(str + career);
                }
            }
            
            size = queue.size();
            while(size-->0) {
                String str = queue.poll();
                if(food.equals("-")) {
                    queue.offer(str + "chicken");
                    queue.offer(str + "pizza");
                } else {
                    queue.offer(str + food);
                }
            }
            
            while(!queue.isEmpty()) {
                String str = queue.poll();
                
                if(map.get(str) != null) {
                    List<Integer> scores = map.get(str);
                    
                    answer[i] += scores.size() - binary(score, scores);
                }
            }
        }
        
        return answer;
    }
    
    public int binary(int score, List<Integer> scores) {
        if(scores.isEmpty() || score > scores.get(scores.size() - 1)) {
            return scores.size();
        }
                                                        
        int left = 0;
        int right = scores.size()-1;
        int result = right;
            
        while(left <= right) {
            int mid = (left + right) / 2;
            
            if(scores.get(mid) >= score) {
                right = mid-1;
                result = mid;
            } else {
                left = mid+1;
            }
        }
        
        return result;
    }
}