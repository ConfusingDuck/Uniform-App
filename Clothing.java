public class Clothing {
    private double price;
    private String condition;
    private String gender;
    private String size;
    private String rPrice;
    private String imagePath;
    private String name;

    public static int binNumber = 0;

    public Clothing() {
        price = 0;
        condition = "";
        gender = "";
        size = "";
        imagePath = "";
        name = "";
    }

    public Clothing(String name, String condition, double price, String imagePath, String size) {
        this.name = name;
        this.condition = condition;
        this.price = price;
        this.imagePath = imagePath;
        this.size = size;
    }

    public void newBinNumber() {
        binNumber++;
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

    public int getBinNumber() {
        return binNumber;
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

    public String toString() {
        rPrice = String.format("%.2f", price);
        return (binNumber + "|" + name + "|" + condition + "|" + size + "|" + condition + "|" + rPrice);
    }
}
