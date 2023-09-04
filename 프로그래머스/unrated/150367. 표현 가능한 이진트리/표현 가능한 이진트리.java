class Solution {
    public static boolean isPossible;
    public static int size;
    public static int[] tree;

    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            long number = numbers[i];

            isPossible = true;
            String binaryNumber = Long.toBinaryString(number);
            size = getSize(binaryNumber.length());
            tree = new int[size];

            for(int idx = binaryNumber.length()-1, j = 1; idx >= 0; idx--, j++) {
                tree[size-j] = binaryNumber.charAt(idx) - '0';
            }

            rec(0, size-1);
            answer[i] = isPossible ? 1 : 0;
        }

        return answer;
    }

    public static int getSize(int length) {
        int size = 1;
        int pow = 1;
        while (size < length) {
            size = (int) Math.pow(2, pow++)-1;
        }
        return size;
    }

    public static void rec(int left, int right) {
        if(left == right) return;

        int mid = (left+right) / 2;

        if(tree[mid] == 0) {
            for(int i = left; i <= right; i++) {
                if(tree[i] == 1) {
                    isPossible = false;
                }
            }
            return;
        }

        rec(left, mid-1);
        rec(mid+1, right);
    }
}