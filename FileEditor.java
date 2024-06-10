import java.io.*;

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

    public static Clothing retreiveByFilter(String filter) {
        String line = " ";
        try{
            FileReader fr = new FileReader("clothingList.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                if (line.contains(filter)) {
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
}
