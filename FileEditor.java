import java.io.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/*This class is used to read and write from the clothinglist file
 * Returns clothing items by various search types*
 * Contains only static methods because no objects of this class are to be made
 * This class only provides functionality through methods*/
public class FileEditor {
    
    /*This method adds a new clothing item to the file*/
    public static void storeClothingItem(Clothing clothing){
        try {
            //The true moves the writer to the end of the file
            FileWriter fw = new FileWriter("clothingList.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(clothing.toString());
            bw.close();
        }
        catch (IOException e) {
        }
    }

    /*This method stores a picture in the program folder
     *It takes the local image path the user enters and saves it with a unique bin number*/
    public static void storePicture(Clothing clothing) {
        try {
                BufferedImage bi;
                //Get the image using the local image path
                bi = ImageIO.read(new File(clothing.getImagePath()));
                //Store it in the common folder using a unique bin number
                File storedImage = new File(clothing.getBinNum() + ".png");
                ImageIO.write(bi, "png", storedImage);
                //Set the image path of the object
                clothing.setImagePath(clothing.getBinNum() + ".png");
            }
            //If the file does not exist (image path is blank because user did not upload an image), then upload a defalut blank image
            catch (IOException e) {
                try {
                    BufferedImage bi;
                    bi = ImageIO.read(new File("jeans example.png"));
                    File storedImage = new File(clothing.getBinNum() + ".png");
                    ImageIO.write(bi, "png", storedImage);
                    clothing.setImagePath("No_image_available.png");
                }
                catch(IOException u) {
                }
            }
    }

    /*This method retreives all clothing items from the file
     *Returns a list of all the objects*/
    public static ArrayList<Clothing> retreiveAll() {
        ArrayList<Clothing> clothes = new ArrayList<Clothing>();
        String line = "";
        try {
            FileReader fr = new FileReader("clothingList.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                Clothing clothing = new Clothing(line);
                clothes.add(clothing);
            }
        }
        catch (IOException e) {
        }
        return clothes;
    }

    /*This method searches the file for a specific clothing item by its unique bin number */
    public static Clothing retreiveByBin(String binNum) {
        String line = " ";
        try{
            FileReader fr = new FileReader("clothingList.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                String[] words = line.split("|");
                //Checks the last element, the bin number, to see if the number matches
                if (words[-1].equals(binNum)) {
                    Clothing clothing = new Clothing(line);
                    return clothing;
                }
            }
            br.close();
        }
        catch(IOException e) {
        }
        Clothing clothing = new Clothing();
        return clothing;
    }

    /*This method searches for clothing items by a specific filter, such as XS(size)
     * Returns a list of all clothing objects with that filter */
    public static ArrayList<Clothing> retreiveByFilter(String filter) {
        String line = " ";
        ArrayList<Clothing> clothes = new ArrayList<Clothing>();
        try{
            FileReader fr = new FileReader("clothingList.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                //If the String contains the name of the filter, it is added to the list
                if (line.contains(filter)) {
                    Clothing clothing = new Clothing(line);
                    clothes.add(clothing);
                }
            }
            br.close();
            //If the size of the list is greater than 0, it is returned
            if (clothes.size() > 0) {
                return clothes;
            }
        }
        catch(IOException e) {
        }
        //If the size of the list is not greater than 0, an empty clothing object is made and returned as part of the lsit
        Clothing clothing = new Clothing();
        clothes.add(clothing);
        return clothes;
    }

    /*This method searches for a clothing item by bin and removes it from the file
     *This method will be used to remove an item once it has been sold*/
    public static void removeByBin(String binNum) {
        String line = "";
        ArrayList<String> clothes = new ArrayList<String>();
        try {
            FileReader fr = new FileReader("clothingList.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null){
                String[] words = line.split("|");
                //If the bin number is found, it is not added to the list, and thus removed when the list is rewritten to the file
                if (words[words.length - 1].equals(binNum)) {
                }
                else {
                    clothes.add(line);
                }
            }
            br.close();

            //Rewrite the list to the file without the specified clothing object
            FileWriter fw = new FileWriter("clothingList.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < clothes.size(); i++) {
                bw.write(clothes.get(i));
                bw.newLine();
            }
            bw.close();
        }
        catch (IOException e) {
        }
    }
}
