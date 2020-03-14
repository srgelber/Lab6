import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class lab6 {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // read file
        File file = new File(args[0]);
        BufferedReader br1 = new BufferedReader(new FileReader(file));
        int count = 0;

        int cache1_hit = 0;
        int[] cache1 = new int[1024];
        int[] cache2 = new int[256];
        int[] cache3 = new int[128];
        int[] cache7 = new int[2048];
        Arrays.fill(cache1, -1);
        Arrays.fill(cache2, -1);
        Arrays.fill(cache3, -1);
        Arrays.fill(cache7, -1);

        int cache1_tag = 0;
        int cache1_index = 0;
        int cache2_tag = 0;
        int cache2_index = 0;
        int cache2_hit = 0;
        int cache3_index = 0;
        int cache3_hit = 0;
        int cache3_tag = 0;
        int cache7_index = 0;
        int cache7_hit = 0;
        int cache7_tag = 0;

        int[][] twoKb_twoWay_oneWord_wayOne = new int[(int)Math.pow(2,8)][2];
        int[][] twoKb_twoWay_oneWord_wayTwo = new int[(int)Math.pow(2,8)][2];
        int[][][] twoKb_twoWay_oneWord = {twoKb_twoWay_oneWord_wayOne, twoKb_twoWay_oneWord_wayTwo};
        int twoKb_twoWay_oneWord_hit = 0;

        int[][] twoKb_fourWay_oneWord_wayOne = new int[(int)Math.pow(2,7)][2];
        int[][] twoKb_fourWay_oneWord_wayTwo = new int[(int)Math.pow(2,7)][2];
        int[][] twoKb_fourWay_oneWord_wayThree = new int[(int)Math.pow(2,7)][2];
        int[][] twoKb_fourWay_oneWord_wayFour = new int[(int)Math.pow(2,7)][2];
        int[][][] twoKb_fourWay_oneWord = {twoKb_fourWay_oneWord_wayOne, twoKb_fourWay_oneWord_wayTwo,twoKb_fourWay_oneWord_wayThree , twoKb_fourWay_oneWord_wayFour };
        int twoKb_fourWay_oneWord_hit = 0;

        int[][] twoKb_fourWay_fourWord_wayOne = new int[(int)Math.pow(2,5)][2];
        int[][] twoKb_fourWay_fourWord_wayTwo = new int[(int)Math.pow(2,5)][2];
        int[][] twoKb_fourWay_fourWord_wayThree = new int[(int)Math.pow(2,5)][2];
        int[][] twoKb_fourWay_fourWord_wayFour = new int[(int)Math.pow(2,5)][2];
        int[][][] twoKb_fourWay_fourWord = {twoKb_fourWay_fourWord_wayOne, twoKb_fourWay_fourWord_wayTwo,twoKb_fourWay_fourWord_wayThree , twoKb_fourWay_fourWord_wayFour };
        int twoKb_fourWay_fourWord_hit = 0;


        String line = br1.readLine();
        while (line != null) {
            String[] split = line.split("\\t");

            int num = Integer.parseInt(split[1], 16);
            int address = num;

            //starting the code for the first cache
            cache1_tag = (address >> 11);
            cache1_index = address >> 2;
            cache1_index = (cache1_index & 511);

            if(cache1[cache1_index] == -1 ){
                cache1[cache1_index] = cache1_tag;
            }else if(cache1[cache1_index] == cache1_tag){
                cache1[cache1_index] = cache1_tag;
                cache1_hit += 1;
            }else{
                cache1[cache1_index] = cache1_tag;
            }

            //starting the code for the second cache
            cache2_tag = (address >> 10);
            cache2_index = address >> 3;
            cache2_index = (cache2_index & 255);

            if(cache2[cache2_index] == -1 ){
                cache2[cache2_index] = cache2_tag;
            }else if(cache2[cache2_index] == cache2_tag){
                cache2[cache2_index] = cache2_tag;
                cache2_hit += 1;
            }else{
                cache2[cache2_index] = cache2_tag;
            }

            //starting the code for the third cache
            cache3_tag = (address >> 10);
            cache3_index = address >> 4;
            cache3_index = (cache3_index & 127);

            if(cache3[cache3_index] == -1 ){
                cache3[cache3_index] = cache3_tag;
            }else if(cache3[cache3_index] == cache3_tag){
                cache3[cache3_index] = cache3_tag;
                cache3_hit += 1;
            }else{
                cache3[cache3_index] = cache3_tag;
            }

            //starting the code for the seventh cache
            cache7_tag = (address >> 12);
            cache7_index = address >> 2;
            cache7_index = (cache7_index & 1023);

            if(cache7[cache7_index] == -1 ){
                cache7[cache7_index] = cache7_tag;
            }else if(cache7[cache7_index] == cache7_tag){
                cache7[cache7_index] = cache7_tag;
                cache7_hit += 1;
            }else{
                cache7[cache7_index] = cache7_tag;
            }


            String binAddress = String.format("%32s", Integer.toBinaryString(num)).replace(' ', '0');
            int[] cell = new int[2];
            cell[1] = count;

            //twoKb_twoWay_oneWord
            String twoKb_twoWay_oneWord_strTag = binAddress.substring(0,22);
            String twoKb_twoWay_oneWord_strIndex = binAddress.substring(22,30);
            int twoKb_twoWay_oneWord_intTag = Integer.parseInt(twoKb_twoWay_oneWord_strTag, 2);
            int twoKb_twoWay_oneWord_intIndex = Integer.parseInt(twoKb_twoWay_oneWord_strIndex, 2);

            //twoKb_fourWay_oneWord
            String twoKb_fourWay_oneWord_strTag = binAddress.substring(0,23);
            String twoKb_fourWay_oneWord_strIndex = binAddress.substring(23,30);
            int twoKb_fourWay_oneWord_intTag = Integer.parseInt(twoKb_fourWay_oneWord_strTag, 2);
            int twoKb_fourWay_oneWord_intIndex = Integer.parseInt(twoKb_fourWay_oneWord_strIndex, 2);

            //twoKb_fourWay_fourWord
            String twoKb_fourWay_fourWord_strTag = binAddress.substring(0,23);
            String twoKb_fourWay_fourWord_strIndex = binAddress.substring(23,28);
            int twoKb_fourWay_fourWord_intTag = Integer.parseInt(twoKb_fourWay_fourWord_strTag, 2);
            int twoKb_fourWay_fourWord_intIndex = Integer.parseInt(twoKb_fourWay_fourWord_strIndex, 2);

            // twoKb_twoWay_oneWord
            cell[0] = twoKb_twoWay_oneWord_intTag;

            //System.out.println(Arrays.toString(cell));

            int twoKb_twoWay_oneWord_flag = 0;
            int twoKb_twoWay_oneWord_wayCnt;

            for (twoKb_twoWay_oneWord_wayCnt=0;twoKb_twoWay_oneWord_wayCnt<twoKb_twoWay_oneWord.length;twoKb_twoWay_oneWord_wayCnt++){

                if(twoKb_twoWay_oneWord[twoKb_twoWay_oneWord_wayCnt][twoKb_twoWay_oneWord_intIndex][0] == 0
                        && twoKb_twoWay_oneWord[twoKb_twoWay_oneWord_wayCnt][twoKb_twoWay_oneWord_intIndex][1] == 0 ){
                    twoKb_twoWay_oneWord[twoKb_twoWay_oneWord_wayCnt][twoKb_twoWay_oneWord_intIndex][0] = cell[0];
                    twoKb_twoWay_oneWord[twoKb_twoWay_oneWord_wayCnt][twoKb_twoWay_oneWord_intIndex][1] = cell[1];

                    twoKb_twoWay_oneWord_flag = 1;
                    break;
                }
                else if (twoKb_twoWay_oneWord[twoKb_twoWay_oneWord_wayCnt][twoKb_twoWay_oneWord_intIndex][0] == cell[0]){
                    twoKb_twoWay_oneWord_hit++;
                    twoKb_twoWay_oneWord_flag = 1;
                    twoKb_twoWay_oneWord[twoKb_twoWay_oneWord_wayCnt][twoKb_twoWay_oneWord_intIndex][1] = cell[1];
                    break;
                }
                else continue;
            }
            //System.out.println(twoKb_twoWay_oneWord_hit);


            if (twoKb_twoWay_oneWord_flag == 0){
                int minLine = twoKb_twoWay_oneWord[0][twoKb_twoWay_oneWord_intIndex][1];
                int replaceWay = 0;

                for (twoKb_twoWay_oneWord_wayCnt=0;twoKb_twoWay_oneWord_wayCnt<twoKb_twoWay_oneWord.length;twoKb_twoWay_oneWord_wayCnt++){

                    if(twoKb_twoWay_oneWord[twoKb_twoWay_oneWord_wayCnt][twoKb_twoWay_oneWord_intIndex][1] < minLine){
                        minLine = twoKb_twoWay_oneWord[twoKb_twoWay_oneWord_wayCnt][twoKb_twoWay_oneWord_intIndex][1];
                        replaceWay = twoKb_twoWay_oneWord_wayCnt;
                    }
                }

                twoKb_twoWay_oneWord[replaceWay][twoKb_twoWay_oneWord_intIndex][0] = cell[0];
                twoKb_twoWay_oneWord[replaceWay][twoKb_twoWay_oneWord_intIndex][1] = cell[1];

            }

            // twoKb_fourWay_oneWord
            cell[0] = twoKb_fourWay_oneWord_intTag;

            //System.out.println(Arrays.toString(cell));

            int twoKb_fourWay_oneWord_flag = 0;
            int twoKb_fourWay_oneWord_wayCnt;

            for (twoKb_fourWay_oneWord_wayCnt=0;twoKb_fourWay_oneWord_wayCnt<twoKb_fourWay_oneWord.length;twoKb_fourWay_oneWord_wayCnt++){

                if(twoKb_fourWay_oneWord[twoKb_fourWay_oneWord_wayCnt][twoKb_fourWay_oneWord_intIndex][0] == 0
                        && twoKb_fourWay_oneWord[twoKb_fourWay_oneWord_wayCnt][twoKb_fourWay_oneWord_intIndex][1] == 0 ){
                    twoKb_fourWay_oneWord[twoKb_fourWay_oneWord_wayCnt][twoKb_fourWay_oneWord_intIndex][0] = cell[0];
                    twoKb_fourWay_oneWord[twoKb_fourWay_oneWord_wayCnt][twoKb_fourWay_oneWord_intIndex][1] = cell[1];

                    twoKb_fourWay_oneWord_flag = 1;
                    break;
                }
                else if (twoKb_fourWay_oneWord[twoKb_fourWay_oneWord_wayCnt][twoKb_fourWay_oneWord_intIndex][0] == cell[0]){
                    twoKb_fourWay_oneWord_hit++;
                    twoKb_fourWay_oneWord_flag = 1;
                    twoKb_fourWay_oneWord[twoKb_fourWay_oneWord_wayCnt][twoKb_fourWay_oneWord_intIndex][1] = cell[1];
                    break;
                }
                else continue;
            }

            if (twoKb_fourWay_oneWord_flag == 0){
                int minLine = twoKb_fourWay_oneWord[0][twoKb_fourWay_oneWord_intIndex][1];
                int replaceWay = 0;

                for (twoKb_fourWay_oneWord_wayCnt=0;twoKb_fourWay_oneWord_wayCnt<twoKb_fourWay_oneWord.length;twoKb_fourWay_oneWord_wayCnt++){

                    if(twoKb_fourWay_oneWord[twoKb_fourWay_oneWord_wayCnt][twoKb_fourWay_oneWord_intIndex][1] < minLine){
                        minLine = twoKb_fourWay_oneWord[twoKb_fourWay_oneWord_wayCnt][twoKb_fourWay_oneWord_intIndex][1];
                        replaceWay = twoKb_fourWay_oneWord_wayCnt;
                    }
                }

                twoKb_fourWay_oneWord[replaceWay][twoKb_fourWay_oneWord_intIndex][0] = cell[0];
                twoKb_fourWay_oneWord[replaceWay][twoKb_fourWay_oneWord_intIndex][1] = cell[1];

            }

            // twoKb_fourWay_fourWord
            cell[0] = twoKb_fourWay_fourWord_intTag;

            //System.out.println(Arrays.toString(cell));


            int twoKb_fourWay_fourWord_flag = 0;
            int twoKb_fourWay_fourWord_wayCnt;

            for (twoKb_fourWay_fourWord_wayCnt=0;twoKb_fourWay_fourWord_wayCnt<twoKb_fourWay_fourWord.length;twoKb_fourWay_fourWord_wayCnt++){

                if(twoKb_fourWay_fourWord[twoKb_fourWay_fourWord_wayCnt][twoKb_fourWay_fourWord_intIndex][0] == 0
                        && twoKb_fourWay_fourWord[twoKb_fourWay_fourWord_wayCnt][twoKb_fourWay_fourWord_intIndex][1] == 0 ){
                    twoKb_fourWay_fourWord[twoKb_fourWay_fourWord_wayCnt][twoKb_fourWay_fourWord_intIndex][0] = cell[0];
                    twoKb_fourWay_fourWord[twoKb_fourWay_fourWord_wayCnt][twoKb_fourWay_fourWord_intIndex][1] = cell[1];
                    twoKb_fourWay_fourWord_flag = 1;
                    break;
                }
                else if (twoKb_fourWay_fourWord[twoKb_fourWay_fourWord_wayCnt][twoKb_fourWay_fourWord_intIndex][0] == cell[0]){
                    twoKb_fourWay_fourWord_hit++;
                    twoKb_fourWay_fourWord_flag = 1;
                    twoKb_fourWay_fourWord[twoKb_fourWay_fourWord_wayCnt][twoKb_fourWay_fourWord_intIndex][1] = cell[1];
                    break;
                }
                else continue;
            }

            if (twoKb_fourWay_fourWord_flag == 0){
                int minLine = twoKb_fourWay_fourWord[0][twoKb_fourWay_fourWord_intIndex][1];
                int replaceWay = 0;

                for (twoKb_fourWay_fourWord_wayCnt=0;twoKb_fourWay_fourWord_wayCnt<twoKb_fourWay_fourWord.length;twoKb_fourWay_fourWord_wayCnt++){

                    if(twoKb_fourWay_fourWord[twoKb_fourWay_fourWord_wayCnt][twoKb_fourWay_fourWord_intIndex][1] < minLine){
                        minLine = twoKb_fourWay_fourWord[twoKb_fourWay_fourWord_wayCnt][twoKb_fourWay_fourWord_intIndex][1];
                        replaceWay = twoKb_fourWay_fourWord_wayCnt;
                    }
                }

                twoKb_fourWay_fourWord[replaceWay][twoKb_fourWay_fourWord_intIndex][0] = cell[0];
                twoKb_fourWay_fourWord[replaceWay][twoKb_fourWay_fourWord_intIndex][1] = cell[1];

            }


            line = br1.readLine();
            count++;
        }
        br1.close();

        double percentage = 100.0 * cache1_hit/count;

        System.out.println("Cache #1");
        System.out.println("Cache size: 2048B   Associativity: 1    Block size: 1");
        System.out.print("Hits: " + cache1_hit + "    Hit Rate: ");
        System.out.printf("%.2f",percentage);
        System.out.println("%");
        System.out.println("---------------------------");

        //cache2
        double percentage2 = 100.0 * cache2_hit/count;

        System.out.println("Cache #2");
        System.out.println("Cache size: 2048B   Associativity: 1    Block size: 2");
        System.out.print("Hits: " + cache2_hit + "    Hit Rate: ");
        System.out.printf("%.2f",percentage2);
        System.out.println("%");
        System.out.println("---------------------------");

        //cache3
        double percentage3 = 100.0 * cache3_hit/count;

        System.out.println("Cache #3");
        System.out.println("Cache size: 2048B   Associativity: 1    Block size: 4");
        System.out.print("Hits: " + cache3_hit + "    Hit Rate: ");
        System.out.printf("%.2f",percentage3);
        System.out.println("%");
        System.out.println("---------------------------");

         percentage = 100.0 * twoKb_twoWay_oneWord_hit/count;

        System.out.println("Cache #4");
        System.out.println("Cache size: 2048B   Associativity: 2    Block size: 1");
        System.out.print("Hits: " + twoKb_twoWay_oneWord_hit + "    Hit Rate: ");
        System.out.printf("%.2f",percentage);
        System.out.println("%");
        System.out.println("---------------------------");

        percentage = 100.0 * twoKb_fourWay_oneWord_hit/count;

        System.out.println("Cache #5");
        System.out.println("Cache size: 2048B   Associativity: 4    Block size: 1");
        System.out.print("Hits: " + twoKb_fourWay_oneWord_hit + "    Hit Rate: ");
        System.out.printf("%.2f",percentage);
        System.out.println("%");
        System.out.println("---------------------------");

        percentage = 100.0 * twoKb_fourWay_fourWord_hit/count;

        System.out.println("Cache #6");
        System.out.println("Cache size: 2048B   Associativity: 4    Block size: 4");
        System.out.print("Hits: " + twoKb_fourWay_fourWord_hit + "    Hit Rate: ");
        System.out.printf("%.2f",percentage);
        System.out.println("%");
        System.out.println("---------------------------");

        //cache7
        double percentage7 = 100.0 * cache7_hit/count;

        System.out.println("Cache #7");
        System.out.println("Cache size: 4096B   Associativity: 1    Block size: 1");
        System.out.print("Hits: " + cache7_hit + "    Hit Rate: ");
        System.out.printf("%.2f",percentage7);
        System.out.println("%");
        System.out.println("---------------------------");


    }

}
