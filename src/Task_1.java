import java.io.*;
import java.util.*;


public class Task_1 {
    Scanner scanner = new Scanner(System.in);
      File InputFileWithWordsAddress;
      File InputFileWithTextAddress;
      File OutputFileAddress;
      ArrayList<String> WordsList;
      ArrayList<ArrayList<String>> Text;
    public Task_1() {
        //Scanner scanner = new Scanner(System.in);
        System.out.println("input file (if need, all address) with words list : ");
        InputFileWithWordsAddress = new File(scanner.nextLine());
        System.out.println(InputFileWithWordsAddress);
        System.out.println("input address of a file with text : ");
        InputFileWithTextAddress = new File(scanner.nextLine());
        System.out.println(InputFileWithTextAddress);
        System.out.println("input address of a file where you want to get result : ");
        OutputFileAddress = new File(scanner.nextLine());
        System.out.println(OutputFileAddress);
    }
    boolean rewriteFileVar(File obj,String A){
        if (A == "input address of a file with words list : "){
            System.out.println("input file with words list :" + "(Again) ");
            InputFileWithWordsAddress = new File(scanner.nextLine());
            System.out.println(InputFileWithWordsAddress);
        } else if (A=="input address of a file with text : ") {
            System.out.println("input address of a file with text :" + "(Again) ");
            InputFileWithTextAddress = new File(scanner.nextLine());
            System.out.println(InputFileWithTextAddress);
        } else if (A=="input address of a file where you want to get result : ") {
            System.out.println("input address of a file where you want to get result :" + "(Again) ");
            OutputFileAddress = new File(scanner.nextLine());
            System.out.println(OutputFileAddress);
        }

        return true;
    }
    public boolean OpenFirstFile(){
        boolean f = false;
        if (true){
            System.out.println(InputFileWithWordsAddress.getAbsolutePath());
        }
        while (!InputFileWithWordsAddress.isFile()){
            rewriteFileVar(InputFileWithWordsAddress,"input address of a file with words list : ");
            System.out.println("re-writed!!! ");
        }
        WordsList = new ArrayList<String>();
            try (BufferedReader reader = new BufferedReader(new FileReader(InputFileWithWordsAddress)))  {
                String Baf;
                while ((Baf = reader.readLine()) != null){
                    // Arrays.asList(Baf.split("[, :;]+")) add this into WordsList.add(*HERE*); if list are not normal
                    WordsList.add(Baf);
                }


                f = true;
                reader.close();
            }catch (FileNotFoundException a){
                System.out.println("File not FOUND (try again)");
            }catch (IOException e) {
                System.out.println("Check you file capacity");
                throw new RuntimeException(e);
            }

        System.out.println(WordsList);


        return f;
    }
    public boolean OpenSecondFile(){
        boolean f = false;
        if (true){
            System.out.println(InputFileWithTextAddress.getAbsolutePath());
        }
        while (!InputFileWithTextAddress.isFile()){
            rewriteFileVar(InputFileWithTextAddress,"input address of a file with text : ");
            System.out.println("re-writed!!! ");
        }
        Text = new ArrayList<ArrayList<String>>();
        try (BufferedReader reader = new BufferedReader(new FileReader(InputFileWithTextAddress)))  {
            String Baf;

            while ((Baf = reader.readLine()) != null){

                Text.add(new ArrayList<String>(Arrays.asList(Baf.split("[, .:;]+"))));

            }


            f = true;
        }catch (FileNotFoundException a){
            System.out.println("File not FOUND (try again)");
        }catch (IOException e) {
            System.out.println("Check you file capacity");
            throw new RuntimeException(e);
        }

        System.out.println(Text);


        return f;
    }

    public SortedMap ListOFWords(){
        SortedMap<String,Integer> ListMap = new TreeMap<String,Integer>();
        for (String word:WordsList){
            int iterator=0;
            for (ArrayList<String> textLine:Text){
                for (String TextWord :textLine){
                    if(TextWord.equals(word)){
                        iterator++;
                    }
                }
            }
            ListMap.put(word,iterator);
        }

        System.out.println(ListMap);
        return ListMap;
    }
    public boolean OpenThirdFile(){
        boolean f = false;
        boolean flag = false;
        if (true){
            System.out.println(OutputFileAddress.getAbsolutePath());
        }
        while (!OutputFileAddress.isFile()){
            rewriteFileVar(OutputFileAddress,"input address of a file where you want to get result : ");
            System.out.println("re-writed!!! ");
        }
        System.out.println("Do you want to open file in ADD-mode (true/false)");
        while (!scanner.hasNextBoolean()){
            System.out.println("Try again (you are so close) : ");

            flag = new Boolean(scanner.nextBoolean());
            scanner.nextLine();

        }
        System.out.println("Successfully (in the end :,)  :" + flag);

        SortedMap<String,Integer> T = new TreeMap<String,Integer>(ListOFWords());
        SortedMap<String,Integer> T1 = new TreeMap<String,Integer>();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OutputFileAddress,flag)))  {


            for (int i = 0; i < Text.size(); i++) {

                for (int j = 0; j < Text.get(i).size(); j++) {
                    point:
                    for (String word: T.keySet()) {
                       if(Text.get(i).get(j).equals(word) && T.get(word) == -1){
                          // writer.write( " ATTENTION ");
                           j++;
                           break point;
                       }
                       if(Text.get(i).get(j).equals(word) && T.get(word) != -1){
                            writer.write(Text.get(i).get(j) + " " );
                            T.put(word,-1);
                           T1.put(Text.get(i).get(j) ,1);
                            j++;
                           break point;
                        }
                    }
                    T1.put(Text.get(i).get(j),1);
                    writer.write(Text.get(i).get(j) + " ");

                }
                writer.newLine();
            }

            writer.close();
            f = true;
        }catch (FileNotFoundException a){
            System.out.println("File not FOUND (try again)");
        }catch (IOException e) {
            System.out.println("Check you file capacity");
            throw new RuntimeException(e);
        }

//        File file = new File(fileName);
//
//        try {
//            if (file.createNewFile()) {
//                System.out.println("File created successfully.");
//            } else {
//                System.out.println("File already exists.");
//            }
//        } catch (IOException e) {
//            System.out.println("An error occurred while creating the file.");
//            e.printStackTrace();
//        }
        File Result = new File("Result.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Result,false)))  {




                    for (String word: T1.keySet()) {
                        writer.write(word+"\n");
                    }


            writer.close();
            f = true;
        }catch (FileNotFoundException a){
            System.out.println("File not FOUND (try again)");
        }catch (IOException e) {
            System.out.println("Check you file capacity");
            throw new RuntimeException(e);
        }

        System.out.println(Text);


        return f;
    }


}
