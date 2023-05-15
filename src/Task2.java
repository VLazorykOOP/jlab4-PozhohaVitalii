import java.io.*;
import java.util.*;
public class Task2 {

        public void start () {
            try {
                File F = new File("out.txt");
               // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                FileOutputStream fos = new FileOutputStream(F,true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter text (press Ctrl+Z to stop):");
                int c;
                while ((c = reader.read()) != -1) {
                    fos.write((char) c);
                }
//                while ((c = System.in.read()) != -1) {
//                    fos.write((char) c);
//                }


                  //reader.close();
                fos.close();

                System.out.println("Successfully written to file.");
            }catch (FileNotFoundException e) {
                System.out.println("file not found");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("An error occurred while writing to file.");
                e.printStackTrace();
            }
        }



}
