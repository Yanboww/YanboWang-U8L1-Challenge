import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Main {
    public static void main(String[] args) {
       String[][] seatingChart = new String[6][];
       seatingChart[0] = new String[12];
       seatingChart[1] = new String[6];
       seatingChart[2] = new String[6];
       seatingChart[3] = new String[2];
       seatingChart[4] = new String[4];
       seatingChart[5] = new String[4];
       HashMap<String, Integer> originalSeats = new HashMap<>();
       ArrayList<String> names = new ArrayList<String>();
       File f;
       try{
           f = new File("Files/Names");
           Scanner s = new Scanner(f);
           while(s.hasNextLine())
           {
               String next = s.nextLine();
               if(!next.equals("-"))
               {
                   String[] temp = next.split(":");
                   temp[1]= temp[1].trim();
                   originalSeats.put(temp[1],Integer.parseInt(temp[0]));
                   names.add(temp[1]);
               }
           }
       }
       catch (FileNotFoundException e)
       {
           System.out.println("File not found");
       }
    }
    public static void print2DArray(String[][] seatingChart)
    {
        for(String[] seats : seatingChart)
        {
            System.out.println(Arrays.toString(seats));
        }
    }
    public static boolean inTheSameRow(int seat1, int seat2)
    {
        String row1 = "1 2 3 4 5 6 7 8 9 10 11 12";
        String row2 = "13 14 15 16 17 18";
        String row3 = "19 20 21 22 23 24";
        String row4 = "25 26";
        String row5 = "27 28 29 30";
        String row6 = "31 32 33 34";
    }
    public static boolean wasNextToEachOther(String name1, String name2,HashMap<String,Integer> original)
    {
        int seat1 = original.get(name1);
        int seat2 = original.get(name2);
        if(Math.abs(seat2-seat1)==1) return true;
        return false;
    }
}