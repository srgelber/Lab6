import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class lab6 {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // read file
        File file = new File(args[0]);
        BufferedReader br1 = new BufferedReader(new FileReader(file));
        int count = 0;
        ArrayList<String> addresses = new ArrayList<String>();
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


        String line = br1.readLine();
        while (line != null) {
            String[] split = line.split("\\t");

            String num = split[1];
            int address = Integer.parseInt(num, 16);
            if(count == 0){
            
        }
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
            
            //insert logic for other caches here
            


            line = br1.readLine();
            count++;
        }
        br1.close();
        //print the end display
        //cache1
        double percentage = 100.0 * cache1_hit/5000000;

        System.out.println("Cache #1");
        System.out.println("Cache size: 2048B   Associativity: 1    Block size: 1");
        System.out.print("Hits: " + cache1_hit + "    Hit Rate: ");
        System.out.printf("%.2f",percentage);
        System.out.println("%");
        System.out.println("---------------------------");

        //cache2
        double percentage2 = 100.0 * cache2_hit/5000000;

        System.out.println("Cache #2");
        System.out.println("Cache size: 2048B   Associativity: 1    Block size: 2");
        System.out.print("Hits: " + cache2_hit + "    Hit Rate: ");
        System.out.printf("%.2f",percentage2);
        System.out.println("%");
        System.out.println("---------------------------");

        //cache3
        double percentage3 = 100.0 * cache3_hit/5000000;

        System.out.println("Cache #3");
        System.out.println("Cache size: 2048B   Associativity: 1    Block size: 4");
        System.out.print("Hits: " + cache3_hit + "    Hit Rate: ");
        System.out.printf("%.2f",percentage3);
        System.out.println("%");
        System.out.println("---------------------------");
        //insert other caches here
        //cache7
        double percentage7 = 100.0 * cache7_hit/5000000;

        System.out.println("Cache #7");
        System.out.println("Cache size: 2048B   Associativity: 1    Block size: 1");
        System.out.print("Hits: " + cache7_hit + "    Hit Rate: ");
        System.out.printf("%.2f",percentage7);
        System.out.println("%");
        System.out.println("---------------------------");


       // for (int i=0; i < addresses.size(); i++){

            //int num = Integer.parseInt(addresses.get(i), 16);

            //String binAddress = Integer.toBinaryString(num);

            //System.out.println(binAddress);


        //}



    }

}
