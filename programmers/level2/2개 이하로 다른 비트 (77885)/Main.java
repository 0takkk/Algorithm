package com.company;

public class Main {
    public static void main(String[] args) {
	    System.out.println(solution(new long[] {2,7}));

    }

    public static long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        int index = 0;

        for(long number : numbers){
            String num = Long.toBinaryString(number);
            num = "0" + num;

            if(number % 2 == 0){
                answer[index] = number + 1;
            }else{
                StringBuilder tmp = new StringBuilder();
                int lastIndex = num.lastIndexOf("0");
                tmp.append(num, 0, lastIndex).append("1");
                tmp.append("0");
                tmp.append(num, lastIndex+2, num.length());
                answer[index] = Long.parseLong(tmp.toString(), 2);
            }
            index++;
        }
        return answer;
    }
}
