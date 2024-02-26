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
       int count = 0;
       for(int i = 0;i<names.size();i++)
       {
           String student = names.get(i);
           System.out.println(student);
           count++;
           System.out.println(count);
           if(i==33)
           {
               int[] finalCoord = findNull(seatingChart);
               seatingChart[finalCoord[0]][finalCoord[1]] = student;
               break;
           }
           boolean set = false;
           while(!set)
           {
               int row = (int)(Math.random()*6);
               int column = generateColumnNum(row);
               int left =column;
               int right = column;
               if(column!=0) right = column-1;
               if(column!=seatingChart[row].length-1) right = column+1;
               if(!sameSeat(student,row,column,originalSeats) && seatingChart[row][column] ==null)
               {
                   if(!wasNextToEachOther(seatingChart[row][left],student,originalSeats) && !wasNextToEachOther(seatingChart[row][right],student,originalSeats))
                   {
                       seatingChart[row][column] = student;
                       set = true;
                   }
               }
           }
       }
       print2DArray(seatingChart);
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
        String sSeat1 = Integer.toString(seat1);
        String sSeat2 = Integer.toString(seat2);
        String row1 = "1 2 3 4 5 6 7 8 9 10 11 12";
        String row2 = "13 14 15 16 17 18";
        String row3 = "19 20 21 22 23 24";
        String row4 = "25 26";
        String row5 = "27 28 29 30";
        String row6 = "31 32 33 34";
        if(row1.contains(sSeat1) && row1.contains(sSeat2)) return true;
        if(row2.contains(sSeat1) && row2.contains(sSeat2)) return true;
        if(row3.contains(sSeat1) && row3.contains(sSeat2)) return true;
        if(row4.contains(sSeat1) && row4.contains(sSeat2)) return true;
        if(row5.contains(sSeat1) && row5.contains(sSeat2)) return true;
        if(row6.contains(sSeat1) && row6.contains(sSeat2)) return true;
        return false;
    }
    public static boolean wasNextToEachOther(String name1, String name2,HashMap<String,Integer> original)
    {
        if(name1 == null || name2 == null || name1.contains("N/A") || name2.contains("N/A")) return false;
        int seat1 = original.get(name1);
        int seat2 = original.get(name2);
        System.out.println(seat1);
        System.out.println(seat2);
        if(Math.abs(seat2-seat1)==1 && inTheSameRow(seat1,seat2)) return true;
        return false;
    }

    public static int generateColumnNum(int rowNum)
    {
        if(rowNum==0){
            int random = (int)(Math.random()*12);
            while(random==4)random = (int)(Math.random()*12);
            return random;
        }
        else if (rowNum==1 || rowNum ==2) return (int)(Math.random()*6);
        else if(rowNum == 3) return (int)(Math.random()*2);
        else return (int)(Math.random()*4);
    }

    public static boolean sameSeat(String name,int row,int column, HashMap<String,Integer> prev)
    {
        int prevSeat =prev.get(name);
        String row1 = "1 2 3 4 5 6 7 8 9 10 11 12";
        String row2 = "13 14 15 16 17 18";
        String row3 = "19 20 21 22 23 24";
        String row4 = "25 26";
        String row5 = "27 28 29 30";
        String row6 = "31 32 33 34";
        String[] tempArray;
        if(row == 0) tempArray = row1.split(" ");
        else if(row == 1) tempArray = row2.split(" ");
        else if(row == 2) tempArray = row3.split(" ");
        else if(row == 3) tempArray = row4.split(" ");
        else if(row == 4) tempArray = row5.split(" ");
        else tempArray = row6.split(" ");
        if(Integer.parseInt(tempArray[column])==prevSeat) return true;
        return false;
    }
   public static int[] findNull (String[][] seatingChart)
    {
        int[] nullArray = {-1,-1};
        for(int i =0;i<seatingChart.length;i++)
        {
            for(int a = 0;a<seatingChart[i].length;a++)
            {
                if(seatingChart[i][a]==null){
                    nullArray[0] = i;
                    nullArray[1] = a;
                    return nullArray;
                }
            }
        }
        return nullArray;
    }

}