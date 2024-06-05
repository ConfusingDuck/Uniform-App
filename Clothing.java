public class Clothing {
    private double price;
    private String condition;
    private String gender;
    private String size;
    private String rPrice;

    public Clothing(){
        price = 0;
        condition = "";
        gender = "";
        size = "";
    }

    public Clothing(double p, String c, String g, String s){
        price = p;
        condition = c;
        gender = g;
        size = s;
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

    public String getCondition() {
        return condition;
    }

    public double getPrice() {
        return price;
    }

    public String getGender() {
        return gender;
    }

    public String getSize(){
        return size;
    }

    public String toString() {
        rPrice = String.format("%.2f", price);
        return ("Gender: " + gender + "\nSize: " + size + "\nCondition: " + condition + "\nPrice: " + rPrice);
    }
}
