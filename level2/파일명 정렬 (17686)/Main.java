package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
	    System.out.println(solution(new String[] {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"}));
        //System.out.println(solution(new String[] {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"}));
    }

    public static String[] solution(String[] files) {
        Arrays.sort(files, new Comparator<String>(){
           public int compare(String s1, String s2){
               String head1 = s1.split("[0-9]")[0];
               String head2 = s2.split("[0-9]")[0];

               int result = head1.toLowerCase().compareTo(head2.toLowerCase());

               if(result == 0){
                    result = convertNum(s1, head1) - convertNum(s2, head2);
               }

               return result;
           }
        });

        return files;
    }

    public static int convertNum(String file, String head){
        file = file.substring(head.length());
        String result = "";
        for(char c : file.toCharArray()){
            if(Character.isDigit(c)){
                result += c;
            }
            else break;
        }
        return Integer.valueOf(result);
    }
}
