import java.io.*;
import java.util.*;

public class Main {

    public static ArrayList<Integer> errors;
    public static boolean TWIRL, HOP, DIP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String input = "";
        StringBuilder sb = new StringBuilder();
        while((input = br.readLine()) != null){
            String[] arr = input.split(" ");
            errors = new ArrayList<>();
            TWIRL = false;
            HOP = false;
            DIP = false;

            checkRule(arr);

            sb.append("form ");
            if(errors.size() == 0){
                sb.append("ok: ");
            }
            else if(errors.size() == 1){
                sb.append("error ").append(errors.get(0)).append(": ");
            }
            else{
                sb.append("errors ").append(errors.get(0));

                for(int i = 1; i < errors.size(); i++){
                    if(i != errors.size()-1){
                        sb.append(", ").append(errors.get(i));
                    }
                    else{
                        sb.append(" and ").append(errors.get(i)).append(": ");
                    }
                }
            }

            for (String s : arr) {
                sb.append(s).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void checkRule(String[] arr){
        checkRule1(arr);
        checkRule2(arr);
        checkRule3();
        checkRule4(arr);
        checkRule5();
    }

    public static void checkRule1(String[] arr){
        boolean totalCheck = true;

        for(int i = 0; i < arr.length; i++){
            if(arr[i].equals("dip")) {
                DIP = true;
                boolean oneCheck = false;

                if(i-1 >= 0 && arr[i-1].equals("jiggle")) oneCheck = true;
                else if(i-2 >= 0 && arr[i-2].equals("jiggle")) oneCheck = true;
                else if(i+1 < arr.length && arr[i+1].equals("twirl")) oneCheck = true;

                if(!oneCheck){
                    totalCheck = false;
                    arr[i] = "DIP";
                }
            }

            if(arr[i].equals("twirl")) TWIRL = true;
            if(arr[i].equals("hop")) HOP = true;
        }

        if(!totalCheck){
            errors.add(1);
        }
    }

    public static void checkRule2(String[] arr){
        if(arr.length < 4
                || !arr[arr.length-3].equals("clap")
                || !arr[arr.length-2].equals("stomp")
                || !arr[arr.length-1].equals("clap")){
            errors.add(2);
        }
    }

    public static void checkRule3(){
        if(TWIRL && !HOP) errors.add(3);
    }

    public static void checkRule4(String[] arr){
        if(arr[0].equals("jiggle")) errors.add(4);
    }

    public static void checkRule5(){
        if(!DIP) errors.add(5);
    }

}
