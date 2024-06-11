import java.io.*;

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

    public Clothing(String dataLine) {
        String[] words = dataLine.split("|");
        init(words[0], words[1], words[2], Double.valueOf(words[3]), words[4], words[5], words[6]);
        binNum = getLatestBinNum() + 1;
    }

    // Find a way to add bin number and make it increase by 1 each time an item is
    // added

    public Clothing(String username, String name, String condition, double price, String imagePath, String size,
            String gender) {
        init(username, name, condition, price, imagePath, size, gender);
        binNum = getLatestBinNum() + 1;
    }

    private void init(String username, String name, String condition, double price, String imagePath, String size,
            String gender) {
        this.username = username;
        this.name = name;
        this.condition = condition;
        this.price = price;
        this.imagePath = imagePath;
        this.size = size;
        this.gender = gender;
    }

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

    public int getLatestBinNum() {
        try {
            FileReader fr = new FileReader("clothingList.txt");
            BufferedReader br = new BufferedReader(fr);

            String last = "", line = "";
            while ((line = br.readLine()) != null) {
                last = line;
            }
            br.close();
            if (last.equals("")) {
                return 0;
            } else {
                String[] words = last.split("|");
                if (words.length != 7) {
                    return 0;
                } else {
                    return (Integer.parseInt(words[-1]));
                }
            }
        } catch (IOException e) {
        }
        return 0;
    }

    public String toString() {
        rPrice = String.format("%.2f", price);
        return (username + "|" + name + "|" + condition + "|" + rPrice + "|" + imagePath + "|" + size + "|" + gender + "|" + binNum);
    }
}
