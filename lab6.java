import java.io.*;
import java.util.ArrayList;

public class lab6 {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        // read file
        File file = new File(args[0]);
        BufferedReader br1 = new BufferedReader(new FileReader(file));
        int count = 0;
        ArrayList<String> addresses = new ArrayList<String>();

        String line = br1.readLine();
        while (line != null) {
            String[] split = line.split("\\t");

            addresses.add(count, split[1]);

            line = br1.readLine();
            count++;
        }
        br1.close();
        System.out.println(addresses);


        for (int i=0; i < addresses.size(); i++){

            int num = Integer.parseInt(addresses.get(i), 16);

            String binAddress = Integer.toBinaryString(num);

            System.out.println(binAddress);


        }



    }

}