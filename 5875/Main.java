package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String input = st.nextToken();
        char[] arr = input.toCharArray();

        int leftSum = 0, rightSum = 0, totalSum = 0;
        Stack<Integer> left = new Stack<>();
        ArrayList<Integer> right = new ArrayList<>();
        int[] leftList = new int[arr.length];
        int[] rightList = new int[arr.length];

        for(int i = 0; i < arr.length; i++){
            if(arr[i] == '('){
                leftSum += 1;
                totalSum += 1;
                left.push(i);
            }
            else{
                rightSum += 1;
                totalSum -= 1;
                if(!left.isEmpty())
                    left.pop();
                else
                    right.add(i);
            }

            leftList[i] = leftSum;
            rightList[i] = rightSum;
        }

        if(totalSum > 0)
            System.out.println(leftList[arr.length-1] - leftList[left.pop()] + 1);
        else if(totalSum < 0)
            System.out.println(rightList[right.get(0)]);
        else
            System.out.println(0);
    }
}
