import java.io.*;
import java.util.ArrayList;

public class FileEditor {
    
    public static void storeClothingItem(Clothing clothing){
        try {
            FileWriter fw = new FileWriter("clothingList.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(clothing.toString());
            bw.close();
        }
        catch (IOException e) {
        }
    }

    public static Clothing retreiveByBin(String binNum) {
        String line = " ";
        try{
            FileReader fr = new FileReader("clothingList.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                if (line.contains(binNum)) {
                    Clothing clothing = new Clothing(line);
                    return clothing;
                }
            }
        }
        catch(IOException e) {
        }
        Clothing clothing = new Clothing();
        return clothing;
    }

    public static ArrayList<Clothing> retreiveByFilter(String filter) {
        String line = " ";
        ArrayList<Clothing> clothes = new ArrayList<Clothing>();
        try{
            FileReader fr = new FileReader("clothingList.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                if (line.contains(filter)) {
                    Clothing clothing = new Clothing(line);
                    clothes.add(clothing);
                }
            }
            if (clothes.size() > 0) {
                return clothes;
            }
        }
        catch(IOException e) {
        }
        Clothing clothing = new Clothing();
        clothes.add(clothing);
        return clothes;
    }
}
