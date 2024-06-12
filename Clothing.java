import java.io.*;

/*This class builds clothing objects with properties listed below*/
public class Clothing {
    private double price;
    private String condition;
    private String gender;
    private String size;
    private String rPrice;
    private String imagePath;
    private String name;
    private String username;
    private int binNum;

    /*This constructor builds a blank clothing item */
    public Clothing() {
        price = 0;
        condition = "";
        gender = "";
        size = "";
        imagePath = "";
        name = "";
        username = "";
        binNum = getLatestBinNum() + 1;
    }

    /*This constructor initliazes a clothing item using just the string from the file*/
    public Clothing(String dataLine) {
        String[] words = dataLine.split("\\|");
        init(words[0], words[1], words[2], Double.valueOf(words[3]), words[4], words[5], words[6], Integer.valueOf(words[7]));
    }

    /*This method allows for a clothing item to be made by inputting all of its features*/
    public Clothing(String username, String name, String condition, double price, String imagePath, String size,
            String gender, int binNum) {
        init(username, name, condition, price, imagePath, size, gender, binNum);
    }

    /*This method is private and is used to initliaze
     *Was made to avoid repetition in the different constructors*/
    private void init(String username, String name, String condition, double price, String imagePath, String size,
            String gender, int binNum) {
        this.username = username;
        this.name = name;
        this.condition = condition;
        this.price = price;
        this.imagePath = imagePath;
        this.size = size;
        this.gender = gender;
        this.binNum = binNum;
    }

    /*All of the set and get functions below simply set or return a feature of the clothing item*/
    public void setUsername(String u) {
        username = u;
    }

    public void setCondition(String c) {
        condition = c;
    }

    public void setPrice(Double p) {
        price = p;
    }

    public void setGender(String g) {
        gender = g;
    }

    public void setSize(String s) {
        size = s;
    }

    public void setImagePath(String i) {
        imagePath = i;
    }

    public void setName(String n) {
        name = n;
    }

    public String getUsername() {
        return username;
    }

    public String getCondition() {
        return condition;
    }

    public double getPrice() {
        return price;
    }

    public String getGender() {
        return gender;
    }

    public String getSize() {
        return size;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }

    public int getBinNum() {
        return binNum;
    }

    /*Gets the next bin number to be associated with a clothing item
     *Generates a new unique bin number by incrementing the last used bin number by 1
     Is static so that it does not pertain to an object, but can be used by all objects and without an instance of the class*/
    public static int getLatestBinNum() {
        try {
            FileReader fr = new FileReader("clothingList.txt");
            BufferedReader br = new BufferedReader(fr);

            String last = "", line = "";
            while ((line = br.readLine()) != null) {
                last = line;
            }
            br.close();
            //If the last line is null (there are no clothing items in the file), return 0
            if (last.equals("")) {
                return 0;
            } else {
                String[] words = last.split("\\|");
                //If the array size is less than 8, the number of features in a clothing item, return 0
                if (words.length < 8) {
                    return 0;
                } else {
                    //Otherwise, return the correct index of the last element
                    return (Integer.parseInt(words[7])); // Correct the index to the last element
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Return 0 if errors occur
        return 0;
    }

    /*This method converts the object's data to a string that is ready to be written to a file */
    public String toString() {
        rPrice = String.format("%.2f", price);
        return (username + "|" + name + "|" + condition + "|" + rPrice + "|" + imagePath + "|" + size + "|" + gender
                + "|" + binNum);
    }
}
