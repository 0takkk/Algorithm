public static int answer;
    public static HashMap<Character, Integer> map = new HashMap<>();
    public static int[] lines;
    public static boolean[] visited;
    public static String[] datas;

    public static int solution(int n, String[] data) {
        answer = 0;
        datas = data;

        map.put('A', 0); map.put('C', 1); map.put('F', 2); map.put('J', 3);
        map.put('M', 4); map.put('N', 5); map.put('R', 6); map.put('T', 7);

        lines = new int[8];
        visited = new boolean[8];

        dfs(0);

        return answer;
    }

    public static void dfs(int idx){
        if(idx == 8){
            if(check()) answer++;
            return;
        }

        for(int i = 0; i < 8; i++){
            if(!visited[i]){
                visited[i] = true;
                lines[idx] = i;
                dfs(idx+1);
                visited[i] = false;
            }
        }
    }

    public static boolean check(){
        for(String data : datas){
            int p1 = lines[map.get(data.charAt(0))];
            int p2 = lines[map.get(data.charAt(2))];
            int op = data.charAt(3);
            int num = data.charAt(4) - '0' + 1;

            if(op == '='){
                if(Math.abs(p1 - p2) != num) return false;
            }
            else if(op == '>'){
                if(Math.abs(p1 - p2) <= num) return false;
            }
            else{
                if(Math.abs(p1 - p2) >= num) return false;
            }
        }

        return true;
    }
